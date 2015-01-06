package com.github.bjoern2.codegen.builder;

import java.util.ArrayList;
import java.util.List;

import com.github.bjoern2.codegen.JavaAccessType;
import com.github.bjoern2.codegen.JavaMethod;
import com.github.bjoern2.codegen.JavaMethodImpl;
import com.github.bjoern2.codegen.JavaMethodParameter;

public class JavaMethodBuilder extends AbstractBuilder<JavaClassBuilder, JavaMethod> {

	private JavaAccessType accessType;
	private String name;
	private JavaTypeBuilder<JavaMethodBuilder> returnType;
	private List<JavaMethodParameterBuilder> parameters = new ArrayList<JavaMethodParameterBuilder>();
	
	public JavaMethodBuilder(JavaClassBuilder parent) {
		super(parent);
	}
	
	public JavaMethodBuilder withReturnType(String type) {
		returnType = new JavaTypeBuilder<JavaMethodBuilder>(this);
		returnType.withName(type);
		return this;
	}
	
	public JavaMethodBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public JavaMethodBuilder withAccessType(JavaAccessType type) {
		this.accessType = type;
		return this;
	}
	
	public JavaMethodParameterBuilder beginParameter() {
		JavaMethodParameterBuilder p = new JavaMethodParameterBuilder(this);
		parameters.add(p);
		return p;
	}

	@Override
	public JavaMethod build() {
		JavaMethod m = new JavaMethodImpl();
		m.setName(name);
		m.setAccessType(accessType);
		m.setReturnType(returnType.build());
		if (!parameters.isEmpty()) {
			m.setParameters(new ArrayList<JavaMethodParameter>());
			for (JavaMethodParameterBuilder b : parameters) {
				m.getParameters().add(b.build());
			}
		}
		return m;
	}

}
