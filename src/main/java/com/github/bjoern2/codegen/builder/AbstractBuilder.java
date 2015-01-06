package com.github.bjoern2.codegen.builder;

public abstract class AbstractBuilder<T, B> implements Builder<B> {

	protected T parent;
	
	public AbstractBuilder(T parent) {
		this.parent = parent;
	}
	
	public T end() {
		return parent;
	}
	
}
