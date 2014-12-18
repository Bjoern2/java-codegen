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
