package com.github.bjoern2.codegen;

import org.apache.commons.lang3.StringUtils;

public class Utils {

	public static String tab(int count) {
		return StringUtils.leftPad("", count, "\t");
	}
	
}
