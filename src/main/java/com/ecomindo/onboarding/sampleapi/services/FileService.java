package com.ecomindo.onboarding.sampleapi.services;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

import org.springframework.web.multipart.MultipartFile;

import com.ecomindo.onboarding.sampleapi.model.FilesModel;

public interface FileService {
	public void upload(MultipartFile file) throws Exception;
	public FilesModel storeToDB(MultipartFile file) throws Exception;
	public FilesModel getFile(Long id) throws Exception;
	public List<String> getFileContentFromSFTP(String filename) throws Exception;
	public void archivedFileByCode(String filename) throws Exception;
	public FilesModel getFileByName(String name) throws Exception;
	public List<String> getFileContentByFilename(String filename) throws Exception;
}
