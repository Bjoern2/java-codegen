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

import org.apache.commons.lang3.ObjectUtils;

public class JavaTypeImpl implements JavaType {

	private String name;
	private List<JavaType> generics;
	private int arrays;

	
	public JavaTypeImpl() {
		super();
	}
	
	public JavaTypeImpl(String name) {
		super();
		this.name = name;
	}
	
	public JavaTypeImpl(String name, List<JavaType> generics) {
		super();
		this.name = name;
		this.generics = generics;
	}
	
	public JavaTypeImpl(String name, int arrays) {
		super();
		this.name = name;
		this.arrays = arrays;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public List<JavaType> getGenerics() {
		return generics;
	}

	@Override
	public void setGenerics(List<JavaType> generics) {
		this.generics = generics;
	}

	@Override
	public void write(int tabs, Generator writer) {
		writer.tab(tabs).write(name);
		if ((generics != null) && (!generics.isEmpty())) {
			writer.write("<");
			for (JavaType t : generics) {
				t.write(tabs, writer);
			}
			writer.write(">");
		}
		
		if (arrays > 0) {
			for (int i = 0; i < arrays; i++) {
				writer.write("[]");
			}
		}
		
	}

	@Override
	public void setArray(int count) {
		this.arrays = count;
	}
	
	public static JavaTypeImpl _void() {
		return new JavaTypeImpl("void");
	}
	
	public static JavaTypeImpl _int() {
		return new JavaTypeImpl("int");
	}
	
	public static JavaTypeImpl _long() {
		return new JavaTypeImpl("long");
	}
	
	public static JavaTypeImpl _String() {
		return new JavaTypeImpl("String");
	}

	@Override
	public int getArray() {
		return arrays;
	}

	@Override
	public JavaTypeImpl clone() {
		return ObjectUtils.clone(this);
	}


}
