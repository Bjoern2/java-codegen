package com.github.bjoern2.codegen;

import java.util.ArrayList;
import java.util.List;

public class ListBuilder<T> {

	private List<T> list = new ArrayList<T>();
	
	public ListBuilder<T> add(T entry) {
		list.add(entry);
		return this;
	}
	
	public List<T> get() {
		return list;
	}
	
}
