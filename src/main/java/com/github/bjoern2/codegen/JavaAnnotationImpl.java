package com.github.bjoern2.codegen;

import java.util.List;

public class JavaAnnotationImpl implements JavaAnnotation {

	private String name;
//	private Map<String, Object> values;
	private List<JavaAnnotationArgument> arguments;

	public JavaAnnotationImpl() {
		super();
	}

	public JavaAnnotationImpl(String name) {
		super();
		this.name = name;
	}

	public JavaAnnotationImpl(String name, List<JavaAnnotationArgument> arguments) {
		super();
		this.name = name;
		this.arguments = arguments;
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
	public void write(int tabs, Generator g) {
		g.tab(tabs);
		g.write("@" + name);
		if (arguments != null && !arguments.isEmpty()) {
			g.write("(");
			
			int i = 0;
			for (JavaAnnotationArgument entry : arguments) {
				if (i > 0) {
					g.write(", ");
				}
				g.write(entry.getName() + " = ");
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

	@Override
	public List<JavaAnnotationArgument> getArguments() {
		return arguments;
	}

	@Override
	public void setArguments(List<JavaAnnotationArgument> arguments) {
		this.arguments = arguments;
	}

}
