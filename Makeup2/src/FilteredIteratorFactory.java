import java.util.Iterator;

/**
 * Things that can build filtered iterators.
 *
 * @author Samuel A. Rebelsky
 */
public interface FilteredIteratorFactory<T>
{
  /**
   * Build an iterator that produces only the values from it for which
   * the predicate holds.
   */
  public Iterator<T> filter(Iterator<T> it, Predicate<? super T> pred);
} // interface FilteredIteratorFactory
