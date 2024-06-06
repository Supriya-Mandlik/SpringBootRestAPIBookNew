package com.code.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.code.helper.FileUploadHelper;

@RestController
public class FileUploadController {
	
	@Autowired
	private FileUploadHelper fileUploadHelper;
	
	@PostMapping("/upload-file")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file)
	{ // the file name which we give in key we have to give in request param same name.
		//System.out.println(file.getOriginalFilename());
		//System.out.println(file.getSize());
		//System.out.println(file.getContentType());
		//System.out.println(file.getName());
		
		try {
		
		//validation
		if (file.isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request Must Contain File");
		}
		
		//
		if (!file.getContentType().equals("image/jpeg")) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only jpeg content type are allowed");	
			
		}
		
		// file upload code
	
		boolean f=fileUploadHelper.uploadFile(file);
		
		if (f) {
			//static
			//return ResponseEntity.ok("File is SuccessFully Uploaded");
			
			//dynamic
			return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString());
		  // for dynamic path files save in target folder.
		}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong ! Try again");
	}

	
}
