package com.github.bjoern2.codegen;

import java.io.StringWriter;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class StringGenerator implements Generator {

	private StringWriter w = new StringWriter();

	public void writeLn(int tabs, String prefix, String content, String postfix) {
		if (content == null) {
			return;
		}
		
		String[] lines = content.split("\\r?\\n");
		for (String line : lines) {
			write(StringUtils.leftPad("", tabs, "\t") + prefix + line + postfix + "\n");
		}
	}

	public void writeLn(int tabs, String content) {
		writeLn(tabs, "", content, "");
	}

	public void write(int tabs, String content) {
		write(StringUtils.leftPad("", tabs, "\t") + content + "");
	}

	@Override
	public Generator tab(int count) {
		for (int i = 0; i < count; i++) {
			w.write("\t");
		}
		return this;
	}

	@Override
	public Generator write(String content) {
		w.write(content);
		return this;
	}

	@Override
	public Generator writeLines(String content, String prefix, String postfix, int tabs) {
		String[] lines = content.split("\\r?\\n");
		for (String line : lines) {
			write(StringUtils.leftPad("", tabs, "\t") + prefix + line + postfix + "\n");
		}
		return this;
	}
	
	@Override
	public Generator writeLines(List<String> lines, String prefix, String postfix, int tabs) {
		for (String line : lines) {
			write(StringUtils.leftPad("", tabs, "\t") + prefix + line + postfix + "\n");
		}
		return this;
	}

	@Override
	public Generator lineBreak() {
		w.write("\n");
		return this;
	}

	@Override
	public String toString() {
		return w.toString();
	}

}
