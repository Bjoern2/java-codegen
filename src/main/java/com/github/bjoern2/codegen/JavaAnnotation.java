package com.github.bjoern2.codegen;

import java.util.Map;

public interface JavaAnnotation extends GeneratesCode {

	String getName();
	
	void setName(String name);
	
	Map<String, Object> getValues();
	
	void setValues(Map<String, Object> values);
}
