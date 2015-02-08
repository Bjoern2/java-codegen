package com.github.bjoern2.codegen.gen;

import com.github.bjoern2.codegen.GeneratorFactory;

public interface Marshaller<M> {

	void marshal(M model, GeneratorFactory factory);
	
}
