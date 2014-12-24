package com.github.bjoern2.codegen;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class MapBuilderTest {

	@Test
	public void test() {
		Map<String, Integer> myMap = new MapBuilder<Integer>().
				put("foo", 42).
				put("bar", 13).
				get();
		
		Assert.assertFalse(myMap.isEmpty());
	}
	
}
