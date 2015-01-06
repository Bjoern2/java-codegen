package com.github.bjoern2.codegen;

public class JavaAnnotationArgumentImpl implements JavaAnnotationArgument {

	private String name;
	private Object value;

	public JavaAnnotationArgumentImpl() {
		super();
	}

	public JavaAnnotationArgumentImpl(String name, Object value) {
		super();
		this.name = name;
		this.value = value;
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
	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

}
