package com.github.bjoern2.codegen.builder;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;

import com.github.bjoern2.codegen.JavaAccessType;
import com.github.bjoern2.codegen.JavaAnnotation;
import com.github.bjoern2.codegen.JavaField;
import com.github.bjoern2.codegen.JavaFieldImpl;

public class JavaFieldBuilder extends AbstractBuilder<JavaClassBuilder, JavaField>{

	private List<JavaAnnotationBuilder<JavaFieldBuilder>> annotations = new ArrayList<JavaAnnotationBuilder<JavaFieldBuilder>>();

	
	private JavaAccessType accessType = JavaAccessType.PRIVATE;
	private boolean _static = false;
	private boolean _final = false;
	private boolean _volatile = false;
	private String name;
	
	private JavaTypeBuilder<JavaFieldBuilder> type;
	
	private Object value;
	
	public JavaFieldBuilder(JavaClassBuilder parent) {
		super(parent);
	}
	
	public JavaFieldBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public JavaFieldBuilder withType(String type) {
		this.type = new JavaTypeBuilder<JavaFieldBuilder>(this);
		this.type.withName(type);
		return this;
	}
	
	public JavaFieldBuilder withAccessType(JavaAccessType type) {
		this.accessType = type;
		return this;
	}
	
	public JavaFieldBuilder withStatic(boolean _static) {
		this._static = _static;
		return this;
	}
	
	public JavaFieldBuilder withFinal(boolean _final) {
		this._final = _final;
		return this;
	}
	
	public JavaFieldBuilder withVolatile(boolean _volatile) {
		this._volatile = _volatile;
		return this;
	}
	
	public JavaTypeBuilder<JavaFieldBuilder> beginType() {
		type = new JavaTypeBuilder<JavaFieldBuilder>(this);
		return type;
	}
	
	public JavaFieldBuilder withValue(Object value) {
		this.value = value;
		return this;
	}
	
	public JavaFieldBuilder withStringValue(String value) {
		if (value == null) {
			value = "";
		}
		this.value = "\"" + StringEscapeUtils.escapeJava(value) + "\"";
		return this;
	}
	
	public JavaAnnotationBuilder<JavaFieldBuilder> beginAnnotation() {
		JavaAnnotationBuilder<JavaFieldBuilder> b = new JavaAnnotationBuilder<JavaFieldBuilder>(this);
		annotations.add(b);
		return b;
	}

	@Override
	public JavaField build() {
		JavaField f = new JavaFieldImpl();
		f.setAnnotations(new ArrayList<JavaAnnotation>());
		if (annotations != null) {
			for (JavaAnnotationBuilder<JavaFieldBuilder> b : annotations) {
				f.getAnnotations().add(b.build());
			}
		}
		
		f.setName(name);
		f.setAccessType(accessType);
		f.setFinal(_final);
		f.setStatic(_static);
		f.setVolatile(_volatile);
		if (type != null) {
			f.setType(type.build());
		}
		f.setValue(value);
		return f;
	}

	
}
