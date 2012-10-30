package com.emn.app.parser;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.emn.app.model.CompilationResult;

public class CompilationResultBuilder {

	public static CompilationResult parseXMLResult(File xmlFile) {
		CompilationResult compilationResult = new CompilationResult();

		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);

			Element root = doc.getDocumentElement();

			NodeList children = root.getChildNodes();
			Element nCompilation = (Element)children.item(0);
			Element nExecution = (Element)children.item(1);

			String compilationError = nCompilation.getChildNodes().item(0).getTextContent();
			String compilationOutput = nCompilation.getChildNodes().item(1).getTextContent();
			String compilationTimeElapsed = nCompilation.getChildNodes().item(2).getTextContent();
			if (compilationError!=null) {
				compilationResult.setCompilationError(compilationError);
			}
			if (compilationOutput!=null) {
				compilationResult.setCompilationOutput(compilationOutput);
			}
			if (compilationTimeElapsed!=null) {
				compilationResult.setCompilationTimeElapsed(compilationTimeElapsed);
			}

			String executionError = nExecution.getChildNodes().item(0).getTextContent();
			String executionOutput = nExecution.getChildNodes().item(1).getTextContent();
			String executionTimeElapsed = nExecution.getChildNodes().item(2).getTextContent();
			if (executionError!=null) {
				compilationResult.setExecutionError(executionError);
			}
			if (executionOutput!=null) {
				compilationResult.setExecutionOutput(executionOutput);
			}
			if (executionTimeElapsed!=null) {
				compilationResult.setExecutionTimeElapsed(executionTimeElapsed);
			}	

		} catch (Exception e) {
			e.printStackTrace();
		}

		return compilationResult;
	}

}
