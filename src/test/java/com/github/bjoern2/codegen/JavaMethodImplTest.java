package com.github.bjoern2.codegen;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class JavaMethodImplTest {

	@Test
	public void test() {
		JavaMethod m = new JavaMethodImpl();
		m.setAccessType(JavaAccessType.PUBLIC);
		m.setStatic(true);
		m.setReturnType(new JavaTypeImpl("void"));
		m.setName("main");
		List<JavaMethodParameter> parameters = new ArrayList<JavaMethodParameter>();
		parameters.add(new JavaMethodParameterImpl(new JavaTypeImpl("String", 1), "args"));
		m.setParameters(parameters);
		m.setBody("System.out.println(\"Hello World\");");
		
		StringGenerator writer = new StringGenerator();
		
		m.write(0, writer);
		
		System.out.println(writer.toString());
	}

}
