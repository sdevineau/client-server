package com.emn.app.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.emn.app.model.CompilationResult;
import com.emn.app.model.FileUpload;
import com.emn.app.model.Post;
import com.emn.app.parser.CompilationResultBuilder;

public class FileUploadController extends SimpleFormController {

	private Properties appConfig;
	
	public FileUploadController(){
		setCommandClass(FileUpload.class);
		setCommandName("fileUploadForm");
	}
 
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
		HttpServletResponse response, Object command, BindException errors)
		throws Exception {
		FileUpload file = (FileUpload)command;
 
		MultipartFile multipartFile = file.getFile();
 
		String fileName="";
		CompilationResult result = new CompilationResult();
		if(multipartFile!=null){
			fileName = multipartFile.getOriginalFilename();
			
			String directoryName = appConfig.getProperty("app.folderLocation");
			String destination = directoryName + fileName;
			File directory = new File(directoryName);
			if(!directory.isDirectory()) {
				directory.mkdir();
			}	
			File fileCopy = new File(destination);
			multipartFile.transferTo(fileCopy);
			
			if(fileName.endsWith(".java")) {
				String fileNameWithoutExtension = fileName.substring(0, fileName.length() - 5);
				String sourceCode = convertStreamToString(multipartFile.getInputStream());
				
				File xmlFile = new File(directoryName + "result.xml");

				FileWriter fw;
				try {
					fw = new FileWriter(xmlFile.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(Post.remoteCompiling("java" , sourceCode, fileNameWithoutExtension));
					bw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				result = CompilationResultBuilder.parseXMLResult(xmlFile);
			}	
		}
		ModelAndView model = new ModelAndView("FileUploadSuccess");
		model.addObject("fileName", fileName);
		model.addObject("result", result);
		return model;
	}
	
	String convertStreamToString(InputStream is) {
	    Scanner s = new Scanner(is, "UTF-8").useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}
	
	public void setAppConfig(Properties appConfig) {
		this.appConfig = appConfig;
	}
}
