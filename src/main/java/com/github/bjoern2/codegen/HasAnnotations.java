package com.github.bjoern2.codegen;

import java.util.List;

public interface HasAnnotations {

	List<JavaAnnotation> getAnnotations();
	
	void setAnnotations(List<JavaAnnotation> annotations);
	
}
