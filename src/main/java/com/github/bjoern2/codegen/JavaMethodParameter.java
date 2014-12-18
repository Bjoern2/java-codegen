package com.github.bjoern2.codegen;


public interface JavaMethodParameter extends GeneratesCode {

	String getName();

	void setName(String name);

	boolean isFinal();
	
	void setFinal(boolean _final);

	JavaType getType();
	
	void setType(JavaType type);

}