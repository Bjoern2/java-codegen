package com.github.bjoern2.codegen;

import java.util.List;

public class JavaClassImpl implements JavaClass {
	
	private JavaAccessType accessType = JavaAccessType.PUBLIC;
	private String name;
	private boolean _final = false;
	private boolean _abstract = false;
	
	private List<JavaField> fields;
	private List<JavaMethod> methods;
	
	@Override
	public void write(int tabs, Generator writer) {
		writer.tab(tabs).write(((accessType != null && accessType != JavaAccessType.PACKAGE) ? accessType.name().toLowerCase() + " " : "") + "class " + name + " {").lineBreak();
		
		// TODO: Add final, abstract
		
		if (fields != null) {
			for (JavaField member : fields) {
				member.write(tabs + 1, writer);
				writer.lineBreak();
			}
		}
		
		if (methods != null) {
			for (JavaMethod method : methods) {
				method.write(tabs + 1, writer);
				writer.lineBreak();
			}
		}
		
		writer.tab(tabs).write("}").lineBreak();

	}

	@Override
	public void setName(String name) {
		this.name = name;
		
	}

	@Override
	public void setExtendsFrom(String clazzName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addImplementsFrom(String interfaceName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAccessType(JavaAccessType type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAbstract(boolean _abstract) {
		this._abstract = _abstract;
	}

	@Override
	public void setFinal(boolean _final) {
		this._final = _final;
	}

	@Override
	public void setMethods(List<JavaMethod> methods) {
		this.methods = methods;
		
	}

	@Override
	public void setFields(List<JavaField> members) {
		this.fields = members;
		
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public JavaAccessType getAccessType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAbstract() {
		return _abstract;
	}

	@Override
	public boolean isFinal() {
		return _final;
	}

	@Override
	public String getExtendsFrom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JavaMethod> getMethods() {
		return methods;
	}

	@Override
	public List<JavaField> getFields() {
		return fields;
	}

}
