package org.handson.Question3;

import org.handson.MyLogger;
import java.util.Comparator;

 class PubDateDescComparator implements Comparator<Book>{
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
