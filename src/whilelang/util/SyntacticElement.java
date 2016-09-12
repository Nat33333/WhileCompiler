

package whilelang.util;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import whilelang.ast.Attribute;

/**
 * A Syntactic Element represents any part of the file for which is relevant to
 * the syntactic structure of the file, and in particular parts we may wish to
 * add information too (e.g. line numbers, types, etc).
 * 
 * @author David Pearce
 */
public interface SyntacticElement {

  /**
   * Get the list of attributes associated with this syntactice element.
   * 
   * @return
   */
  public List<Attribute> attributes();

  /**
   * Get the first attribute of the given class type. This is useful short-hand.
   * 
   * @param c
   * @return
   */
  public <T extends Attribute> T attribute(Class<T> c);

  public class Impl implements SyntacticElement {

    private List<Attribute> attributes;

    public Impl() {
      // I use copy on write here, since for the most part I don't expect
      // attributes to change, and hence can be safely aliased. But, when they
      // do change I need fresh copies.
      attributes = new CopyOnWriteArrayList<Attribute>();
    }

    public Impl(Attribute x) {
      attributes = new ArrayList<Attribute>();
      attributes.add(x);
    }

    public Impl(Collection<Attribute> attributes) {
      this.attributes = new ArrayList<Attribute>(attributes);
    }

    public Impl(Attribute[] attributes) {
      this.attributes = new ArrayList<Attribute>(Arrays.asList(attributes));
    }

    public List<Attribute> attributes() {
      return attributes;
    }

    @SuppressWarnings("unchecked")
    public <T extends Attribute> T attribute(Class<T> c) {
      for (Attribute a : attributes) {
        if (c.isInstance(a)) {
          return (T) a;
        }
      }
      return null;
    }
  }
}
