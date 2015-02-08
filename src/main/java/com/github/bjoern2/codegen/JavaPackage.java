package com.github.bjoern2.codegen;

import java.util.List;

public interface JavaPackage {

	String getName();
	
	void setName(String name);
	
	List<JavaFile> getFiles();
	
	void setFiles(List<JavaFile> files);
	
}
