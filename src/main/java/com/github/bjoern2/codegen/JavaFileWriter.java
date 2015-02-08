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
		String filename = javaFile.getDefinition().getName() + ".java";
		
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
