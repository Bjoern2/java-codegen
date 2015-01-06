package com.github.bjoern2.codegen;


public interface JavaAnnotationArgument {

	String getName();
	
	void setName(String name);
	
	void setValue(Object value);
	
	Object getValue();
	
}
