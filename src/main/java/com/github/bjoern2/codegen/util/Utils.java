package com.github.bjoern2.codegen.util;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

public class Utils {

	private static String[] nativeTypes = new String[] {
			"byte",
			"short",
			"int",
			"long",
			"float",
			"double",
			"boolean",
			"char",
			"void"
	};
	
	private static String[] reservedKeywords = new String[] {
			"abstract",
			"continue",
			"for",
			"new",
			"switch",
			"assert",
			"default",
			"goto",
			"package",
			"synchronized",
			"boolean",
			"do",
			"if",
			"private",
			"this",
			"break",
			"double",
			"implements",
			"protected",
			"throw",
			"byte",
			"else",
			"import",
			"public",
			"throws",
			"case",
			"enum",
			"instanceof",
			"return",
			"transient",
			"catch",
			"extends",
			"int",
			"short",
			"try",
			"char",
			"final",
			"interface",
			"static",
			"void",
			"class",
			"finally",
			"long",
			"strictfp",
			"volatile",
			"const",
			"float",
			"native",
			"super",
			"while"
	};
	
	public static String tab(int count) {
		return StringUtils.leftPad("", count, "\t");
	}
	
	public static boolean isNativeType(String type) {
		return Arrays.asList(nativeTypes).contains(type);
	}
	
	public static boolean isReservedKeyword(String keyword) {
		return Arrays.asList(reservedKeywords).contains(keyword);
	}
	
	
}
