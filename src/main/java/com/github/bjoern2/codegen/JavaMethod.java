package com.github.bjoern2.codegen;

import java.util.List;

public interface JavaMethod extends GeneratesCode {

	void setName(String name);
	
	void setAbstract(boolean a);
	
	void setSynchronized(boolean s);
	
	void setStatic(boolean s);
	
	void setFinal(boolean f);
	
	void setAccessType(JavaAccessType type);
	
	void setReturnType(JavaType type);
	
	void setParameters(List<JavaMethodParameter> parameters);
	
	void setExceptions(List<JavaType> _exceptions);
	
	void setBody(String body);
	
}
