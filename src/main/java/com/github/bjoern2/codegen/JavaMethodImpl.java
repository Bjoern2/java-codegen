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

	private List<JavaAnnotation> annotations;
	private ClassType type = ClassType.CLASS;
	private JavaAccessType accessType;
	private boolean _static;
	private boolean _final;
	private boolean _synchronized;
	private boolean _abstract;
	private JavaType returnType;
	private String name;
	private List<JavaMethodParameter> parameters;
	private List<JavaType> _exceptions;
	private String body = "// TODO Auto-generated method stub";
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
	public void write(int tabs, Generator g) {
		
		if (comment != null && !comment.isEmpty()) {
			String[] commentLines = comment.split("\\r?\\n");
			g.tab(tabs).write("/**").lineBreak();
			for (String commentLine : commentLines) {
				g.tab(tabs).write(" * ").write(commentLine).lineBreak();
			}
			g.tab(tabs).write(" */").lineBreak();
		}
		
		if (annotations != null) {
			for (JavaAnnotation annotation : annotations) {
				annotation.write(tabs, g);
				g.lineBreak();
			}
		}
		
		g.tab(tabs);
		if (accessType != null && accessType != JavaAccessType.PACKAGE && type == ClassType.CLASS) {
			g.write(accessType.name().toLowerCase());
			g.write(" ");
		}
		
		if (_final && type == ClassType.CLASS) {
			g.write("final");
			g.write(" ");
		}
		
		if (_static && type == ClassType.CLASS) {
			g.write("static");
			g.write(" ");
		}
		
		if (_abstract && type == ClassType.CLASS) {
			g.write("abstract");
			g.write(" ");
		}
		
		if (_synchronized && type == ClassType.CLASS) {
			g.write("synchronized");
			g.write(" ");
		}
		
		returnType.write(0, g);
		g.write(" ");
		
		g.write(name);
		
		g.write("(");
		
		if (parameters != null && !parameters.isEmpty()) {
			for (int i = 0; i < parameters.size(); i++) {
				if (i > 0) {
					g.write(", ");
				}
				JavaMethodParameter parameter = parameters.get(i);
				parameter.write(0, g);
			}
		}
		
		g.write(")");
		
		if (_exceptions != null && !_exceptions.isEmpty()) {
			g.write(" throws ");
			for (int i = 0; i < _exceptions.size(); i++) {
				if (i > 0) {
					g.write(", ");
				}
				JavaType ex = _exceptions.get(i);
				ex.write(0, g);
			}
		}
		
		if (_abstract || type == ClassType.INTERFACE) {
			g.write(";");
		} else {
			g.write(" {").lineBreak();
			if (body != null && !body.isEmpty()) {
				g.tab(tabs + 1).write(body).lineBreak();
			}
			g.tab(tabs).write("}").lineBreak();
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

	@Override
	public List<JavaAnnotation> getAnnotations() {
		return annotations;
	}

	@Override
	public void setAnnotations(List<JavaAnnotation> annotations) {
		this.annotations = annotations;
	}

}
