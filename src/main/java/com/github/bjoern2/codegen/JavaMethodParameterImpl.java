package com.github.bjoern2.codegen;


public class JavaMethodParameterImpl implements JavaMethodParameter {

	private boolean _final = false;
	private JavaType type;
	private String name;
	
	public JavaMethodParameterImpl() {
		super();
	}
	
	public JavaMethodParameterImpl(JavaType type, String name) {
		super();
		this.type = type;
		this.name = name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void write(int tabs, Generator writer) {
		writer.tab(tabs);
		if (_final) {
			writer.write("final ");
		}
		type.write(0, writer);
		writer.write(" ");
		writer.write(name);
	}

	@Override
	public boolean isFinal() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public JavaType getType() {
		return type;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setType(JavaType type) {
		this.type = type;
	}

	@Override
	public void setFinal(boolean _final) {
		this._final = _final;
	}

}
