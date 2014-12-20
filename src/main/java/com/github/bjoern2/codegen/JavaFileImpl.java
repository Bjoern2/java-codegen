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

import org.apache.commons.lang3.StringUtils;


public class JavaFileImpl implements JavaFile {


	private String comment;
	
	private String _package;
	
	private List<JavaType> imports;
	
	private JavaClass clazz;

	@Override
	public void setComment(String comment) {
		this.comment = comment;
		
	}
	
	@Override
	public void write(int tabs, Generator writer) {
		if (comment != null && !comment.isEmpty()) {
			String[] commentLines = comment.split("\\r?\\n");
			writer.tab(tabs).write("/*").lineBreak();
			for (String commentLine : commentLines) {
				writer.tab(tabs).write(" * ").write(commentLine).lineBreak();
			}
			writer.tab(tabs).write(" */").lineBreak();
		}
		
		if (StringUtils.isNoneBlank(_package)) {
			writer.tab(tabs).write("package " + _package + ";").lineBreak();
			writer.lineBreak();
		}
		
		if (imports != null) {
			for (JavaType type : imports) {
				writer.tab(tabs).write("import " + type.getName() + ";").lineBreak();
			}
			writer.lineBreak();
		}
		
		if (clazz != null) {
			clazz.write(tabs, writer);
			writer.lineBreak();
		}
		
	}

	@Override
	public String getPackage() {
		return _package;
	}

	@Override
	public void setPackage(String _package) {
		this._package = _package;
	}

	@Override
	public List<JavaType> getImports() {
		return imports;
	}

	@Override
	public void setImports(List<JavaType> imports) {
		this.imports = imports;
	}

	@Override
	public JavaClass getClazz() {
		return clazz;
	}

	@Override
	public void setClazz(JavaClass clazz) {
		this.clazz = clazz;
	}

	@Override
	public String getComment() {
		return comment;
	}

}
