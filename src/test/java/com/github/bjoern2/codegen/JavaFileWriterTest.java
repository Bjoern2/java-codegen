package com.github.bjoern2.codegen;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class JavaFileWriterTest {

	@Test
	public void test() {
		JavaFile f = new JavaFileImpl();
		f.setComment("Apache 2 Lisence");
		
		JavaClass clazz = new JavaClassImpl();
		clazz.setName("HelloWorldApp");
		f.setDefinition(clazz);
		
		List<JavaMethod> methods = new ArrayList<JavaMethod>();
		clazz.setMethods(methods);
		
		JavaMethod m = new JavaMethodImpl();
		m.setAccessType(JavaAccessType.PUBLIC);
		m.setStatic(true);
		m.setReturnType(new JavaTypeImpl("void"));
		m.setName("main");
		List<JavaMethodParameter> parameters = new ArrayList<JavaMethodParameter>();
		parameters.add(new JavaMethodParameterImpl(new JavaTypeImpl("String", 1), "args"));
		m.setParameters(parameters);
		m.setBody("System.out.println(\"Hello World\");");
		methods.add(m);
		
		StringGenerator w = new StringGenerator();
		f.write(0, w);
		
	
		System.out.println(w.toString());
	}

}
