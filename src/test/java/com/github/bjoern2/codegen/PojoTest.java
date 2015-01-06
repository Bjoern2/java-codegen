package com.github.bjoern2.codegen;

import java.util.Arrays;

import org.junit.Test;

public class PojoTest {

	@Test
	public void test() {
		JavaFile file = new JavaFileImpl();
		file.setPackage("com.github.bjoern2.test");
		
		JavaClass pojo = new JavaClassImpl();
		pojo.setName("Address");
		file.setClazz(pojo);
		
	
		JavaField id = new JavaFieldImpl(JavaTypeImpl._long(), "id");
		JavaField firstname = new JavaFieldImpl(JavaTypeImpl._String(), "firstname");
		JavaField lastname = new JavaFieldImpl(JavaTypeImpl._String(), "lastname");
		pojo.setFields(Arrays.asList(id, firstname, lastname));
		
		JavaMethod getId = new JavaGetterMethodImpl(id);
		JavaMethod setId = new JavaSetterMethodImpl(id);
		JavaMethod getFirstname = new JavaGetterMethodImpl(firstname);
		JavaMethod setFirstname = new JavaSetterMethodImpl(firstname);
		JavaMethod getLastname = new JavaGetterMethodImpl(lastname);
		JavaMethod setLastname = new JavaSetterMethodImpl(lastname);
		pojo.setMethods(Arrays.asList(getId, setId, getFirstname, setFirstname, getLastname, setLastname));
		
		

		
		StringGenerator writer = new StringGenerator();
		
		file.write(0, writer);
		
		System.out.println(writer.toString());
	}
	
}
