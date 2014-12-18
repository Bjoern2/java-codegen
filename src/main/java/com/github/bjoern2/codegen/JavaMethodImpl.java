package com.github.bjoern2.codegen;

import java.util.List;

public class JavaMethodImpl implements JavaMethod {

	private JavaAccessType accessType;
	private boolean _static;
	private boolean _final;
	private boolean _synchronized;
	private boolean _abstract;
	private JavaType returnType;
	private String name;
	private List<JavaMethodParameter> parameters;
	private List<JavaType> _exceptions;
	private String body;
	
	@Override
	public void setName(String name) {
		this.name = name;
		
	}

	@Override
	public void setAbstract(boolean _abstract) {
		this._abstract = _abstract;
		
	}

	@Override
	public void setSynchronized(boolean s) {
		this._synchronized = s;
		
	}

	@Override
	public void setAccessType(JavaAccessType type) {
		this.accessType = type;
		
	}

	@Override
	public void setStatic(boolean s) {
		this._static = s;
	}

	@Override
	public void setFinal(boolean f) {
		this._final = f;
	}

	@Override
	public void setParameters(List<JavaMethodParameter> parameters) {
		this.parameters = parameters;
		
	}

	@Override
	public void setExceptions(List<JavaType> _exceptions) {
		this._exceptions = _exceptions;
		
	}
	
	@Override
	public void write(int tabs, Generator writer) {
		
		writer.tab(tabs);
		if (accessType != null && accessType != JavaAccessType.PACKAGE) {
			writer.write(accessType.name().toLowerCase());
			writer.write(" ");
			
		}
		
		if (_final) {
			writer.write("final");
			writer.write(" ");
		}
		
		if (_static) {
			writer.write("static");
			writer.write(" ");
		}
		
		if (_abstract) {
			writer.write("abstract");
			writer.write(" ");
		}
		
		if (_synchronized) {
			writer.write("synchronized");
			writer.write(" ");
		}
		
		returnType.write(0, writer);
		writer.write(" ");
		
		writer.write(name);
		
		writer.write("(");
		
		if (parameters != null && !parameters.isEmpty()) {
			for (int i = 0; i < parameters.size(); i++) {
				if (i > 0) {
					writer.write(", ");
				}
				JavaMethodParameter parameter = parameters.get(i);
				parameter.write(0, writer);
			}
		}
		
		writer.write(")");
		
		if (_exceptions != null && !_exceptions.isEmpty()) {
			writer.write(" throws ");
			for (int i = 0; i < _exceptions.size(); i++) {
				if (i > 0) {
					writer.write(", ");
				}
				JavaType ex = _exceptions.get(i);
				ex.write(0, writer);
			}
		}
		
		if (_abstract) {
			writer.write(";");
		} else {
			writer.write(" {").lineBreak();
			if (body != null && !body.isEmpty()) {
				writer.tab(tabs + 1).write(body).lineBreak();
			}
			writer.tab(tabs).write("}").lineBreak();
		}
	}

	@Override
	public void setReturnType(JavaType type) {
		this.returnType = type;
		
	}

	@Override
	public void setBody(String body) {
		this.body = body;
		
	}

}
