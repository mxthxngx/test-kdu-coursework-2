package org.handson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class APIResponseParser {


    public static void displayDetails(Book obj)
    {
        CustomLogger.customLogger('i',"Entered displayDetails");
        CustomLogger.customLogger('d',"Title:"+obj.getTitle());
        CustomLogger.customLogger('d',"Author ID:"+obj.getAuthor().getId()+" Author Name:"+obj.getAuthor().getName());
        CustomLogger.customLogger('d',"Publication Year:"+obj.getPublicationYear());
        CustomLogger.customLogger('d',"Average Rating:"+obj.getAverageRating());
        CustomLogger.customLogger('d',"Ratings Count:"+obj.getRatingsCount());
        CustomLogger.customLogger('d',"ImageURL :"+obj.getImageUrl());

    }

    public static Book parse(String response) {
        Book book = new Book();

        try {
            String endRule = "</title>";
            String startRule = "<title>";
            String title = parse(response, startRule, endRule);
            if (title.isBlank()) {
                CustomLogger.customLogger('e', "Null Title");
            }
            else
            {
                CustomLogger.customLogger('i',"Title Received");
                book.setTitle(title);
                CustomLogger.customLogger('d',"Title Added");
            }

            CustomLogger.customLogger('i',"Title has been set ");
            String imageStartRule = "<image_url>";
            String imageEndRule = "</image_url>";
            String imageURL = parse(response, imageStartRule, imageEndRule);
            if (imageURL.isBlank()) {
                CustomLogger.customLogger('e', "Null Image URL");
            }
            else
            {
                CustomLogger.customLogger('i',"Image URL Received");
                book.setImageUrl(imageURL);
                CustomLogger.customLogger('d',"Image URL Added");
            }

            String publicationStartRule = "<original_publication_year type=\"integer\">";
            String publicationEndRule = "</original_publication_year>";
            Integer publicationYear = Integer.parseInt(parse(response, publicationStartRule, publicationEndRule).trim());
            if (publicationYear==0) {
                CustomLogger.customLogger('e', "Null publication year");
            }
            else
            {
                CustomLogger.customLogger('i',"Publication Received");

                book.setPublicationYear(publicationYear);

                CustomLogger.customLogger('d',"Publication year Added: "+publicationYear);
            }
            String authorIDStartRule = "<id type=\"integer\">";
            String authorIDEndRule = "</id>";
            Long authorID = Long.parseLong(parse(response, authorIDStartRule, authorIDEndRule));
            if (authorID==0) {
                CustomLogger.customLogger('e', "Null ID of author");
            }
            else
            {
                CustomLogger.customLogger('i',"ID of Author recieved");
            }
            String authorNameStartRule = "<name>";
            String authorNameEndRule = "</name>";
            String authorName = parse(response, authorNameStartRule, authorNameEndRule);
            if (authorName.isBlank()) {
                CustomLogger.customLogger('e', "Null Title");
            }
            else
            {
                CustomLogger.customLogger('i',"Author Name Received");
               book.setAuthor(authorID,authorName);
                CustomLogger.customLogger('d',"Author Name and ID Added");

            }
            String ratingsCountStartRule = "<ratings_count type=\"integer\">";
            String ratingsCountEndRule = "</ratings_count>";
            String ratingString = parse(response, ratingsCountStartRule, ratingsCountEndRule);
            ratingString=ratingString.replaceAll(",","");
   
            Integer ratingsCount = Integer.parseInt(ratingString);
            if (ratingsCount==0) {
                CustomLogger.customLogger('e', "Invalid Rating Count");
            }
            else
            {
                CustomLogger.customLogger('i',"Rating Count Received:"+ratingsCount);

                book.setRatingsCount(ratingsCount);

                CustomLogger.customLogger('d',"Rating Count Added");
            }

            String averageRatingStartRule = "<average_rating>";
            String averageRatingEndRule = "</average_rating>";
            Double averageRating = Double.parseDouble(parse(response, averageRatingStartRule, averageRatingEndRule));
            if (averageRating==0) {
                CustomLogger.customLogger('e', "Null Average Ratings");
            }
            else
            {
                CustomLogger.customLogger('i',"Average Rating Received:"+averageRating);

                book.setAverageRating(averageRating);

                CustomLogger.customLogger('d',"Average Rating Added");
            }

        } catch (Exception e) {
            CustomLogger.customLogger('e', e.toString());
        }
        return book;
    }

    public static String parse(String response, String startRule, String endRule) {
        Integer initialIndex = response.indexOf(startRule);
        Integer finalIndex = response.indexOf(endRule);
//        System.out.println(initialIndex);
//        System.out.println(finalIndex);
        String parsedValue = "";
        if (!initialIndex.equals(-1) && !finalIndex.equals(-1)) {
            initialIndex += +startRule.length();


            parsedValue = response.substring(initialIndex, finalIndex);


        }


        return parsedValue;
    }

    // write overloaded parse method with the 3 parameters response,startRule, endRule
    public static void main(String[] args) {

        String response = "<work>" +
                "<id type=\"integer\">2361393</id>" +
                "<books_count type=\"integer\">813</books_count>" +
                "<ratings_count type=\"integer\">1,16,315</ratings_count>" +
                "<text_reviews_count type=\"integer\">3439</text_reviews_count>" +
                "<original_publication_year type=\"integer\">1854</original_publication_year>" +
                "<original_publication_month type=\"integer\" nil=\"true\"/>" +
                "<original_publication_day type=\"integer\" nil=\"true\"/>" +
                "<average_rating>3.79</average_rating>" +
                "<best_book type=\"Book\">" +
                "<id type=\"integer\">16902</id>" +
                "<title>Walden</title>" +
                "<author>" +
                "<id type=\"integer\">10264</id>" +
                "<name>Henry David Thoreau</name>" +
                "</author>" +
                "<image_url>" +
                "http://images.gr-assets.com/books/1465675526m/16902.jpg" +
                "</image_url>" +
                "<small_image_url>" +
                "http://images.gr-assets.com/books/1465675526s/16902.jpg" +
                "</small_image_url>" +
                "</best_book>" +
                "</work>";
      Book bookObject =  APIResponseParser.parse(response);
      APIResponseParser.displayDetails(bookObject);
    }
}
