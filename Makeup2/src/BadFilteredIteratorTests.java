import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

/**
 * Tests for our bad implementation of FilteredIterators.  
 *
 * @author Samuel A. Rebelsky
 */
public class BadFilteredIteratorTests
    extends FilteredIteratorTests
{
  public BadFilteredIteratorTests()
  {
    super(new FilteredIteratorFactory<Integer>()
      {
        public Iterator<Integer> filter(Iterator<Integer> it,
                                        Predicate<? super Integer> pred)
        {
          return new BadFilteredIterator<Integer>(it, pred);
        } // filter(Iterator<...>, Predicate<...>)
      });
  } // FilteredIteratorTests(FilteredIteratorFactory)
} // class BadFilteredIteratorTests
