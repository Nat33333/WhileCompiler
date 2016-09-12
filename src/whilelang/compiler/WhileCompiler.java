
package whilelang.compiler;

import java.io.File;
import java.io.IOException;

import whilelang.ast.WhileFile;

/**
 * Encapsulates the process for compiling a While file into its Abstract Syntac
 * Reprentation. This includes the application of all stages in the compilation
 * pipeline (such as type checking, etc).
 * 
 * @author David J. Pearce
 *
 */
public class WhileCompiler {
	private File srcFile;
	
	public WhileCompiler(String filename) {
		this.srcFile = new File(filename);
	}
	
	public WhileFile compile() throws IOException {
		// First, lexing and parsing
		Lexer lexer = new Lexer(srcFile.getPath());
		Parser parser = new Parser(srcFile.getPath(), lexer.scan());
		WhileFile ast = parser.read();

		// Second, type checking
		new TypeChecker().check(ast);

		// Third, unreachable code
		new UnreachableCode().check(ast);

		// Fourth, definite assignment
		new DefiniteAssignment().check(ast);
		
		// Done
		return ast;
	}
}
