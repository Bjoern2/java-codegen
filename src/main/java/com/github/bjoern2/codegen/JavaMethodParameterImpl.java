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


public class JavaMethodParameterImpl implements JavaMethodParameter {

	private List<JavaAnnotation> annotations;
	private boolean _final = false;
	private JavaType type;
	private String name;
	
	public JavaMethodParameterImpl() {
		super();
	}
	
	public JavaMethodParameterImpl(JavaType type, String name) {
		super();
		this.type = type;
		this.name = name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void write(int tabs, Generator g) {
		g.tab(tabs);
		
		if (annotations != null) {
			for (JavaAnnotation annotation : annotations) {
				annotation.write(tabs, g);
				g.write(" ");
			}
		}
		
		if (_final) {
			g.write("final ");
		}
		type.write(0, g);
		g.write(" ");
		g.write(name);
	}

	@Override
	public boolean isFinal() {
		return _final;
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
	public void setType(JavaType type) {
		this.type = type;
	}

	@Override
	public void setFinal(boolean _final) {
		this._final = _final;
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
