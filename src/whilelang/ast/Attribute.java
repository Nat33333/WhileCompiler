
package whilelang.ast;

public interface Attribute {

  public static class Source implements Attribute {

    public final int start;
    public final int end;

    public Source(int start, int end) {
      this.start = start;
      this.end = end;
    }

    public String toString() {
      return "@" + start + ":" + end;
    }
  }  
  
  public static class Type implements Attribute {

	  public final whilelang.ast.Type type;

	  public Type(whilelang.ast.Type type) {
		  this.type = type;
	  }
  }
}
