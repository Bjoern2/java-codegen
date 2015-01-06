package com.github.bjoern2.codegen.builder;

import com.github.bjoern2.codegen.JavaMethodParameter;
import com.github.bjoern2.codegen.JavaMethodParameterImpl;

public class JavaMethodParameterBuilder extends AbstractBuilder<JavaMethodBuilder, JavaMethodParameter> {

	private String name;
	private JavaTypeBuilder<JavaMethodParameterBuilder> type;
	
	public JavaMethodParameterBuilder(JavaMethodBuilder parent) {
		super(parent);
	}

	public JavaMethodParameterBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public JavaMethodParameterBuilder withType(String type) {
		this.type = new JavaTypeBuilder<JavaMethodParameterBuilder>(this);
		this.type.withName(type);
		return this;
	}
	
	@Override
	public JavaMethodParameter build() {
		JavaMethodParameter p = new JavaMethodParameterImpl();
		p.setName(name);
		p.setType(type.build());
		return p;
	}

}
