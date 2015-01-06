package com.github.bjoern2.codegen.builder;

import org.apache.commons.lang3.StringEscapeUtils;

import com.github.bjoern2.codegen.JavaAnnotationArgument;
import com.github.bjoern2.codegen.JavaAnnotationArgumentImpl;

public class JavaAnnotationArgumentBuilder<P> extends AbstractBuilder<JavaAnnotationBuilder<P>, JavaAnnotationArgument>{

	private String name = "value";
	private Object value;
	
	public JavaAnnotationArgumentBuilder(JavaAnnotationBuilder<P> parent) {
		super(parent);
	}
	
	public JavaAnnotationArgumentBuilder<P> withName(String name) {
		this.name = name;
		return this;
	}

	public JavaAnnotationArgumentBuilder<P> withValue(Object value) {
		this.value = value;
		return this;
	}
	
	public JavaAnnotationArgumentBuilder<P> withStringValue(String value) {
		if (value == null) {
			value = "";
		}
		this.value = "\"" + StringEscapeUtils.escapeJava(value) + "\"";
		return this;
	}
	
	@Override
	public JavaAnnotationArgument build() {
		JavaAnnotationArgument a = new JavaAnnotationArgumentImpl();
		a.setName(name);
		a.setValue(value);
		return a;
	}

}
