package game.grounds;

/**
 * An interface that represents an unlockable object. An unlockable object is an object that can be
 * unlocked by an action, such as a door or a chest, with a key.
 */
public interface Unlockable {

  /**
   * Unlocks the object and changes its state.
   */
  void unlocked();
}
