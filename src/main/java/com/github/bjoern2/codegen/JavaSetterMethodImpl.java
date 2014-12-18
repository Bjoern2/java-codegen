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
