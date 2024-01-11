package org.handson.Question4;

import org.handson.MyLogger;
import java.util.Comparator;
 class PubDateAscComparator implements Comparator<Book>{
    public int compare(Book book1,Book book2)
    {
        MyLogger.customLogger("book1 year: "+book1.getYear()+" book2 year: "+book2.getYear()+"", "INFO");
        if(book1.getYear()==book2.getYear())
        {
            return book1.getTitle().compareTo(book2.getTitle());
        }
        return book1.getYear()-book2.getYear();
    } 
}
