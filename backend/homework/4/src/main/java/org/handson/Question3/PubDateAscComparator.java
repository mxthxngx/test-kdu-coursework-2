package org.handson.Question3;

import org.handson.MyLogger;
import java.util.Comparator;
 class PubDateAscComparator implements Comparator<Book>{
    /**
     * Compares two Book objects based on their year and title.
     *
     * @param  book1  the first Book object to be compared
     * @param  book2  the second Book object to be compared
     * @return        an integer value indicating the comparison result:
     *                - a negative value if book1's year is less than book2's year
     *                - zero if book1's year is equal to book2's year and their titles are the same
     *                - a positive value if book1's year is greater than book2's year
     */
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
