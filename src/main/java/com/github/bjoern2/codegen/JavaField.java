package com.github.bjoern2.codegen;

public interface JavaField extends GeneratesCode {

	void setFinal(boolean _final);

	boolean isFinal();

	void setVolatile(boolean _final);

	boolean isVolatile();

	void setType(JavaType type);

	JavaType getType();

	void setName(String name);

	String getName();

	JavaAccessType getAccessType();

	void setAccessType(JavaAccessType accessType);
	
	boolean isStatic();
	
	void setStatic(boolean _static);

}
