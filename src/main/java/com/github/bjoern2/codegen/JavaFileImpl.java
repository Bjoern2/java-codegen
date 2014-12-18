package com.github.bjoern2.codegen;

import java.util.List;

import org.apache.commons.lang3.StringUtils;


public class JavaFileImpl implements JavaFile {


	private String comment;
	
	private String _package;
	
	private List<JavaType> imports;
	
	private JavaClass clazz;

	@Override
	public void setComment(String comment) {
		this.comment = comment;
		
	}
	
	@Override
	public void write(int tabs, Generator writer) {
		if (comment != null && !comment.isEmpty()) {
			String[] commentLines = comment.split("\\r?\\n");
			writer.tab(tabs).write("/*").lineBreak();
			for (String commentLine : commentLines) {
				writer.tab(tabs).write(" * ").write(commentLine).lineBreak();
			}
			writer.tab(tabs).write(" */").lineBreak();
		}
		
		if (StringUtils.isNoneBlank(_package)) {
			writer.tab(tabs).write("package " + _package + ";").lineBreak();
			writer.lineBreak();
		}
		
		if (imports != null) {
			for (JavaType type : imports) {
				writer.tab(tabs).write("import " + type.getName() + ";").lineBreak();
			}
		}
		
		if (clazz != null) {
			clazz.write(tabs, writer);
		}
		
	}

	@Override
	public String getPackage() {
		return _package;
	}

	@Override
	public void setPackage(String _package) {
		this._package = _package;
	}

	@Override
	public List<JavaType> getImports() {
		return imports;
	}

	@Override
	public void setImports(List<JavaType> imports) {
		this.imports = imports;
	}

	@Override
	public JavaClass getClazz() {
		return clazz;
	}

	@Override
	public void setClazz(JavaClass clazz) {
		this.clazz = clazz;
	}

	@Override
	public String getComment() {
		return comment;
	}

}
