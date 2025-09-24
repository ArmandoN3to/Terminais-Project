package com.superterminais.portal.controller;

import com.superterminais.portal.model.Attachment;
import com.superterminais.portal.service.FileStorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/companies/{companyId}/documents")
public class DocumentController {

    private final FileStorageService fileStorageService;

    public DocumentController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping
    public ResponseEntity<Attachment> uploadDocument(
            @PathVariable UUID companyId,
            @RequestParam("file") MultipartFile file) {
        
        Attachment attachment = fileStorageService.storeFile(file, companyId);
        return new ResponseEntity<>(attachment, HttpStatus.CREATED);
    }
}