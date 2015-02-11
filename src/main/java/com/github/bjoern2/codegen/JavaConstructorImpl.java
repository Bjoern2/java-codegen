package com.github.bjoern2.codegen;

import java.util.List;

public class JavaConstructorImpl implements JavaConstructor {

	private JavaAccessType accessType;
	private List<JavaAnnotation> annotations;
	private String comment;
	private List<JavaMethodParameter> parameters;
	private String name;
	private List<JavaType> _exceptions;
	private String body;
	
	@Override
	public JavaAccessType getAccessType() {
		return accessType;
	}

	@Override
	public void setAccessType(JavaAccessType type) {
		this.accessType = type;
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
	public String getComment() {
		return comment;
	}

	@Override
	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public void write(int tabs, Generator generator) {
		// TODO Auto-generated method stub
		
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
	public List<JavaMethodParameter> getParameters() {
		return parameters;
	}

	@Override
	public void setParameters(List<JavaMethodParameter> parameters) {
		this.parameters = parameters;
	}

	@Override
	public List<JavaType> getExceptions() {
		return _exceptions;
	}

	@Override
	public void setExceptions(List<JavaType> _exceptions) {
		this._exceptions = _exceptions;
	}

	@Override
	public String getBody() {
		return body;
	}

	@Override
	public void setBody(String body) {
		this.body = body;
	}

}
