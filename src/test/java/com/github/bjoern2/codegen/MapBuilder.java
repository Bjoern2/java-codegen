package com.github.bjoern2.codegen;

import java.util.HashMap;
import java.util.Map;

public class MapBuilder<T> {

	private Map<String, T> map = new HashMap<String, T>();
	
	public MapBuilder<T> put(String key, T value) {
		map.put(key, value);
		return this;
	}
	
	public Map<String, T> get() {
		return map;
	}
	
}
