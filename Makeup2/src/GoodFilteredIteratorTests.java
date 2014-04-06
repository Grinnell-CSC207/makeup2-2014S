import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

/**
 * Tests for our bad implementation of FilteredIterators.  
 *
 * @author Samuel A. Rebelsky
 */
public class GoodFilteredIteratorTests
    extends FilteredIteratorTests
{
  public GoodFilteredIteratorTests()
  {
    super(new FilteredIteratorFactory<Integer>()
           {
             public Iterator<Integer> filter(Iterator<Integer> it,
                 Predicate<? super Integer> pred)
             {
               return new GoodFilteredIterator<Integer>(it, pred);
             } // filter(Iterator<...>, Predicate<...>)
           });
  } // FilteredIteratorTests(FilteredIteratorFactory)
} // class GoodFilteredIteratorTests
