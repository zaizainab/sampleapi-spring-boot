package com.ecomindo.onboarding.sampleapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecomindo.onboarding.sampleapi.dto.ResponseDTO;
import com.ecomindo.onboarding.sampleapi.model.FilesModel;
import com.ecomindo.onboarding.sampleapi.services.FileService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/sftp")
public class SftpController {

	@Autowired
	FileService fileService;

//	@ApiParam(allowMultiple=true) 
	@ApiOperation("Upload file to SFTP")
	@RequestMapping(path = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseDTO upload(@RequestPart(value = "file") MultipartFile file) {
		ResponseDTO response = new ResponseDTO();
		try {
			fileService.upload(file);

			response.setCode("200");
			response.setMessage("Upload Success");

			return response;
		} catch (Exception e) {
			response.setCode("500");
			response.setMessage("Upload Failed");
			return response;
		}
	}
	
	@RequestMapping(path = "/archived", method = RequestMethod.GET)
	public ResponseDTO archivedAllFileTextFromSFTP() {
		ResponseDTO response = new ResponseDTO();
		try {
			fileService.archivedFileByCode(".txt"); 			
			
			response.setCode("200");
			response.setMessage("Archived Success");
			return response;
		} catch (Exception e) {
			response.setCode("500");
			response.setMessage("Get All file Failed");
			return response;
		}
	}

	@ApiOperation("Insert file to DB")
	@RequestMapping(path = "/upload2", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseDTO upload2(@RequestPart(value = "file") MultipartFile file) {
		ResponseDTO response = new ResponseDTO();
		try {
			fileService.storeToDB(file);

			response.setCode("200");
			response.setMessage("Uploaded the file successfully: " + file.getOriginalFilename());

			return response;
		} catch (Exception e) {
			response.setCode("500");
			response.setMessage("Could not upload the file: " + file.getOriginalFilename() + "!");
			return response;
		}
	}
	
	@ApiOperation("Get file from DB")
	@RequestMapping(path = "/download/{id}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> download(@PathVariable Long id) {
		try {
			FilesModel fileDB = fileService.getFile(id);

			return ResponseEntity.ok()
			        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
			        .body(fileDB.getData());
		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
//	@ApiOperation("Reupload file from SFTP to DB")
//	@RequestMapping(path = "/reupload", method = RequestMethod.POST)
//	public ResponseDTO reupload(@RequestBody String filename) {
//		ResponseDTO response = new ResponseDTO();
//		try {
//			String fileContent = fileService.getFileContentFromSFTP(filename).get(0);
//			FilesModel fileDB = fileService.getFileByName(filename);
//			
//			if(fileContent.isEmpty() || fileDB == null)
//				throw new Exception("There is no such file stored in database");
//			
//			String firstValue = fileContent.substring(0, fileContent.length()/2);
//			String secondValue = fileContent.substring((fileContent.length())/2, fileContent.length());
//			Future<Void> firstBatch = fileService.uploadFileFromFileContent(filename, firstValue);
//			Future<Void> secondBatch = fileService.uploadFileFromFileContent(filename, secondValue);
//			
//			
//			while (!(firstBatch.isDone() && secondBatch.isDone())) {
//			  if(!firstBatch.isDone()){
//			  System.out.println("Add file From File Job first batch is running..."); }
//			  
//			  if(!secondBatch.isDone()){
//				  System.out.println("Add file From File Job second batch is running..."); 
//			  }
//			  
//			  if(firstBatch.isDone() && secondBatch.isDone()) {
//				  System.out.println("Processing all batch is done..."); 
//			  }
////			  if(firstBatch.isCancelled() || secondBatch.isCancelled())
////				  System.out.println("Error...");
//			  
//			  Thread.sleep(500); 
//			}
//			 
//			
//			response.setCode("200");
//			response.setMessage("Uploaded the files successfully");
//
//			return response;
//		} catch (Exception e) {
//			response.setCode("500");
//			response.setMessage("Could not upload files: " + e.getMessage());
//			return response;
//		}
//	}
}
