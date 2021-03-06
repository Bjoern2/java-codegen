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

import java.util.List;

public interface Generator {
	
	Generator tab(int count);
	
	Generator write(String content);
	
	Generator writeLines(String lines, String prefix, String postfix, int tabs);
	
	Generator writeLines(List<String> lines, String prefix, String postfix, int tabs);
	
	Generator lineBreak();
	
	void close();
	
}
