package com.ems.api.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
@Component
public class CommonFileUploadAndDownloadUtils {

    // Use an absolute path for the upload directory
    private static final String FILE_PATH = "C:/path/to/your/upload/directory";
    private static final String FILE_EMPTY = "File is empty";
    private static final String FILE_NOT_SUPPORT = "File type is not supported";
    private static final String[] SUPPORTED_MEDIA_TYPES = {"image/", "video/", "application/pdf"};
    private static final Map<String, String> contentTypesMap = Map.of(
        "jpg", "image/jpeg",
        "jpeg", "image/jpeg",
        "png", "image/png",
        "pdf", "application/pdf",
        "mp4", "video/mp4"
    );

    public String uploadFile(MultipartFile file) throws IllegalStateException, IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalStateException(FILE_EMPTY);
        }
        String contentType = file.getContentType();
        if (!isMediaType(contentType)) {
            throw new IllegalStateException(FILE_NOT_SUPPORT);
        }

        // Ensure the directory exists
        File directoryFile = new File(FILE_PATH);
        if (!directoryFile.exists()) {
            directoryFile.mkdirs();
        }

        String fileName = file.getOriginalFilename();
        String filePath = FILE_PATH + "/" + fileName;
        file.transferTo(new File(filePath));

        return filePath;
    }

    private boolean isMediaType(String contentType) {
        return Arrays.stream(SUPPORTED_MEDIA_TYPES).anyMatch(contentType::startsWith);
    }

    private String determineContentType(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        return contentTypesMap.getOrDefault(extension, "application/octet-stream");
    }

    public ResponseEntity<ByteArrayResource> viewFile(String filePath) throws IOException {
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalStateException(FILE_EMPTY);
        }
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new IllegalStateException(FILE_EMPTY);
        }

        byte[] fileContent = Files.readAllBytes(path);
        ByteArrayResource resource = new ByteArrayResource(fileContent);

        HttpHeaders headers = new HttpHeaders();
        String contentType = determineContentType(filePath);
        headers.setContentType(MediaType.parseMediaType(contentType));
        headers.setContentDispositionFormData("inline", new File(filePath).getName());

        return ResponseEntity.ok().headers(headers).contentLength(fileContent.length).body(resource);
    }

    public boolean deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            return file.delete();
        } else {
            return true;
        }
    }
}
