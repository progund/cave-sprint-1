package se.itu.game.cave;


/**
 * Represents a Thing in the game.
 */
public class Thing {

  private String name;

  /**
   * Constructor for Thing.
   * @param name the name of the Thing
   */
  public Thing(String name) {
    this.name = name;
  }
    
  /**
   * Returns the name of the Thing
   * @return the name of the Thing
   */
  public String name() {
    return name;
  }

  /**
   * Returns a String representation of the Thing
   * @return a String representation of the Thing
   */
  public String toString() {
    return name;
  }

  /**
   * Check if this Thing equals other
   * @return true if this Thing equals other
   */
  @Override
  public boolean equals(Object o) {
    if (! (o instanceof Thing) ) {
      return false;
    }
    if (o == this) {
      return true;
    }
    return ((Thing) o).name.equals(name);
  }
    
}
