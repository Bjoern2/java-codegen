package com.github.bjoern2.codegen;

import java.util.List;

public interface JavaClass extends GeneratesCode {

	JavaAccessType getAccessType();
	
	void setAccessType(JavaAccessType type);
	
	boolean isAbstract();
	
	void setAbstract(boolean _abstract);
	
	boolean isFinal();
	
	void setFinal(boolean _final);
	
	String getName();
	
	void setName(String name);
	
	String getExtendsFrom();
	
	void setExtendsFrom(String clazzName);
	
	
	void addImplementsFrom(String interfaceName);
	
	List<JavaMethod> getMethods();
	
	void setMethods(List<JavaMethod> methods);
	
	List<JavaField> getFields();
	
	void setFields(List<JavaField> fields);
	 
}
