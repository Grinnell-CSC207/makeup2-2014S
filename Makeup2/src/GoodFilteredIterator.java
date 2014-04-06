import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterators that filter elements.
 */
public class GoodFilteredIterator<T>
  implements Iterator<T>
{
  // +-------+-----------------------------------------------------------
  // | Notes |
  // +-------+

/*

  We add a field, next, which holds the next value we should return.

  We add a field, okToRemove, which keeps track of whether or not it's
  okay to call the remove method.

 */

  // +--------+----------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The iterator we're filtering.
   */
  Iterator<T> base;

  /**
   * The predicate used to do the filtering.
   */
  Predicate<? super T> pred;

  /**
   * The next value to return.
   */
  T next;

  /**
   * Can we call remove?
   */
  boolean okToRemove;

  // +--------------+----------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a version of base which returns only the values for which
   * pred holds.
   */
  public GoodFilteredIterator(Iterator<T> base, Predicate<? super T> pred)
  {
    this.base = base;
    this.pred = pred;
    this.next = null;
    this.okToRemove = false;
  } // GoodFilteredIterator(Iterator<T>, Predicate<? super T>)

  // +---------+---------------------------------------------------------
  // | Helpers |
  // +---------+

  /**
   * Grab the next value to return.  Sets next to null upon failure.
   */
  void getNext()
  {
    // If we already have a next value, we're done.
    if (this.next != null)
      return;
    // Find the next value that meets the predicate.
    while (this.base.hasNext())
      {
        this.next = this.base.next();
        if (pred.test(next))
          return;
      } // while
    // If we got this far, there is no next value.  Set the state
    // appropriately.
    this.next = null;
    this.okToRemove = false;
  } // getNext()

  // +---------+---------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Determine if any elements remain for which pred holds.
   */
  public boolean hasNext()
  {
    getNext();
    return (this.next != null);
  } // hasNext()

  /**
   * Get the next element for which pred holds.
   *
   * @throws NoSuchElementException
   *   when there are no more elements for which pred holds.
   */
  public T next()
    throws NoSuchElementException
  {
    getNext();
    if (this.next == null)
      throw new NoSuchElementException();
    T tmp = this.next;
    this.next = null;
    this.okToRemove = true;
    return tmp;
  } // next()

  /**
   * Remove the element most recently returned by next.  
   *
   * @throws IllegalStateException
   *   If the next method has not yet been called, or the remove method
   *   has already been called after the last call to the next method,
   *   or if the hasNext method has been called after the last call to
   *   the next method.
   */
  public void remove()
    throws IllegalStateException
  {
    if (!this.okToRemove)
      throw new IllegalStateException();
    this.okToRemove = false;
    this.base.remove();
  } // remove
} // class GoodFilteredIterator<T>
