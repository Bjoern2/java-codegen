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

public class JavaMethodImpl implements JavaMethod {

	private ClassType type;
	private JavaAccessType accessType;
	private boolean _static;
	private boolean _final;
	private boolean _synchronized;
	private boolean _abstract;
	private JavaType returnType;
	private String name;
	private List<JavaMethodParameter> parameters;
	private List<JavaType> _exceptions;
	private String body;
	private String comment;
	
	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setAbstract(boolean _abstract) {
		this._abstract = _abstract;
	}

	@Override
	public void setSynchronized(boolean s) {
		this._synchronized = s;
	}

	@Override
	public void setAccessType(JavaAccessType type) {
		this.accessType = type;
	}

	@Override
	public void setStatic(boolean s) {
		this._static = s;
	}

	@Override
	public void setFinal(boolean f) {
		this._final = f;
	}

	@Override
	public void setParameters(List<JavaMethodParameter> parameters) {
		this.parameters = parameters;
		
	}

	@Override
	public void setExceptions(List<JavaType> _exceptions) {
		this._exceptions = _exceptions;
		
	}
	
	@Override
	public void write(int tabs, Generator writer) {
		
		if (comment != null && !comment.isEmpty()) {
			String[] commentLines = comment.split("\\r?\\n");
			writer.tab(tabs).write("/**").lineBreak();
			for (String commentLine : commentLines) {
				writer.tab(tabs).write(" * ").write(commentLine).lineBreak();
			}
			writer.tab(tabs).write(" */").lineBreak();
		}
		
		writer.tab(tabs);
		if (accessType != null && accessType != JavaAccessType.PACKAGE && type == ClassType.CLASS) {
			writer.write(accessType.name().toLowerCase());
			writer.write(" ");
		}
		
		if (_final && type == ClassType.CLASS) {
			writer.write("final");
			writer.write(" ");
		}
		
		if (_static && type == ClassType.CLASS) {
			writer.write("static");
			writer.write(" ");
		}
		
		if (_abstract && type == ClassType.CLASS) {
			writer.write("abstract");
			writer.write(" ");
		}
		
		if (_synchronized && type == ClassType.CLASS) {
			writer.write("synchronized");
			writer.write(" ");
		}
		
		returnType.write(0, writer);
		writer.write(" ");
		
		writer.write(name);
		
		writer.write("(");
		
		if (parameters != null && !parameters.isEmpty()) {
			for (int i = 0; i < parameters.size(); i++) {
				if (i > 0) {
					writer.write(", ");
				}
				JavaMethodParameter parameter = parameters.get(i);
				parameter.write(0, writer);
			}
		}
		
		writer.write(")");
		
		if (_exceptions != null && !_exceptions.isEmpty()) {
			writer.write(" throws ");
			for (int i = 0; i < _exceptions.size(); i++) {
				if (i > 0) {
					writer.write(", ");
				}
				JavaType ex = _exceptions.get(i);
				ex.write(0, writer);
			}
		}
		
		if (_abstract || type == ClassType.INTERFACE) {
			writer.write(";");
		} else {
			writer.write(" {").lineBreak();
			if (body != null && !body.isEmpty()) {
				writer.tab(tabs + 1).write(body).lineBreak();
			}
			writer.tab(tabs).write("}").lineBreak();
		}
	}

	@Override
	public void setReturnType(JavaType type) {
		this.returnType = type;
		
	}

	@Override
	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public JavaType getReturnType() {
		return returnType;
	}

	@Override
	public List<JavaMethodParameter> getParameters() {
		return parameters;
	}

	@Override
	public List<JavaType> getExceptions() {
		return _exceptions;
	}

	@Override
	public ClassType getType() {
		return type;
	}

	@Override
	public void setType(ClassType type) {
		this.type = type;
		
	}

	@Override
	public String getBody() {
		return body;
	}

	@Override
	public String getComment() {
		return comment;
	}

	@Override
	public void setComment(String comment) {
		this.comment = comment;
	}

}
