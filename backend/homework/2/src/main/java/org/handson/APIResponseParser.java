package org.handson;

public class APIResponseParser {


    public static void displayDetails(Book bookObj)
    {
        CustomLogger.customLogger('i',"Entered displayDetails");
        CustomLogger.customLogger('d',"Title:"+(bookObj.getTitle().isBlank()?"Not Defined": bookObj.getTitle()));
        CustomLogger.customLogger('d',"Author ID:"+(bookObj.getAuthor().getId()==0?"ID Not found":bookObj.getAuthor().getId())+" Author Name:"+bookObj.getAuthor().getName());
        CustomLogger.customLogger('d',"Publication Year:"+(bookObj.getPublicationYear()==0?"Publication Year Not Found":bookObj.getPublicationYear()));
        CustomLogger.customLogger('d',"Average Rating:"+(bookObj.getAverageRating()==0?"Average Rating Not Found":bookObj.getAverageRating()));
        CustomLogger.customLogger('d',"Ratings Count:"+(bookObj.getRatingsCount()==0?"Ratings Count Not Found":bookObj.getRatingsCount()));
        CustomLogger.customLogger('d',"ImageURL :"+(bookObj.getImageUrl().isBlank()?"URL Not Found":bookObj.getImageUrl()));

    }

    public static Book parse(String response) {
        Book book = new Book();

        try {
            String title = parse(response, Constants.START_RULE_TITLE, Constants.END_RULE_TITLE);
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
            String imageURL = parse(response, Constants.IMAGE_URL_START_RULE, Constants.IMAGE_URL_END_RULE);
            if (imageURL.isBlank()) {
                CustomLogger.customLogger('e', "Null Image URL");
            }
            else
            {
                CustomLogger.customLogger('i',"Image URL Received");
                book.setImageUrl(imageURL);
                CustomLogger.customLogger('d',"Image URL Added");
            }


            Integer publicationYear = Integer.parseInt(parse(response, Constants.PUBLICATION_START_RULE, Constants.PUBLICATION_END_RULE).trim());
            if (publicationYear==0) {
                CustomLogger.customLogger('e', "Null publication year");
            }
            else
            {
                CustomLogger.customLogger('i',"Publication Received");

                book.setPublicationYear(publicationYear);

                CustomLogger.customLogger('d',"Publication year Added: "+publicationYear);
            }

            Long authorID = Long.parseLong(parse(response, Constants.AUTHOR_ID_START_RULE, Constants.AUTHOR_ID_END_RULE));
            if (authorID == 0) {
                CustomLogger.customLogger('e', "Null ID of author");
            }
            else
            {
                CustomLogger.customLogger('i',"ID of Author recieved");
            }

            String authorName = parse(response, Constants.AUTHOR_NAME_START_RULE, Constants.AUTHOR_NAME_END_RULE);
            if (authorName.isBlank()) {
                CustomLogger.customLogger('e', "Null Title");
            }
            else
            {
                CustomLogger.customLogger('i',"Author Name Received");
                book.setAuthor(authorID,authorName);
                CustomLogger.customLogger('d',"Author Name and ID Added");

            }

            String ratingString = parse(response, Constants.RATINGS_COUNT_TYPE_INTEGER, Constants.RATINGS_COUNT_END_RULE);
            ratingString=ratingString.replace(",","");

            Integer ratingsCount = Integer.parseInt(ratingString);
            if (ratingsCount ==0) {
                CustomLogger.customLogger('e', "Invalid Rating Count");
            }
            else
            {
                CustomLogger.customLogger('i',"Rating Count Received:"+ratingsCount);

                book.setRatingsCount(ratingsCount);

                CustomLogger.customLogger('d',"Rating Count Added");
            }


            Double averageRating = Double.parseDouble(parse(response, Constants.AVERAGE_RATING_START_RULE, Constants.AVERAGE_RATING_END_RULE));
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

        try
        {
            Integer initialIndex = response.indexOf(startRule);
            Integer finalIndex = response.indexOf(endRule);
            String parsedValue = "";
            if (!initialIndex.equals(-1) && !finalIndex.equals(-1)) {
                initialIndex += +startRule.length();
                parsedValue = response.substring(initialIndex, finalIndex);
            }
            return parsedValue;

        }
        catch(Exception e)
        {
            CustomLogger.customLogger('e', e.toString());
            return "";
        }
    }


    public static void main(String[] args) {


        Book bookObject =  APIResponseParser.parse(Constants.API_RESPONSE);
        APIResponseParser.displayDetails(bookObject);
    }
}
