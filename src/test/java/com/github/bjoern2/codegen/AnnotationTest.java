package com.github.bjoern2.codegen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.junit.Test;

import com.github.bjoern2.codegen.util.ImportOrganizer;

public class AnnotationTest {

	@Test
	public void test() {
		JavaFile file = new JavaFileImpl();
		file.setPackage("com.github.bjoern2.test");
		
		JavaClass pojo = new JavaClassImpl();
		pojo.setName("Address");
		file.setDefinition(pojo);
		
		
		JavaAnnotation anRootElement = new JavaAnnotationImpl();
		anRootElement.setName(XmlRootElement.class.getName());
		anRootElement.setArguments(new ArrayList<JavaAnnotationArgument>());
		anRootElement.getArguments().add(new JavaAnnotationArgumentImpl("name", "\"address\""));
		
		JavaAnnotation anFoo = new JavaAnnotationImpl("com.bjoern2.test.Foo");
		anFoo.setArguments(new ArrayList<JavaAnnotationArgument>());
		
		List<Object> anBars = new ArrayList<Object>();
		anBars.add(new JavaAnnotationImpl("com.github.bjoern2.test.Bar"));
		anBars.add(new JavaAnnotationImpl("com.bjoern2.test.Bar"));
		anFoo.getArguments().add(new JavaAnnotationArgumentImpl("bars", anBars));
		
		pojo.setAnnotations(Arrays.asList(anRootElement, anFoo));
	
		JavaField id = new JavaFieldImpl(JavaTypeImpl._long(), "id");
		JavaAnnotation anId = new JavaAnnotationImpl(XmlElement.class.getName());
		id.setAnnotations(Arrays.asList(anId));
		
		
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
		
		
		ImportOrganizer organizer = new ImportOrganizer();
		organizer.organize(file);

		
		StringGenerator writer = new StringGenerator();
		
		file.write(0, writer);
		
		System.out.println(writer.toString());
	}
	
}
