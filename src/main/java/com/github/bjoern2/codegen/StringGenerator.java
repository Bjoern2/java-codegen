/*
 * Copyright 2015 Björn Schmitz
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.bjoern2.codegen;

import java.io.IOException;
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

	@Override
	public void close() throws IOException {
		w.close();
		
	}

}
