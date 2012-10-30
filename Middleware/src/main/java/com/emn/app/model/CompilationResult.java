package com.emn.app.model;

public class CompilationResult {
	private String compilationError;
	private String compilationOutput;
	private String compilationTimeElapsed;
	private String executionError;
	private String executionOutput;
	private String executionTimeElapsed;
	
	public CompilationResult() {
		this.compilationError = "";
		this.compilationOutput = "";
		this.compilationTimeElapsed = "";
		this.executionError = "";
		this.executionOutput = "";
		this.executionTimeElapsed = "";
	}
	
	
	public String getCompilationError() {
		return compilationError;
	}
	public void setCompilationError(String compilationError) {
		this.compilationError = compilationError;
	}
	public String getCompilationOutput() {
		return compilationOutput;
	}
	public void setCompilationOutput(String compilationOutput) {
		this.compilationOutput = compilationOutput;
	}
	public String getCompilationTimeElapsed() {
		return compilationTimeElapsed;
	}
	public void setCompilationTimeElapsed(String compilationTimeElapsed) {
		this.compilationTimeElapsed = compilationTimeElapsed;
	}
	public String getExecutionError() {
		return executionError;
	}
	public void setExecutionError(String executionError) {
		this.executionError = executionError;
	}
	public String getExecutionOutput() {
		return executionOutput;
	}
	public void setExecutionOutput(String executionOutput) {
		this.executionOutput = executionOutput;
	}
	public String getExecutionTimeElapsed() {
		return executionTimeElapsed;
	}
	public void setExecutionTimeElapsed(String executionTimeElapsed) {
		this.executionTimeElapsed = executionTimeElapsed;
	}
	
	
	
}
