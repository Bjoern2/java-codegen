package com.github.bjoern2.codegen;

import java.util.List;

public class JavaAnnotationDefImpl implements JavaAnnotationDef {

	private String comment;
	private List<JavaAnnotation> annotations;
	private JavaAccessType accessType;
	private String name;
	private List<JavaMethod> methods;
	
	@Override
	public List<JavaAnnotation> getAnnotations() {
		return annotations;
	}

	@Override
	public void setAnnotations(List<JavaAnnotation> annotations) {
		this.annotations = annotations;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void write(int tabs, Generator generator) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<JavaMethod> getMethods() {
		return methods;
	}

	@Override
	public void setMethods(List<JavaMethod> methods) {
		this.methods = methods;
	}

	@Override
	public String getComment() {
		return comment;
	}

	@Override
	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public JavaAccessType getAccessType() {
		// TODO Auto-generated method stub
		return accessType;
	}

	@Override
	public void setAccessType(JavaAccessType type) {
		this.accessType = type;
	}

}
