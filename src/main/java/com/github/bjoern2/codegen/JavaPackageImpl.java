package com.github.bjoern2.codegen;

import java.util.List;

public class JavaPackageImpl implements JavaPackage {

	private String name;
	private List<JavaClass> clazzes;
	private List<JavaInterface> interfaces;
	private List<JavaEnum> enums;
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public List<JavaClass> getClazzes() {
		return clazzes;
	}

	@Override
	public void setClazzes(List<JavaClass> clazzes) {
		this.clazzes = clazzes;
	}

	@Override
	public List<JavaInterface> getInterfaces() {
		return interfaces;
	}

	@Override
	public void setInterfaces(List<JavaInterface> interfaces) {
		this.interfaces = interfaces;
	}

	@Override
	public List<JavaEnum> getEnums() {
		return enums;
	}

	@Override
	public void setEnums(List<JavaEnum> enums) {
		this.enums = enums;
	}

}
