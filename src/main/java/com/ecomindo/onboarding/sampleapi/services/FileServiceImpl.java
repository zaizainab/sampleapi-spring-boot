package com.ecomindo.onboarding.sampleapi.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ecomindo.onboarding.sampleapi.config.Config;
import com.ecomindo.onboarding.sampleapi.dao.FilesDao;
import com.ecomindo.onboarding.sampleapi.model.FilesModel;
import com.ecomindo.onboarding.sampleapi.util.SftpUtil;

@Service
public class FileServiceImpl implements FileService{
	
	@Autowired
	Config config;
	
	@Autowired
	FilesDao filesDao;
	
	private ExecutorService executor = Executors.newFixedThreadPool(2);
	
	@Override
	public void upload(MultipartFile file) throws Exception {
		try {
			SftpUtil sftp = new SftpUtil(config.getSftpHost(), config.getSftpPort(), config.getSftpUsername(),
					config.getSftpPassword(), config.getSftpFolder());

			String filename = file.getOriginalFilename();
//			String fileType = file.getContentType();
//			byte[] fileContent = file.getBytes();

			String sftpPath = config.getSftpFolder().concat("/").concat(filename);
			sftp.sftpPutFromStream(file.getInputStream(), sftpPath);

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<String> getFileContentFromSFTP(String filename) throws Exception {
		SftpUtil sftp = new SftpUtil(config.getSftpHost(), config.getSftpPort(), config.getSftpUsername(),
				config.getSftpPassword(), config.getSftpFolder());

	    String sftpPath = config.getSftpFolder().concat("/").concat(filename);
	    List<String> res = sftp.sftpGetFile(sftpPath);
	    return res;
		
	}
	
	@Override
	public void archivedFileByCode(String code) throws Exception {
		SftpUtil sftp = new SftpUtil(config.getSftpHost(), config.getSftpPort(), config.getSftpUsername(),
				config.getSftpPassword(), config.getSftpFolder());

	    String sftpPath = config.getSftpFolder();
	    sftp.sftpArchiveAllFilenameByCode(sftpPath, sftpPath.concat("/archived"), code);
	}

	@Override
	public FilesModel storeToDB(MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	    FilesModel FileDB = new FilesModel(fileName, file.getContentType(), file.getBytes());

	    return filesDao.save(FileDB);
	}

	@Override
	public FilesModel getFile(Long id) throws Exception {
		return filesDao.findById(id).get();
	}

	@Override
	public FilesModel getFileByName(String name) throws Exception {
		return filesDao.findByName(name);
	}

	@Override
    public List<String> getFileContentByFilename(String filename) throws Exception {
		
		SftpUtil sftp = new SftpUtil(config.getSftpHost(), config.getSftpPort(), config.getSftpUsername(),
				config.getSftpPassword(), config.getSftpFolder());

	    String sftpPath = config.getSftpFolder().concat("/").concat(filename);
	    List<String> res = sftp.sftpGetFile(sftpPath);
	    return res;
        
    } 
}
