package com.github.bjoern2.codegen.builder;

import org.junit.Test;

import com.github.bjoern2.codegen.JavaAccessType;
import com.github.bjoern2.codegen.JavaFile;
import com.github.bjoern2.codegen.StringGenerator;

public class BuilderTest {
	
	@Test
	public void test() {
		JavaFileBuilder b = new JavaFileBuilder();
		JavaFile file = b.
				withPackage("com.github.bjoern2.test").
				beginClass().
					withName("MyApp").
					beginAnnotation().
						withName("Table").
						beginArgument().
							withStringValue("mytable").
						end().
					end().
					beginField().
						withStatic(true).withFinal(true).withType("String").withName("MESSAGE").withStringValue("Hello {0}!").
					end().
					beginMethod().
						withAccessType(JavaAccessType.PUBLIC).
						withReturnType("void").
						withName("sayHello").
						beginParameter().
							withType("String").
							withName("yourName").
						end().
					end().
				end().
				build();
		
		StringGenerator gen = new StringGenerator();
		file.write(0, gen);
		
		System.out.println(gen.toString());
	}

}
