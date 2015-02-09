package com.github.bjoern2.codegen;

import java.util.List;

public class JavaPackageImpl implements JavaPackage {

	private String comment;
	private String name;
	private List<JavaAnnotation> annotations;
	
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public List<JavaAnnotation> getAnnotations() {
		return annotations;
	}

	@Override
	public void setAnnotations(List<JavaAnnotation> annotations) {
		this.annotations = annotations;
	}

	@Override
	public void write(int tabs, Generator generator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getComment() {
		// TODO Auto-generated method stub
		return comment;
	}

	@Override
	public void setComment() {
		this.comment = comment;
	}


}
