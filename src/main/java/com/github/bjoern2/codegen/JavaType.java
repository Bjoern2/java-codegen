package com.github.bjoern2.codegen;

import java.util.List;

public interface JavaType extends GeneratesCode {

	String getName();

	void setName(String name);

	List<JavaType> getGenerics();

	void setGenerics(List<JavaType> generics);
	
	int getArray();
	
	void setArray(int level);

}