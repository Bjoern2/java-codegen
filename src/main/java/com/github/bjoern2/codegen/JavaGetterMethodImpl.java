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

import org.apache.commons.lang3.StringUtils;

public class JavaGetterMethodImpl extends JavaMethodImpl {

	public JavaGetterMethodImpl(JavaField member) {
		this(member.getType(), member.getName());

	}
	
	public JavaGetterMethodImpl(JavaType type, String n) {
		setAccessType(JavaAccessType.PUBLIC);
		setStatic(false);
		setReturnType(type);
		String name = "";
		if (StringUtils.equals(type.getName(), "boolean")) {
			name += "is";
		} else {
			name += "get";
		}
		name += StringUtils.capitalize(n);
		
		setName(name);
//		List<JavaField> parameters = new ArrayList<JavaField>();
//		parameters.add(new JavaFieldImpl(new JavaTypeImpl("String", 1), "args"));
//		m.setParameters(parameters);
		setBody("return this." + n + ";");
	}

}
