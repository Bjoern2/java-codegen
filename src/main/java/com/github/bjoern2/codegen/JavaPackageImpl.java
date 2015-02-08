package com.github.bjoern2.codegen;

import java.util.List;

public class JavaPackageImpl implements JavaPackage {

	private String name;
	private List<JavaFile> files;
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public List<JavaFile> getFiles() {
		return files;
	}

	@Override
	public void setFiles(List<JavaFile> files) {
		this.files = files;
	}


}
