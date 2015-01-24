package com.github.bjoern2.codegen.entity;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;

import com.github.bjoern2.codegen.JavaFile;
import com.github.bjoern2.codegen.JavaFileWriter;
import com.github.bjoern2.codegen.builder.JavaClassBuilder;
import com.github.bjoern2.codegen.builder.JavaFileBuilder;
import com.github.bjoern2.codegen.entity.xml.Entities;
import com.github.bjoern2.codegen.entity.xml.Entity;
import com.github.bjoern2.codegen.entity.xml.Field;
import com.github.bjoern2.codegen.util.ImportOrganizer;

public class SpringDataJpaBuilder {

	public class Settings {

		private String basePath;
		private String basePackage;
		private String domainPackage;
		private String repositoryPackage;
		private InputStream xml;

		public String getBasePath() {
			return basePath;
		}

		public void setBasePath(String basePath) {
			this.basePath = basePath;
		}

		public String getBasePackage() {
			return basePackage;
		}

		public void setBasePackage(String basePackage) {
			this.basePackage = basePackage;
		}

		public String getDomainPackage() {
			return domainPackage;
		}

		public void setDomainPackage(String domainPackage) {
			this.domainPackage = domainPackage;
		}

		public String getRepositoryPackage() {
			return repositoryPackage;
		}

		public void setRepositoryPackage(String repositoryPackage) {
			this.repositoryPackage = repositoryPackage;
		}

		public InputStream getXml() {
			return xml;
		}

		public void setXml(InputStream xml) {
			this.xml = xml;
		}

	}

	private Entities unmarshall(InputStream in) {
		try {
			JAXBContext context = JAXBContext.newInstance(Entities.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Entities root = (Entities) unmarshaller.unmarshal(in);
			return root;
		} catch (JAXBException ex) {
			throw new RuntimeException(ex);
		}

	}

	public void build(Settings s) throws IOException {

		String pack = s.getBasePackage();
		if (StringUtils.isNotBlank(s.getDomainPackage())) {
			pack = pack + "." + s.getDomainPackage();
		}
		
		Entities root = unmarshall(s.getXml());

		JavaFileWriter writer = new JavaFileWriter(s.getBasePath(), pack);

		List<Entity> entities = root.getEntities();
		for (Entity entity : entities) {
			JavaFileBuilder b = new JavaFileBuilder();
			JavaClassBuilder clazz = b.beginClass()
				.withName(StringUtils.capitalize(entity.getName()));
			
			for (Field field : entity.getFields()) {
				clazz.beginField()
					.withName(field.getName())
					.withType(field.getType())
					.beginAnnotation()
						.withName("Column")
						.beginArgument()
							.withName("name")
							.withStringValue(field.getColumnName());
				
				clazz.beginMethod()
					.withName("set" + StringUtils.capitalize(field.getName()))
					.withReturnType("void")
					.withBody("this." + field.getName() + " = " + field.getName() + ";")
					.beginParameter()
						.withType(field.getType())
						.withName(field.getName());
				
				clazz.beginMethod()
					.withName((StringUtils.equals("boolean", field.getType()) ? "is" : "get") + StringUtils.capitalize(field.getName()))
					.withReturnType(field.getType())
					.withBody("return " + field.getName() + ";");
	
			}

			JavaFile file = b.build();

			ImportOrganizer organizer = new ImportOrganizer();
			organizer.organize(file);

			writer.write(file);

		}
	}

}
