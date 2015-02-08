package com.github.bjoern2.codegen.gen;

import java.util.List;

import com.github.bjoern2.codegen.ClassType;
import com.github.bjoern2.codegen.Generator;
import com.github.bjoern2.codegen.GeneratorFactory;
import com.github.bjoern2.codegen.JavaAccessType;
import com.github.bjoern2.codegen.JavaAnnotation;
import com.github.bjoern2.codegen.JavaClass;
import com.github.bjoern2.codegen.JavaField;
import com.github.bjoern2.codegen.JavaInterface;
import com.github.bjoern2.codegen.JavaMethod;
import com.github.bjoern2.codegen.JavaMethodParameter;
import com.github.bjoern2.codegen.JavaPackage;
import com.github.bjoern2.codegen.JavaType;

public class JavaMarshaller implements Marshaller<JavaPackage> {

	@Override
	public void marshal(JavaPackage model, GeneratorFactory factory) {
		List<JavaClass> clazzes = model.getClazzes();
		for (JavaClass c : clazzes) {
			Generator g = factory.newGenerator(model.getName().replace(".", "/") + "/" + model.getName() + ".java");
			marshalClass(c, g, 0);
			g.close();
		}
		List<JavaInterface> interfaces = model.getInterfaces();
		for (JavaInterface i : interfaces) {
			Generator g = factory.newGenerator(model.getName().replace(".", "/") + "/" + model.getName() + ".java");
			marshalInterface(i, g, 0);
			g.close();
		}
		// TODO Auto-generated method stub
		
	}
	

	protected void marshalClass(JavaClass model, Generator g, int tabs) {
		marshalJavadoc(model.getComment(), g, tabs);
		
		marshalAnnotations(model.getAnnotations(), g, tabs);
		
		// public class Foo {
		g.tab(tabs);
		if (model.getAccessType() != null && model.getAccessType() != JavaAccessType.PACKAGE) {
			g.write(model.getAccessType().name().toLowerCase());
			g.write(" ");
		}
		
		if (model.isAbstract() && model.getType() == ClassType.CLASS) {
			g.write("abstract ");
		}
		
		if (model.isFinal() && model.getType() == ClassType.CLASS) {
			g.write("final ");
		}
		
		if (model.isStrictfp() && model.getType() == ClassType.CLASS) {
			g.write("strictfp ");
		}
		
		if (model.getType() == ClassType.INTERFACE) {
			g.write("interface ");
		} else if (model.getType() == ClassType.ANNOTATION) {
			g.write("@interface ");
		} else {
			g.write("class ");
		}
		
		g.write(model.getName() + " {");
		g.lineBreak();
		g.lineBreak();
		
		if (model.getFields() != null) {
			for (JavaField member : model.getFields()) {
				member.write(tabs + 1, g);
				g.lineBreak();
			}
		}
		
		if (model.getMethods() != null) {
			for (JavaMethod method : model.getMethods()) {
				method.write(tabs + 1, g);
				g.lineBreak();
			}
		}
		
		g.tab(tabs).write("}").lineBreak();
		
	}
	
	protected void marshalInterface(JavaInterface model, Generator g, int tabs) {
		marshalJavadoc(model.getComment(), g, tabs);
		
		marshalAnnotations(model.getAnnotations(), g, tabs);
		
		// public class Foo {
		g.tab(tabs);
		if (model.getAccessType() != null && model.getAccessType() != JavaAccessType.PACKAGE) {
			g.write(model.getAccessType().name().toLowerCase());
			g.write(" ");
		}
		
		g.write("interface ");
		
		g.write(model.getName() + " {");
		g.lineBreak();
		g.lineBreak();
		
		if (model.getFields() != null) {
			for (JavaField member : model.getFields()) {
				member.write(tabs + 1, g);
				g.lineBreak();
			}
		}
		
		if (model.getMethods() != null) {
			for (JavaMethod method : model.getMethods()) {
				method.write(tabs + 1, g);
				g.lineBreak();
			}
		}
		
		g.tab(tabs).write("}").lineBreak();
	}

	
	protected void marshalMethod(JavaMethod m, ClassType type, Generator g, int tabs) {
		marshalJavadoc(m.getComment(), g, tabs);
		
		marshalAnnotations(m.getAnnotations(), g, tabs);
		
		g.tab(tabs);
		if (m.getAccessType() != null && m.getAccessType() != JavaAccessType.PACKAGE && type == ClassType.CLASS) {
			g.write(m.getAccessType().name().toLowerCase());
			g.write(" ");
		}
		
		if (m.isNative()) {
			g.write("native ");
		}
		
		if (m.isFinal() && type == ClassType.CLASS) {
			g.write("final");
			g.write(" ");
		}
		
		if (m.isStatic() && type == ClassType.CLASS) {
			g.write("static");
			g.write(" ");
		}
		
		if (m.isAbstract() && type == ClassType.CLASS) {
			g.write("abstract");
			g.write(" ");
		}
		
		if (m.isSynchronized() && type == ClassType.CLASS) {
			g.write("synchronized");
			g.write(" ");
		}
		
		m.getReturnType().write(0, g);
		g.write(" ");
		
		g.write(m.getName());
		
		g.write("(");
		
		if (m.getParameters() != null && !m.getParameters().isEmpty()) {
			for (int i = 0; i < m.getParameters().size(); i++) {
				if (i > 0) {
					g.write(", ");
				}
				JavaMethodParameter parameter = m.getParameters().get(i);
				//parameter.write(0, g);
				marshalMethodParameter(parameter, g, 0);
			}
		}
		
		g.write(")");
		
		if (m.getExceptions() != null && !m.getExceptions().isEmpty()) {
			g.write(" throws ");
			for (int i = 0; i < m.getExceptions().size(); i++) {
				if (i > 0) {
					g.write(", ");
				}
				JavaType ex = m.getExceptions().get(i);
				ex.write(0, g);
			}
		}
		
		if (m.isAbstract() || m.isNative() ||  type == ClassType.INTERFACE) {
			g.write(";");
		} else {
			g.write(" {").lineBreak();
			if (m.getBody() != null && !m.getBody().isEmpty()) {
				g.tab(tabs + 1).write(m.getBody()).lineBreak();
			}
			g.tab(tabs).write("}").lineBreak();
		}
	}


	private void marshalAnnotations(List<JavaAnnotation> annotations, Generator g, int tabs) {
		if (annotations != null) {
			for (JavaAnnotation annotation : annotations) {
				annotation.write(tabs, g);
				g.lineBreak();
			}
		}
	}
	
	protected void marshalMethodParameter(JavaMethodParameter p, Generator g, int tabs) {
		g.tab(tabs);
		
		if (p.getAnnotations() != null) {
			for (JavaAnnotation annotation : p.getAnnotations()) {
				annotation.write(tabs, g);
				g.write(" ");
			}
		}
		
		if (p.isFinal()) {
			g.write("final ");
		}
		p.getType().write(0, g);
		g.write(" ");
		g.write(p.getName());
	}
	
	protected void marshalJavadoc(String comment, Generator g, int tabs) {
		if (comment != null && !comment.isEmpty()) {
			String[] commentLines = comment.split("\\r?\\n");
			g.tab(tabs).write("/**").lineBreak();
			for (String commentLine : commentLines) {
				g.tab(tabs).write(" * ").write(commentLine).lineBreak();
			}
			g.tab(tabs).write(" */").lineBreak();
		}
	}
	
	protected void marshalField(JavaField f, ClassType type, Generator g, int tabs) {
		marshalAnnotations(f.getAnnotations(), g, tabs);
		
		
		g.tab(tabs);
		if (f.getAccessType() != null && f.getAccessType() != JavaAccessType.PACKAGE) {
			g.write(f.getAccessType().name().toLowerCase() + " ");
		}
		
		if (f.isStatic()) {
			g.write("static ");
		}
		
		if (f.isVolatile()) {
			g.write("volatile ");
		}
		
		if (f.isFinal()) {
			g.write("final ");
		}
		
		f.getType().write(0, g);
		g.write(" ");
		g.write(f.getName());
		
		if (f.getValue() != null) {
			g.write(" = " + f.getValue());
		}
		
		g.write(";");
		g.lineBreak();
	}



}
