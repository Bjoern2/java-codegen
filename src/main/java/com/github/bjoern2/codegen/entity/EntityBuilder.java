package com.github.bjoern2.codegen.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.github.bjoern2.codegen.JavaClass;
import com.github.bjoern2.codegen.JavaClassImpl;
import com.github.bjoern2.codegen.JavaField;
import com.github.bjoern2.codegen.JavaFile;
import com.github.bjoern2.codegen.JavaFileImpl;
import com.github.bjoern2.codegen.JavaFileWriter;
import com.github.bjoern2.codegen.JavaMethod;
import com.github.bjoern2.codegen.JavaTypeImpl;
import com.github.bjoern2.codegen.builder.PojoPropertyBuilder;
import com.github.bjoern2.codegen.entity.xml.Entities;
import com.github.bjoern2.codegen.entity.xml.Entity;
import com.github.bjoern2.codegen.entity.xml.Field;
import com.github.bjoern2.codegen.util.ImportOrganizer;

public class EntityBuilder {

	public void build(String basePath, String basePackage, Entities root) throws IOException {
		JavaFileWriter writer = new JavaFileWriter(basePath, basePackage);
		
		List<Entity> entities = root.getEntities();
		for (Entity entity : entities) {
			JavaFile file = new JavaFileImpl();
			JavaClass clazz = new JavaClassImpl();
			clazz.setName(StringUtils.capitalize(entity.getName()));
			
			final List<JavaField> fields = new ArrayList<JavaField>();
			final List<JavaMethod> methods = new ArrayList<JavaMethod>();
			for (Field field : entity.getFields()) {
				PojoPropertyBuilder b = new PojoPropertyBuilder();
				b.name(field.getName());
				b.type(new JavaTypeImpl(field.getType()));
				fields.add(b.buildField());
				methods.add(b.buildGetter());
				methods.add(b.buildSetter());
			}
			clazz.setFields(fields);
			clazz.setMethods(methods);
			
			file.setClazz(clazz);
			
			ImportOrganizer organizer = new ImportOrganizer();
			organizer.organize(file);
			
			writer.write(file);
			
		}
	}
	
}
