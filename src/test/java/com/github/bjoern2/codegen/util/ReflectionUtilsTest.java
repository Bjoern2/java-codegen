package com.github.bjoern2.codegen.util;

import static org.junit.Assert.*;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.github.bjoern2.codegen.JavaFile;
import com.github.bjoern2.codegen.StringGenerator;

public class ReflectionUtilsTest {

	@Test
	public void test() {
		JavaFile test = ReflectionUtils.toModel(StringUtils.class);
		
		System.out.println(test);
		
		
		
		StringGenerator g = new StringGenerator();
		test.write(0, g);
		System.out.println(g.toString());
		
	}

}
