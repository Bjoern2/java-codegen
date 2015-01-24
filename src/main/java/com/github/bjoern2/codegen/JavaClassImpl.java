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

public class JavaClassImpl implements JavaClass {
	
	private String comment;
	
	private List<JavaAnnotation> annotations;
	private ClassType type = ClassType.CLASS;
	private JavaAccessType accessType = JavaAccessType.PUBLIC;
	private String name;
	private boolean _final = false;
	private boolean _abstract = false;
	private boolean _strictfp = false;
	
	private JavaType extendsFrom;
	private List<JavaType> implementsFrom;
	
	private List<JavaField> fields;
	private List<JavaMethod> methods;
	
	@Override
	public void write(int tabs, Generator g) {
		// /** ... */
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
		
		// public class Foo {
		g.tab(tabs);
		if (accessType != null && accessType != JavaAccessType.PACKAGE) {
			g.write(accessType.name().toLowerCase());
			g.write(" ");
		}
		
		if (_abstract && type == ClassType.CLASS) {
			g.write("abstract ");
		}
		
//		if (_static && type == ClassType.CLASS) {
//			g.write("static ");
//		}
		
		if (_final && type == ClassType.CLASS) {
			g.write("final ");
		}
		
		if (_strictfp && type == ClassType.CLASS) {
			g.write("strictfp ");
		}
		
		if (type == ClassType.INTERFACE) {
			g.write("interface ");
		} else if (type == ClassType.ANNOTATION) {
			g.write("@interface ");
		} else {
			g.write("class ");
		}
		
		g.write(name + " {");
		g.lineBreak();
		g.lineBreak();
		
		if (fields != null) {
			for (JavaField member : fields) {
				member.write(tabs + 1, g);
				g.lineBreak();
			}
		}
		
		if (methods != null) {
			for (JavaMethod method : methods) {
				method.write(tabs + 1, g);
				g.lineBreak();
			}
		}
		
		g.tab(tabs).write("}").lineBreak();

	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setAccessType(JavaAccessType type) {
		this.accessType = type;
	}

	@Override
	public void setAbstract(boolean _abstract) {
		this._abstract = _abstract;
	}

	@Override
	public void setFinal(boolean _final) {
		this._final = _final;
	}

	@Override
	public void setMethods(List<JavaMethod> methods) {
		this.methods = methods;
		
	}

	@Override
	public void setFields(List<JavaField> members) {
		this.fields = members;
		
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public JavaAccessType getAccessType() {
		return accessType;
	}

	@Override
	public boolean isAbstract() {
		return _abstract;
	}

	@Override
	public boolean isFinal() {
		return _final;
	}

	@Override
	public List<JavaMethod> getMethods() {
		return methods;
	}

	@Override
	public List<JavaField> getFields() {
		return fields;
	}

	@Override
	public JavaType getExtendsFrom() {
		return extendsFrom;
	}

	@Override
	public void setExtendsFrom(JavaType type) {
		this.extendsFrom = type;
	}

	@Override
	public List<JavaType> getImplementsFrom() {
		return implementsFrom;
	}

	@Override
	public void setImplementsFrom(List<JavaType> type) {
		this.implementsFrom = type;
		
	}

	@Override
	public void setType(ClassType type) {
		this.type = type;
		
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
	public ClassType getType() {
		return type;
	}

	@Override
	public List<JavaAnnotation> getAnnotations() {
		return annotations;
	}

	@Override
	public void setAnnotations(List<JavaAnnotation> annotations) {
		this.annotations = annotations;
	}

	@Override
	public boolean isStrictfp() {
		return _strictfp;
	}

	@Override
	public void setStrictfp(boolean _strictfp) {
		this._strictfp = _strictfp;
	}

}
