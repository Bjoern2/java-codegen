package com.github.bjoern2.codegen;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class JavaAnnotationImpl implements JavaAnnotation {

	private String name;
	private Map<String, Object> values;

	public JavaAnnotationImpl() {
		super();
	}

	public JavaAnnotationImpl(String name) {
		super();
		this.name = name;
	}

	public JavaAnnotationImpl(String name, Map<String, Object> values) {
		super();
		this.name = name;
		this.values = values;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Map<String, Object> getValues() {
		return values;
	}

	@Override
	public void setValues(Map<String, Object> values) {
		this.values = values;
	}
	
	@Override
	public void write(int tabs, Generator g) {
		g.tab(tabs);
		g.write("@" + name);
		if (values != null && !values.isEmpty()) {
			g.write("(");
			
			int i = 0;
			for (Entry<String, Object> entry : values.entrySet()) {
				if (i > 0) {
					g.write(", ");
				}
				g.write(entry.getKey() + " = ");
				if (entry.getValue() instanceof JavaAnnotation) {
					JavaAnnotation a = (JavaAnnotation)entry.getValue();
					a.write(0, g);
				} else if (entry.getValue() instanceof List) {
					List<Object> list = (List)entry.getValue();
					if (!list.isEmpty()) {
						g.write("{");
						int j = 0;
						for (Object listEntry : list) {
							if (j > 0) {
								g.write(", ");
							}
							if (listEntry instanceof JavaAnnotation) {
								JavaAnnotation a = (JavaAnnotation)listEntry;
								a.write(0, g);
							} else {
								g.write("" + listEntry);
							}
							j++;
						}
						g.write("}");
					}
				} else {
					g.write("" + entry.getValue());
				}
				i++;
			}
			g.write(")");
		}
		
	}

}
