package com.project.file.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.project.file.entity.FilesEntity;
import com.project.file.service.FilesService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/files")
public class FilesController 
{
	@Autowired 
	private FilesService filesService;
	
	@GetMapping("/getAll")
	public List<FilesEntity> getAllFiles() 
	{
		return filesService.getAllFiles();
	}
	
	
	@PostMapping("/addFile")
	  public ResponseEntity<FilesEntity> addfiles (
			  @RequestParam("aadhaar") MultipartFile aadhaar,
			  @RequestParam("panCard") MultipartFile panCard,
			  @RequestParam("companyForm") MultipartFile companyForm)
	  {
		 try {
	            byte[] aadhaarBytes = aadhaar.getBytes();
	            String aadhaarFilePath = saveFileToDisk(aadhaar);
	            byte[] panCardBytes = panCard.getBytes();
	            String panCardFilePath = saveFileToDisk(panCard);
	            byte[] companyFormBytes = companyForm.getBytes();
	            String companyFormFilePath = saveFileToDisk(companyForm);
	            if (aadhaarFilePath == null && panCardFilePath == null || companyFormFilePath == null) {
	                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	            }
	            
	            FilesEntity filesEntity = new FilesEntity();
	            filesEntity.setAadhaarFile(aadhaarBytes);
	            filesEntity.setAadhaarPath(aadhaarFilePath);
	            filesEntity.setPanCardFile(panCardBytes);
	            filesEntity.setPanCardPath(panCardFilePath);
	            filesEntity.setCompanyFormFile(companyFormBytes);
	            filesEntity.setCompanyFormPath(companyFormFilePath);
	            
	            FilesEntity newDocumentEntity = filesService.addFiles(filesEntity);
	            return ResponseEntity.status(HttpStatus.CREATED).body(newDocumentEntity);
	            
		 }
		 catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	  }
	
	private String saveFileToDisk(MultipartFile file) {
        try {
            String uploadDir = "C:\\Users\\lenovo";
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String filePath = uploadDir + "/" + file.getOriginalFilename();
            file.transferTo(new File(filePath));
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	
//	@GetMapping("/files/{id}")
//    public ResponseEntity<Resource> getDocumentFileById(@PathVariable Long id) {
//        Optional<FilesEntity> optionalTraining = Optional.of(filesService.getFilesById(id));
//        if (optionalTraining.isPresent()) {
//            FilesEntity training = optionalTraining.get();
//            String aadhaarFilePath = training.getAadhaarPath();
//            String panCadeFilePath = training.getPanCardPath();
//            String companyFilePath = training.getCompanyFormPath();
//            if (aadhaarFilePath != null && panCadeFilePath != null && companyFilePath != null ) {
//            	Path path1 = Paths.get(panCadeFilePath);
//            	Path path2 = Paths.get(companyFilePath);
//                Path path = Paths.get(aadhaarFilePath);
//                try {
//                    ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
//                    ByteArrayResource resource1 = new ByteArrayResource(Files.readAllBytes(path1));
//                    ByteArrayResource resource2 = new ByteArrayResource(Files.readAllBytes(path2));
//                    return ResponseEntity.ok()
//                            .contentType(MediaType.parseMediaType("video/mp4"))
//                            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + path.getFileName() + "\"")
//                            .body(resource,resource1,resource2);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//                }
//            }
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//    }
	
	
	@GetMapping("/file/{id}")
	public ResponseEntity<MultiValueMap<String, Object>> getDocumentFileById(@PathVariable Long id) {
	    Optional<FilesEntity> optionalFilesEntity = Optional.of(filesService.getFilesById(id));
	    if (optionalFilesEntity.isPresent()) {
	        FilesEntity filesEntity = optionalFilesEntity.get();
	        String aadhaarFilePath = filesEntity.getAadhaarPath();
	        String panCardFilePath = filesEntity.getPanCardPath();
	        String companyFilePath = filesEntity.getCompanyFormPath();
	        
	        if (aadhaarFilePath != null && panCardFilePath != null && companyFilePath != null) {
	            Path aadhaarPath = Paths.get(aadhaarFilePath);
	            Path panCardPath = Paths.get(panCardFilePath);
	            Path companyPath = Paths.get(companyFilePath);
	            
	            try {
	                ByteArrayResource aadhaarResource = new ByteArrayResource(Files.readAllBytes(aadhaarPath));
	                ByteArrayResource panCardResource = new ByteArrayResource(Files.readAllBytes(panCardPath));
	                ByteArrayResource companyResource = new ByteArrayResource(Files.readAllBytes(companyPath));
	                
	                MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
	                body.add("aadhaarFile", aadhaarResource);
	                body.add("panCardFile", panCardResource);
	                body.add("companyFormFile", companyResource);
	                
	                HttpHeaders headers = new HttpHeaders();
	                headers.setContentType(MediaType.MULTIPART_FORM_DATA);
	                headers.setContentDispositionFormData("attachment", aadhaarPath.getFileName().toString());
	                
	                return ResponseEntity.ok()
	                        .headers(headers)
	                        .body(body);
	            } catch (IOException e) {
	                e.printStackTrace();
	                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	            }
	        }
	    }
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	
	@GetMapping("/get/{id}")
    public ResponseEntity<FilesEntity> getFilesById(@PathVariable Long id) {
        FilesEntity training = filesService.getFilesById(id);
        return ResponseEntity.ok().body(training);
  }
}
