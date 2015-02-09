package com.github.bjoern2.codegen.builder;

import com.github.bjoern2.codegen.JavaFile;
import com.github.bjoern2.codegen.JavaFileImpl;


public class JavaFileBuilder implements Builder<JavaFile> {

	private String _package;
	private JavaClassBuilder clazz;
	
	public JavaFileBuilder withPackage(String p) {
		this._package = p;
		return this;
	}
	
	public JavaClassBuilder beginClass() {
		clazz = new JavaClassBuilder(this);
		return clazz;
	}

	@Override
	public JavaFile build() {
		JavaFile f = new JavaFileImpl();
		f.setPackage(_package);
		if (clazz != null) {
			f.setDefinition(clazz.build());
		}
		return f;
	}
	
}
