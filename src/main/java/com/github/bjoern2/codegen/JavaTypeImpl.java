package com.github.bjoern2.codegen;

import java.util.ArrayList;
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
	public void setArray(int level) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public JavaTypeImpl clone() {
		return ObjectUtils.clone(this);
	}

}
