package com.github.bjoern2.codegen;

import java.util.ArrayList;
import java.util.List;

public class JavaMainMethodImpl extends JavaMethodImpl {

	public JavaMainMethodImpl() {
		super();
		setAccessType(JavaAccessType.PUBLIC);
		setStatic(true);
		setReturnType(new JavaTypeImpl("void"));
		setName("main");
		List<JavaMethodParameter> parameters = new ArrayList<JavaMethodParameter>();
		parameters.add(new JavaMethodParameterImpl(new JavaTypeImpl("String", 1), "args"));
		setParameters(parameters);
	}

}
