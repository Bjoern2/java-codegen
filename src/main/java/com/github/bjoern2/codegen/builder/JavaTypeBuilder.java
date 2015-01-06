package com.github.bjoern2.codegen.builder;

import com.github.bjoern2.codegen.JavaType;
import com.github.bjoern2.codegen.JavaTypeImpl;

public class JavaTypeBuilder<T> extends AbstractBuilder<T, JavaType> {

	private String name;
	
	public JavaTypeBuilder(T parent) {
		super(parent);
	}
	
	public JavaTypeBuilder<T> withName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public JavaType build() {
		JavaType t = new JavaTypeImpl();
		t.setName(name);
		return t;
	}

}
