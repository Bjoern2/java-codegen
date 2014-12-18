package com.github.bjoern2.codegen;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class JavaFileWriter {

	private String basePath;
	private String basePackage;
	
	public JavaFileWriter(String basePath, String basePackage) {
		super();
		this.basePath = basePath;
		this.basePackage = basePackage;
	}

	public void write(JavaFile javaFile) throws IOException {
		javaFile.setPackage(basePackage);
		
		String packagePath = basePackage.replace(".", "/");
		String filename = javaFile.getClazz().getName() + ".java";
		
		String pathname = FilenameUtils.concat(basePath, packagePath);
		File dir = new File(pathname);
		
		String fullpathname = FilenameUtils.concat(pathname, filename);
		File file = new File(fullpathname);
		
		dir.mkdirs();
		
		StringGenerator generator = new StringGenerator();
		javaFile.write(0, generator);
		
		FileUtils.write(file, generator.toString());
	}
	
}
