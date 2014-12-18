package com.github.bjoern2.codegen;

import java.util.List;

public interface JavaFile extends GeneratesCode {

	void setComment(String comment);
	
	String getPackage();

	void setPackage(String _package);

	List<JavaType> getImports();

	void setImports(List<JavaType> imports);

	JavaClass getClazz();

	void setClazz(JavaClass clazz);

	String getComment();
	
}
