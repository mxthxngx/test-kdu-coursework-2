package org.handson;

public class Constants {
   private Constants (){}
    public static final double LOWERLIMITGPA = 3.2;
    public static final double HIGHERLIMITGPA = 3.5;

    protected static final int[] STUDENT_ID_LIST = {1001,1002};
    public static final  char[][] STUDENTS_GRADES = {{'A','A','A','B'},{'A','B','B'}};


    public static final String API_RESPONSE = "<work>" +
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

    public static final int GRADE_A_VALUE = 4;
    public static final int GRADE_B_VALUE = 3;
    public static final int GRADE_C_VALUE = 2;

    public static final  String REVIEW_SENTIMENT_ANALYSER = "Haven't been here in years! Fantastic service and the food was delicious! Definetly will be a frequent flyer! Francisco was very attentive";
    public static final String REVIEW_SENTIMENT_ANALYSER_EXAMPLE_2 = "Sorry OG, but you just lost some loyal customers. Horrible service, no smile or greeting just attitude. The breadsticks were stale and burnt, appetizer was cold and the food came out before the salad.";
    protected static  final String[][] FEATURE_SET_SENTIMENT_ANALYSER = {
            {"ambiance", "ambience", "atmosphere", "decor"},
            {"dessert", "ice cream", "desert"},
            {"food"},
            {"soup"},
            {"service", "management", "waiter", "waitress",
                    "bartender", "staff", "server"}};
    protected static final  String[] POSITIVE_OPINION_WORDS = {"good", "fantastic", "friendly",
            "great", "excellent", "amazing", "awesome",
            "delicious"};
    protected static final String[] NEGATIVE_OPINION_WORDS = {"slow", "bad", "horrible",
            "awful", "unprofessional", "poor"};

    public static final String END_RULE_TITLE = "</title>";
    public static final String START_RULE_TITLE = "<title>";

    public static final String IMAGE_URL_START_RULE = "<image_url>";
    public static final String IMAGE_URL_END_RULE = "</image_url>";

    public static final String PUBLICATION_START_RULE = "<original_publication_year type=\"integer\">";

    public static final String PUBLICATION_END_RULE = "</original_publication_year>";

    public static final String AUTHOR_ID_START_RULE = "<id type=\"integer\">";
   public static final String AUTHOR_ID_END_RULE = "</id>";

    public static final String AUTHOR_NAME_START_RULE = "<name>";
    public static final String AUTHOR_NAME_END_RULE = "</name>";

    public static final String RATINGS_COUNT_TYPE_INTEGER = "<ratings_count type=\"integer\">";
    public static final String RATINGS_COUNT_END_RULE = "</ratings_count>";

    public static final  String AVERAGE_RATING_START_RULE = "<average_rating>";
    public static final String AVERAGE_RATING_END_RULE = "</average_rating>";




}
