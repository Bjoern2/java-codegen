package com.github.bjoern2.codegen.builder;

import java.util.ArrayList;
import java.util.List;

import com.github.bjoern2.codegen.JavaAnnotation;
import com.github.bjoern2.codegen.JavaAnnotationArgument;
import com.github.bjoern2.codegen.JavaAnnotationImpl;


public class JavaAnnotationBuilder<P> extends AbstractBuilder<P, JavaAnnotation> {
	
	private String name;
	private List<JavaAnnotationArgumentBuilder<P>> arguments = new ArrayList<JavaAnnotationArgumentBuilder<P>>();
	
	public JavaAnnotationBuilder(P parent) {
		super(parent);
	}

	public JavaAnnotationBuilder<P> withName(String name) {
		this.name = name;
		return this;
	}
	
	public JavaAnnotationArgumentBuilder<P> beginArgument() {
		JavaAnnotationArgumentBuilder<P> a = new JavaAnnotationArgumentBuilder<P>(this);
		arguments.add(a);
		return a;
	}

	@Override
	public JavaAnnotation build() {
		JavaAnnotation a = new JavaAnnotationImpl();
		a.setName(name);
		
		List<JavaAnnotationArgument> args = new ArrayList<JavaAnnotationArgument>();
		for (JavaAnnotationArgumentBuilder<P> b : arguments) {
			args.add(b.build());
		}
		a.setArguments(args);
		return a;
	}
	
}
