package com.github.bjoern2.codegen;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.github.bjoern2.codegen.util.LicenseUtils;

public class InterfaceTest {

	@Test
	public void test() {
		JavaFile f = new JavaFileImpl();
		f.setComment(LicenseUtils.apache2(2014, "Björn Schmitz"));
		f.setPackage("com.github.bjoern2.test");
		
		JavaClass clazz = new JavaClassImpl();
		clazz.setName("IHelloWorld");
		clazz.setType(ClassType.INTERFACE);
		clazz.setComment("Interface for a hello world program.");
		f.setDefinition(clazz);
		
		List<JavaMethod> methods = new ArrayList<JavaMethod>();
		clazz.setMethods(methods);
		
		JavaMethod m = new JavaMethodImpl();
		m.setType(ClassType.INTERFACE);
		m.setAccessType(JavaAccessType.PUBLIC);
		m.setReturnType(new JavaTypeImpl("void"));
		m.setName("sayHello");
		List<JavaMethodParameter> parameters = new ArrayList<JavaMethodParameter>();
		parameters.add(new JavaMethodParameterImpl(new JavaTypeImpl("String"), "name"));
		m.setParameters(parameters);
		m.setBody("System.out.println(\"Hello World\");");
		m.setComment("Says hello.");
		methods.add(m);
		
		StringGenerator w = new StringGenerator();
		f.write(0, w);
		
	
		System.out.println(w.toString());
	}

}
