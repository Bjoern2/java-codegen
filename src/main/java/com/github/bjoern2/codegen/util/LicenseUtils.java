package com.github.bjoern2.codegen.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class LicenseUtils {

	public static String apache2(int year, String owner) {
		String template = loadTemplate("apache-2.0.txt");
		template = template.replace("[yyyy]", "" + year);
		template = template.replace("[name of copyright owner]", owner);
		return template;
	}
	
	public static String mit(int year, String owner) {
		String template = loadTemplate("mit.txt");
		template = template.replace("<year>", "" + year);
		template = template.replace("<copyright holders>", owner);
		return template;
	}
	
	public static String gpl30(String libraryName, int year, String owner) {
		String template = loadTemplate("gpl30.txt");
		template = template.replace("<year>", "" + year);
		template = template.replace("<name of author>", owner);
		template = template.replace("<one line to give the program's name and a brief idea of what it does.>", libraryName);
		return template;
	}
	
	public static String lgpl21(String libraryName, int year, String owner) {
		String template = loadTemplate("lgpl21.txt");
		template = template.replace("<year>", "" + year);
		template = template.replace("<name of author>", owner);
		template = template.replace("<one line to give the library's name and an idea of what it does.>", libraryName);
		return template;
	}
	
	private static String loadTemplate(String filename) {
		InputStream input = LicenseUtils.class.getResourceAsStream(filename);
		try {
			return IOUtils.toString(input);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
