package com.github.bjoern2.codegen;

import java.util.List;

public interface JavaPackage {

	String getName();
	
	void setName(String name);
	
	List<JavaClass> getClazzes();
	
	void setClazzes(List<JavaClass> clazzes);
	
	List<JavaInterface> getInterfaces();
	
	void setInterfaces(List<JavaInterface> interfaces);
	
	List<JavaEnum> getEnums();
	
	void setEnums(List<JavaEnum> enums);
	
}
