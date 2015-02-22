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

public interface JavaClass extends JavaDefinition, GeneratesCode, HasMethods, HasAnnotations, HasComment {

	void setType(ClassType type);
	
	ClassType getType();
	
	JavaAccessType getAccessType();
	
	void setAccessType(JavaAccessType type);
	
	boolean isAbstract();
	
	void setAbstract(boolean _abstract);
	
	boolean isFinal();
	
	void setFinal(boolean _final);
	
	boolean isStrictfp();
	
	void setStrictfp(boolean _strictfp);
	
	JavaType getExtendsFrom();
	
	void setExtendsFrom(JavaType type);
	
	List<JavaType> getImplementsFrom();
	
	void setImplementsFrom(List<JavaType> type);
	
	List<JavaConstructor> getConstructors();
	
	void setConstructors(List<JavaConstructor> constructors);
	
	List<JavaField> getFields();
	
	void setFields(List<JavaField> fields);
	 
}
