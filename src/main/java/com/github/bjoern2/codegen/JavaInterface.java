package com.github.bjoern2.codegen;

import java.util.List;

public interface JavaInterface extends HasAnnotations {
	
	JavaAccessType getAccessType();
	
	void setAccessType(JavaAccessType type);
	
	String getName();
	
	void setName(String name);
	
	List<JavaType> getExtendsFrom();
	
	void setExtendsFrom(List<JavaType> type);
	
	List<JavaMethod> getMethods();
	
	void setMethods(List<JavaMethod> methods);
	
	List<JavaField> getFields();
	
	void setFields(List<JavaField> fields);
	
	String getComment();
	
	void setComment(String comment);
	
}
