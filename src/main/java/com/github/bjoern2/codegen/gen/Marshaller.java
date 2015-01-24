package com.github.bjoern2.codegen.gen;

import com.github.bjoern2.codegen.Generator;

public interface Marshaller<M> {

	void marshal(M model, Generator g);
	
}
