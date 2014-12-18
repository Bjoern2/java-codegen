package com.github.bjoern2.codegen;

import java.util.List;

public interface Generator {
	
	Generator tab(int count);
	
	Generator write(String content);
	
	Generator writeLines(String lines, String prefix, String postfix, int tabs);
	
	Generator writeLines(List<String> lines, String prefix, String postfix, int tabs);
	
	Generator lineBreak();
	
}
