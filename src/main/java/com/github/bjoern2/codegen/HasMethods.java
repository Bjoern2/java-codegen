package com.github.bjoern2.codegen;

import java.util.List;

public interface HasMethods {

	List<JavaMethod> getMethods();
	
	void setMethods(List<JavaMethod> methods);
	
}
