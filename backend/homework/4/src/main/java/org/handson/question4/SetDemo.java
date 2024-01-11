package org.handson.question4;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.handson.MyLogger;

public class SetDemo {
	
	public static Set<Book> treeSetDemo(Comparator<Book> comparator) {
    Book book1 = new Book("Harry Potter", "J.K.Rowling", 1997);
    Book book2 = new Book("Harry Potter", "J.K.Rowling", 1997);
    Book book3 = new Book("Walden", "Henry David Thoreau", 1854);
    Book book4 = new Book("Effective Java", "Joshua Bloch", 2008);
    Book book5 = new Book("The Last Lecture", "Randy Pausch", 2008);

    Set<Book> books;
    if (comparator == null) {
        books = new TreeSet<>();
    } else {
        books = new TreeSet<>(comparator);
    }

    books.add(book1);
    books.add(book2);
    books.add(book3);
    books.add(book4);
    books.add(book5);

    for (Book book : books) {
        MyLogger.customLogger(book.getTitle()+" "+book.getAuthor()+" "+ book.getYear(),"DEBUG");
    }

    return books;
}

public static void main(String[] args) {
    MyLogger.customLogger("Reading first test case ...","INFO");

    treeSetDemo(null);
MyLogger.customLogger("Reading from second test case ...","INFO");
    treeSetDemo(new PubDateAscComparator());
    MyLogger.customLogger("Reading from third test case ...","INFO");

    treeSetDemo(new PubDateAscComparator());
    MyLogger.customLogger("Reading from fourth test case ...","INFO");

    treeSetDemo(new PubDateDescComparator()); 
    MyLogger.customLogger("Test cases complete!","INFO");

}
}