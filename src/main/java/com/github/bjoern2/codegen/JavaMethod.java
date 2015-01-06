/*
 * Copyright 2015 Björn Schmitz
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.bjoern2.codegen;

import java.util.List;

public interface JavaMethod extends GeneratesCode {

	List<JavaAnnotation> getAnnotations();
	
	void setAnnotations(List<JavaAnnotation> annotations);
	
	ClassType getType();
	
	void setType(ClassType type);
	
	String getName();
	
	void setName(String name);
	
	boolean isAbstract();
	
	void setAbstract(boolean a);
	
	boolean isSynchronized();
	
	void setSynchronized(boolean s);
	
	boolean isStatic();
	
	void setStatic(boolean s);
	
	boolean isFinal();
	
	void setFinal(boolean f);
	
	boolean isStrictfp();
	
	void setStrictfp(boolean _strictfp);
	
	boolean isNative();
	
	void setNative(boolean _native);
	
	void setAccessType(JavaAccessType type);
	
	JavaType getReturnType();
	
	void setReturnType(JavaType type);
	
	List<JavaMethodParameter> getParameters();
	
	void setParameters(List<JavaMethodParameter> parameters);
	
	List<JavaType> getExceptions();
	
	void setExceptions(List<JavaType> _exceptions);
	
	String getBody();
	
	void setBody(String body);
	
	String getComment();
	
	void setComment(String comment);
	
}
