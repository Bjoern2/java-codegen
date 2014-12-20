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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class JavaSetterMethodImpl extends JavaMethodImpl {

	public JavaSetterMethodImpl(JavaField member) {
		this(member.getType(), member.getName());
	}
	
	public JavaSetterMethodImpl(JavaType type, String n) {
		super();
		setAccessType(JavaAccessType.PUBLIC);
		setStatic(false);
		setReturnType(new JavaTypeImpl("void"));
		String name = "set" + StringUtils.capitalize(n);
		setName(name);
		List<JavaMethodParameter> parameters = new ArrayList<JavaMethodParameter>();
		parameters.add(new JavaMethodParameterImpl(type, n));
		setParameters(parameters);
		setBody("this." + n + " = " + n + ";");
	}

}
