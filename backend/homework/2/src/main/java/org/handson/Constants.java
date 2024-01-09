package org.handson;

public class Constants {
    public static final double lowerLimitGPA = 3.2;
    public static final double higherLimitGPA = 3.5;

    public static final int[] studentIDList = {1001,1002};
    public static final  char[][] studentsGrades = {{'A','A','A','B'},{'A','B','B'}};


    public static final String apiResponse = "<work>" +
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

    public static final int gradeAValue = 4;
    public static final int gradeBValue = 3;
    public static final int gradeCValue = 2;

    public static final  String reviewSentimentAnalyser = "Haven't been here in years! Fantastic service and the food was delicious! Definetly will be a frequent flyer! Francisco was very attentive";
    public static final String reviewSentimentAnalyserExample2 = "Sorry OG, but you just lost some loyal customers. Horrible service, no smile or greeting just attitude. The breadsticks were stale and burnt, appetizer was cold and the food came out before the salad.";
    public static  final String[][] featureSetSentimentAnalyser = {
            {"ambiance", "ambience", "atmosphere", "decor"},
            {"dessert", "ice cream", "desert"},
            {"food"},
            {"soup"},
            {"service", "management", "waiter", "waitress",
                    "bartender", "staff", "server"}};
    public static final  String[] positiveOpinionWords = {"good", "fantastic", "friendly",
            "great", "excellent", "amazing", "awesome",
            "delicious"};
    public static final String[] negativeOpinionWords = {"slow", "bad", "horrible",
            "awful", "unprofessional", "poor"};

    public static final String endRuleTitle = "</title>";
    public static final String startRuleTitle = "<title>";

    public static final String imageURLStartRule = "<image_url>";
    public static final String imageURLEndRule = "</image_url>";

    public static final String publicationStartRule = "<original_publication_year type=\"integer\">";

    public static final String  publicationEndRule = "</original_publication_year>";

    public static final String  authorIDStartRule = "<id type=\"integer\">";
   public static final String authorIDEndRule = "</id>";

    public static final String authorNameStartRule = "<name>";
    public static final String authorNameEndRule = "</name>";

    public static final String ratingsCountStartRule = "<ratings_count type=\"integer\">";
    public static final String ratingsCountEndRule = "</ratings_count>";

    public static final  String averageRatingStartRule = "<average_rating>";
    public static final String averageRatingEndRule = "</average_rating>";




}
