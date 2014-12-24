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

public class JavaFieldImpl implements JavaField {

	private List<JavaAnnotation> annotations;
	private JavaAccessType accessType = JavaAccessType.PRIVATE;
	private boolean _volatile;
	private boolean _final;
	private boolean _static;
	private JavaType type;
	private String name;
	
	public JavaFieldImpl() {
		super();
	}
	
	public JavaFieldImpl(JavaType type, String name) {
		super();
		this.type = type;
		this.name = name;
	}

	@Override
	public void setFinal(boolean _final) {
		this._final = _final;
	}

	@Override
	public void setVolatile(boolean _final) {
		this._final = _final;
	}

	@Override
	public void setType(JavaType type) {
		this.type = type;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void write(int tabs, Generator g) {
		if (annotations != null) {
			for (JavaAnnotation annotation : annotations) {
				annotation.write(tabs, g);
				g.lineBreak();
			}
		}
		
		g.tab(tabs);
		if (accessType != null && accessType != JavaAccessType.PACKAGE) {
			g.write(accessType.name().toLowerCase() + " ");
		}
		type.write(0, g);
		g.write(" ");
		g.write(name);
		g.write(";");
		g.lineBreak();
	}

	@Override
	public boolean isFinal() {
		return _final;
	}

	@Override
	public boolean isVolatile() {
		return _volatile;
	}

	@Override
	public JavaType getType() {
		return type;
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
	public void setAccessType(JavaAccessType accessType) {
		this.accessType = accessType;
	}

	@Override
	public boolean isStatic() {
		return _static;
	}

	@Override
	public void setStatic(boolean _static) {
		this._static = _static;
	}

	@Override
	public List<JavaAnnotation> getAnnotations() {
		// TODO Auto-generated method stub
		return annotations;
	}

	@Override
	public void setAnnotations(List<JavaAnnotation> annotations) {
		this.annotations = annotations;
		
	}

}
