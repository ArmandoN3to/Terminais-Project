package com.superterminais.portal.service;

import com.superterminais.portal.exception.RegistrationException;
import com.superterminais.portal.model.Attachment;
import com.superterminais.portal.repository.AttachmentRepository;
import com.superterminais.portal.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;


@Service
public class FileStorageService {

    private final Path fileStorageLocation;
    private final AttachmentRepository attachmentRepository;
    private final CompanyRepository companyRepository;

    public FileStorageService(@Value("${file.upload-dir}") String uploadDir, AttachmentRepository attachmentRepository, CompanyRepository companyRepository) {
        this.attachmentRepository = attachmentRepository;
        this.companyRepository = companyRepository;
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public Attachment storeFile(MultipartFile file, UUID companyId) {
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());

        if (originalFileName.contains("..")) {
            throw new RegistrationException("Sorry! Filename contains invalid path sequence " + originalFileName);
        }

        var company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RegistrationException("Company not found with id " + companyId));

        // Construct a unique file name
        String newFileName = companyId.toString() + "_" + System.currentTimeMillis() + "_" + originalFileName;
        Path targetLocation = this.fileStorageLocation.resolve(newFileName);

        try {
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file " + newFileName + ". Please try again!", ex);
        }

        Attachment attachment = new Attachment();
        attachment.setFileName(originalFileName);
        attachment.setFilePath(targetLocation.toString());
        attachment.setContentType(file.getContentType());
        attachment.setSize(file.getSize());
        attachment.setCompany(company);

        return attachmentRepository.save(attachment);
    }
}