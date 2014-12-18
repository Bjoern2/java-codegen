package com.github.bjoern2.codegen.builder;

import com.github.bjoern2.codegen.JavaField;
import com.github.bjoern2.codegen.JavaFieldImpl;
import com.github.bjoern2.codegen.JavaGetterMethodImpl;
import com.github.bjoern2.codegen.JavaMethod;
import com.github.bjoern2.codegen.JavaSetterMethodImpl;
import com.github.bjoern2.codegen.JavaType;

public class PojoPropertyBuilder {

	private JavaType type;
	private String name;
	
	public PojoPropertyBuilder name(String name) {
		this.name = name;
		return this;
	}
	
	public PojoPropertyBuilder type(JavaType type) {
		this.type = type;
		return this;
	}
	
	public JavaField buildField() {
		return new JavaFieldImpl(type, name);
	}
	
	public JavaMethod buildGetter() {
		return new JavaGetterMethodImpl(type, name);
	}
	
	public JavaMethod buildSetter() {
		return new JavaSetterMethodImpl(type, name);
	}
	
}
