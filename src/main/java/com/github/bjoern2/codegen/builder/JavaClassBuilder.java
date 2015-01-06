package com.github.bjoern2.codegen.builder;

import java.util.ArrayList;
import java.util.List;

import com.github.bjoern2.codegen.JavaAnnotation;
import com.github.bjoern2.codegen.JavaClass;
import com.github.bjoern2.codegen.JavaClassImpl;
import com.github.bjoern2.codegen.JavaField;
import com.github.bjoern2.codegen.JavaMethod;

public class JavaClassBuilder extends AbstractBuilder<JavaFileBuilder, JavaClass> {
	
	private List<JavaAnnotationBuilder<JavaClassBuilder>> annotations = new ArrayList<JavaAnnotationBuilder<JavaClassBuilder>>();

	private String name;
	
	private List<JavaFieldBuilder> fields = new ArrayList<JavaFieldBuilder>();
	
	private List<JavaMethodBuilder> methods = new ArrayList<JavaMethodBuilder>();
	
	public JavaClassBuilder(JavaFileBuilder parent) {
		super(parent);
	}

	public JavaClassBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public JavaFieldBuilder beginField() {
		JavaFieldBuilder f = new JavaFieldBuilder(this);
		fields.add(f);
		return f;
	}
	
	public JavaMethodBuilder beginMethod() {
		JavaMethodBuilder m = new JavaMethodBuilder(this);
		methods.add(m);
		return m;
	}
	
	public JavaAnnotationBuilder<JavaClassBuilder> beginAnnotation() {
		JavaAnnotationBuilder<JavaClassBuilder> b = new JavaAnnotationBuilder<JavaClassBuilder>(this);
		annotations.add(b);
		return b;
	}

	@Override
	public JavaClass build() {
		JavaClass c = new JavaClassImpl();
		c.setName(name);
		if (!annotations.isEmpty()) {
			c.setAnnotations(new ArrayList<JavaAnnotation>());
			for (JavaAnnotationBuilder<JavaClassBuilder> b : annotations) {
				c.getAnnotations().add(b.build());
			}
		}
		if (!fields.isEmpty()) {
			c.setFields(new ArrayList<JavaField>());
			for (JavaFieldBuilder b : fields) {
				c.getFields().add(b.build());
			}
		}
		
		if (!methods.isEmpty()) {
			c.setMethods(new ArrayList<JavaMethod>());
			for (JavaMethodBuilder b : methods) {
				c.getMethods().add(b.build());
			}
		}
		
		return c;
	}
	
}
