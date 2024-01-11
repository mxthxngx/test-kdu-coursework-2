package org.handson.Question3;

import org.handson.MyLogger;
import java.util.Comparator;

 class PubDateDescComparator implements Comparator<Book>{
      /**
       * Compares two books based on their years and titles.
       *
       * @param  book1  the first book to compare
       * @param  book2  the second book to compare
       * @return        a negative integer if book1's year is greater than book2's year,
       *                a positive integer if book1's year is less than book2's year,
       *                or zero if the years are equal and the titles are compared lexicographically
       */
      public int compare(Book book1,Book book2)
    {
        MyLogger.customLogger("book1 year: "+book1.getYear()+" book2 year: "+book2.getYear()+"", "INFO");
        if(book1.getYear()==book2.getYear())
        {
            return book1.getTitle().compareTo(book2.getTitle());
        }
        return book2.getYear()-book1.getYear();
    } 
}
