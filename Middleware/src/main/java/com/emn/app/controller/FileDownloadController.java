package com.emn.app.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FileDownloadController {
	
	@Value(value = "${app.folderLocation}")
	private String folderLocation;
	
	@RequestMapping(value = "/filedownload.htm", method = RequestMethod.GET)
	public String FileDownloadPage(Model model){
		List<String> fileList = new ArrayList<String>();
		
		File directory = new File(folderLocation);
		String[] files = directory.list();
		for (int i = 0; i < files.length ; i++) {
			if (!files[i].endsWith("~")) {
				fileList.add(files[i]);
			}
		}
		model.addAttribute("fileList", fileList);
		
		return "FileDownloadPage";
	}
}
