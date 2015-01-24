package com.github.bjoern2.codegen;

import java.util.List;

public class JavaInterfaceImpl implements JavaInterface {

	private List<JavaAnnotation> annotations;
	private JavaAccessType accessType;
	private String name;
	private List<JavaType> extendsFrom;
	private List<JavaMethod> methods;
	private List<JavaField> fields;
	private String comment;
	
	@Override
	public List<JavaAnnotation> getAnnotations() {
		return annotations;
	}

	@Override
	public void setAnnotations(List<JavaAnnotation> annotations) {
		this.annotations = annotations;
	}

	@Override
	public JavaAccessType getAccessType() {
		return accessType;
	}

	@Override
	public void setAccessType(JavaAccessType type) {
		this.accessType = type;
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
	public List<JavaType> getExtendsFrom() {
		return extendsFrom;
	}

	@Override
	public void setExtendsFrom(List<JavaType> type) {
		this.extendsFrom = type;
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
	public List<JavaField> getFields() {
		return fields;
	}

	@Override
	public void setFields(List<JavaField> fields) {
		this.fields = fields;
	}

	@Override
	public String getComment() {
		return comment;
	}

	@Override
	public void setComment(String comment) {
		this.comment = comment;
	}

}
