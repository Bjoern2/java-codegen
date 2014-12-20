package com.github.bjoern2.codegen.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.github.bjoern2.codegen.JavaClass;
import com.github.bjoern2.codegen.JavaField;
import com.github.bjoern2.codegen.JavaFile;
import com.github.bjoern2.codegen.JavaMethod;
import com.github.bjoern2.codegen.JavaMethodParameter;
import com.github.bjoern2.codegen.JavaType;
import com.github.bjoern2.codegen.JavaTypeImpl;

public class ImportOrganizer {

	private List<JavaType> imports;
	private List<String> clazzNames;
	
	private final class ImportComparator implements Comparator<JavaType> {

		@Override
		public int compare(JavaType o1, JavaType o2) {
			String s1 = o1.getName() == null ? "" : o1.getName();
			String s2 = o2.getName() == null ? "" : o2.getName();
			return s1.compareTo(s2);
		}
		
	}
	
	public void organize(JavaFile file) {
		imports = new ArrayList<JavaType>();
		clazzNames = new ArrayList<String>();
		
		if (file.getImports() != null) {
			imports.addAll(file.getImports());
		}
		
		organize(file.getClazz());
		
		Collections.sort(imports, new ImportComparator());
		
		file.setImports(imports);
	}
	
	private void organize(JavaClass clazz) {
		for (JavaField field : clazz.getFields()) {
			organize(field);
		}
		for (JavaMethod method : clazz.getMethods()) {
			organize(method);
		}
	}
	
	private void organize(JavaField field) {
		if (field == null) {
			return;
		}
		organize(field.getType());
	}
	
	private void organize(JavaMethod method) {
		if (method == null) {
			return;
		}
		organize(method.getReturnType());
		
		if (method.getExceptions() != null) {
			for (JavaType ex : method.getExceptions()) {
				organize(ex);
			}
		}
		
		if (method.getParameters() != null) {
			for (JavaMethodParameter para : method.getParameters()) {
				organize(para.getType());
			}
		}
		
	}
	
	private void organize(JavaType type) {
		if (type == null) {
			return;
		}
		if (Utils.isNativeType(type.getName())) {
			return;
		}
		
		if (type.getGenerics() != null) {
			for (JavaType generic : type.getGenerics()) {
				organize(generic);
			}
		}
		
		// Check for internal import:
		final String packageName = extractPackage(type.getName());
		if (StringUtils.isBlank(packageName)) {
			return;
		}
		
		// Class with the same name already imported?
		if (hasNameConflict(type.getName())) {
			return;
		}
		
		final String clazzName = extractClazzName(type.getName());
		if (!alreadyImported(type.getName())) {
			clazzNames.add(type.getName());
			JavaType imp = new JavaTypeImpl();
			imp.setName(type.getName());
			imp.setGenerics(new ArrayList<JavaType>());
			imp.setArray(0);
			imports.add(imp);
		}
		
		type.setName(clazzName);
	}
	
	
	private String extractClazzName(String clazzWithPackage) {
		int idxStart = clazzWithPackage.lastIndexOf(".");
		if (idxStart <= 0) {
			return clazzWithPackage;
		}
		return clazzWithPackage.substring(idxStart + 1);
	}
	
	private String extractPackage(String clazzWithPackage) {
		int idxStart = clazzWithPackage.lastIndexOf(".");
		if (idxStart <= 0) {
			return "";
		}
		return clazzWithPackage.substring(0, idxStart);
	}
	
	private boolean hasNameConflict(String name) {
		for (String clazzName : clazzNames) {
			if (clazzName.equals(name)) {
				continue;
			}
			String onlyClazz = extractClazzName(name);
			if (clazzName.endsWith(onlyClazz)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean alreadyImported(String name) {
		return clazzNames.contains(name);
	}
}
