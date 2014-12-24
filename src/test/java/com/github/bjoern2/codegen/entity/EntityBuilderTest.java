package com.github.bjoern2.codegen.entity;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.Test;

import com.github.bjoern2.codegen.entity.xml.Entities;

public class EntityBuilderTest {

	@Test
	public void test() throws Throwable {
		
		JAXBContext context = JAXBContext.newInstance(Entities.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Entities root = (Entities)unmarshaller.unmarshal(getClass().getResourceAsStream("Persons.xml"));
		
		String basePath = FileUtils.getTempDirectoryPath();
		String basePackage = "com.github.bjoern2.test";

		EntityBuilder builder = new EntityBuilder();
		builder.build(basePath + "/src/main/java", basePackage, root);
	}

}
