package com.github.bjoern2.codegen;

import java.util.List;

public interface JavaAnnotation extends GeneratesCode {

	String getName();
	
	void setName(String name);
	
	List<JavaAnnotationArgument> getArguments();
	
	void setArguments(List<JavaAnnotationArgument> arguments);
}
