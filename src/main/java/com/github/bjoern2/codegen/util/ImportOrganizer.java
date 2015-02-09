package com.github.bjoern2.codegen.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.github.bjoern2.codegen.JavaAnnotation;
import com.github.bjoern2.codegen.JavaAnnotationArgument;
import com.github.bjoern2.codegen.JavaClass;
import com.github.bjoern2.codegen.JavaDefinition;
import com.github.bjoern2.codegen.JavaField;
import com.github.bjoern2.codegen.JavaFile;
import com.github.bjoern2.codegen.JavaMethod;
import com.github.bjoern2.codegen.JavaMethodParameter;
import com.github.bjoern2.codegen.JavaType;
import com.github.bjoern2.codegen.JavaTypeImpl;

public class ImportOrganizer {

	private String filePackage;
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
		
		filePackage = file.getPackage() == null ? "" : file.getPackage();
		
		if (file.getImports() != null) {
			imports.addAll(file.getImports());
		}
		
		organize(file.getDefinition());
		
		Collections.sort(imports, new ImportComparator());
		
		file.setImports(imports);
	}
	
	private void organize(JavaDefinition def) {
		if (def instanceof JavaClass) {
			JavaClass clazz = (JavaClass)def;
			organize(clazz);
		}
	}
	
	private void organize(JavaClass clazz) {
		if (clazz.getAnnotations() != null) {
			for (JavaAnnotation ann : clazz.getAnnotations()) {
				organize(ann);
			}
		}
		if (clazz.getFields() != null) {
			for (JavaField field : clazz.getFields()) {
				organize(field);
			}
		}
		if (clazz.getMethods() != null) {
			for (JavaMethod method : clazz.getMethods()) {
				organize(method);
			}
		}
	}
	
	private void organize(JavaField field) {
		if (field == null) {
			return;
		}
		
		if (field.getAnnotations() != null) {
			for (JavaAnnotation ann : field.getAnnotations()) {
				organize(ann);
			}
		}
		
		organize(field.getType());
	}
	
	private void organize(JavaMethod method) {
		if (method == null) {
			return;
		}
		
		if (method.getAnnotations() != null) {
			for (JavaAnnotation ann : method.getAnnotations()) {
				organize(ann);
			}
		}
		
		organize(method.getReturnType());
		
		if (method.getExceptions() != null) {
			for (JavaType ex : method.getExceptions()) {
				organize(ex);
			}
		}
		
		if (method.getParameters() != null) {
			for (JavaMethodParameter para : method.getParameters()) {
				organize(para);
			}
		}
	}
	
	private void organize(JavaMethodParameter para) {
		organize(para.getType());
		if (para.getAnnotations() != null) {
			for (JavaAnnotation ann : para.getAnnotations()) {
				organize(ann);
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
	
	private void organize(JavaAnnotation annotation) {
		if (annotation == null) {
			return;
		}
		
		if (annotation.getArguments() != null) {
			for (JavaAnnotationArgument arg : annotation.getArguments()) {
				Object value = arg.getValue();
				if (value instanceof JavaAnnotation) {
					organize((JavaAnnotation)value);
				} else if (value instanceof List) {
					List<Object> list = (List)value;
					for (Object entry : list) {
						if (entry instanceof JavaAnnotation) {
							organize((JavaAnnotation)entry);
						}
					}
				}
			}
		}
		
		// Check for internal import:
		final String packageName = extractPackage(annotation.getName());
		if (StringUtils.isBlank(packageName)) {
			return;
		}
		
		// Class with the same name already imported?
		if (hasNameConflict(annotation.getName())) {
			return;
		}
		
		final String clazzName = extractClazzName(annotation.getName());
		if (!alreadyImported(annotation.getName())) {
			clazzNames.add(annotation.getName());
			JavaType imp = new JavaTypeImpl();
			imp.setName(annotation.getName());
			imp.setGenerics(new ArrayList<JavaType>());
			imp.setArray(0);
			imports.add(imp);
		}
		
		annotation.setName(clazzName);
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
	
	private boolean ownPackage(String name) {
		String pack = extractPackage(name);
		return filePackage.equals(pack);
	}
}
