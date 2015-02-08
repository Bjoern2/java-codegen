package com.github.bjoern2.codegen;

public interface GeneratorFactory {

	Generator newGenerator(String filename);
	
}
