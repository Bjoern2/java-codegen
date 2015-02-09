package com.github.bjoern2.codegen.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import com.github.bjoern2.codegen.JavaAccessType;
import com.github.bjoern2.codegen.JavaFile;
import com.github.bjoern2.codegen.builder.JavaClassBuilder;
import com.github.bjoern2.codegen.builder.JavaFileBuilder;

public class ReflectionUtils {

	public static JavaFile toModel(Class<?> clazz) {
		
		
		JavaFileBuilder builder = new JavaFileBuilder();
		builder.withPackage(clazz.getPackage().getName());
		JavaClassBuilder c = builder.beginClass();
		c.withName(clazz.getName());
		for (Field field : clazz.getFields()) {
			c.beginField()
				.withAccessType(toAccessType(field.getModifiers()))
				.withFinal(Modifier.isFinal(field.getModifiers()))
				.withName(field.getName())
				.withStatic(Modifier.isStatic(clazz.getModifiers()))
				.withType(field.getType().getName());
		}
		
		for (Method method : clazz.getDeclaredMethods()) {
			c.beginMethod()
				.withAccessType(toAccessType(method.getModifiers()))
				.withName(method.getName())
				.withReturnType(method.getReturnType().getName());
		}
		
		return builder.build();

	}
	
	private static JavaAccessType toAccessType(int modifiers) {
		if (Modifier.isPublic(modifiers)) {
			return JavaAccessType.PUBLIC;
		} else if (Modifier.isPrivate(modifiers)) {
			return JavaAccessType.PRIVATE;
		} else if (Modifier.isProtected(modifiers)) {
			return JavaAccessType.PROTECTED;
		} else {
			return JavaAccessType.PACKAGE;
		}
	}
	
}
