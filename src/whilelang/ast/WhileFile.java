
package whilelang.ast;

import java.util.ArrayList;
import java.util.List;

import whilelang.util.SyntacticElement;

public class WhileFile {

	public final String filename;

	public final ArrayList<Decl> declarations;

	public WhileFile(String filename, List<Decl> decls) {
		this.filename = filename;
		this.declarations = new ArrayList<Decl>(decls);
	}

	public boolean hasName(String name) {
		for (Decl d : declarations) {
			if (d instanceof TypeDecl) {
				TypeDecl cd = (TypeDecl) d;
				if (cd.name().equals(name)) {
					return true;
				}
			} else if (d instanceof MethodDecl) {
				MethodDecl fd = (MethodDecl) d;
				if (fd.name().equals(name)) {
					return true;
				}
			}
		}
		return false;
	}

	public TypeDecl type(String name) {
		for (Decl d : declarations) {
			if (d instanceof TypeDecl) {
				TypeDecl cd = (TypeDecl) d;
				if (cd.name().equals(name)) {
					return cd;
				}
			}
		}
		return null;
	}

	public List<MethodDecl> functions(String name) {
		ArrayList<MethodDecl> matches = new ArrayList<MethodDecl>();
		for (Decl d : declarations) {
			if (d instanceof MethodDecl) {
				MethodDecl cd = (MethodDecl) d;
				if (cd.name().equals(name)) {
					matches.add(cd);
				}
			}
		}
		return matches;
	}

	public interface Decl extends SyntacticElement {

		public String name();
	}

	public static class TypeDecl extends SyntacticElement.Impl implements Decl {

		private final Type type;
		private final String name;

		public TypeDecl(Type type, String name, Attribute... attributes) {
			super(attributes);
			this.type = type;
			this.name = name;
		}

		public String name() {
			return getName();
		}

		public String toString() {
			return "type " + getType() + " is " + getName();
		}

		public Type getType() {
			return type;
		}

		public String getName() {
			return name;
		}
	}

	public final static class MethodDecl extends SyntacticElement.Impl implements Decl {

		private final String name;
		private final Type ret;
		private final ArrayList<Parameter> parameters;
		private final ArrayList<Stmt> statements;

		/**
		 * Construct an object representing a Whiley function.
		 * 
		 * @param name
		 *            - The name of the function.
		 * @param ret
		 *            - The return type of this method
		 * @param parameters
		 *            - The list of parameter names and their types for this
		 *            method
		 * @param statements
		 *            - The Statements making up the function body.
		 */
		public MethodDecl(String name, Type ret, List<Parameter> parameters, List<Stmt> statements,
				Attribute... attributes) {
			super(attributes);
			this.name = name;
			this.ret = ret;
			this.parameters = new ArrayList<Parameter>(parameters);
			this.statements = new ArrayList<Stmt>(statements);
		}

		public String name() {
			return getName();
		}

		public String getName() {
			return name;
		}

		public Type getRet() {
			return ret;
		}

		public List<Parameter> getParameters() {
			return parameters;
		}

		public List<Stmt> getBody() {
			return statements;
		}
	}

	public static final class Parameter extends SyntacticElement.Impl implements Decl {

		private final Type type;
		private final String name;

		public Parameter(Type type, String name, Attribute... attributes) {
			super(attributes);
			this.type = type;
			this.name = name;
		}

		public String name() {
			return getName();
		}

		public Type getType() {
			return type;
		}

		public String getName() {
			return name;
		}
	}
}
