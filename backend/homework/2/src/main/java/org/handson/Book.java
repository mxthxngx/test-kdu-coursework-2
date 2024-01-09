package org.handson;
public class Book {
    private String title;
    private Author author = new Author();
    private int publicationYear;
    private double averageRating;
    private int ratingsCount;
    private String imageUrl;
// Add getters & setters for author, averageRating, and ratingsCount
    public String getTitle() {
        CustomLogger.customLogger('i',"Fetching title");

        return title;
    }
    public void setTitle(String title) {
        CustomLogger.customLogger('i',"Setting title");

        this.title = title;
    }
    public int getPublicationYear() {
        CustomLogger.customLogger('i',"Fetching Publication Year");

        return publicationYear;
    }
    public void setPublicationYear(int publicationYear) {
        CustomLogger.customLogger('i',"Setting Publication Year");

        this.publicationYear = publicationYear;
    }
    public String getImageUrl() {
        CustomLogger.customLogger('i',"Fetching ImageURL");

        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        CustomLogger.customLogger('i',"Setting ImageURL");

        this.imageUrl = imageUrl;
    }

    public int getRatingsCount() {
        CustomLogger.customLogger('i',"Fetching Ratings Count");

        return ratingsCount;
    }

    public void setRatingsCount(int ratingsCount) {
        CustomLogger.customLogger('i',"Setting Ratings Count");

        this.ratingsCount = ratingsCount;
    }

    public double getAverageRating() {
        CustomLogger.customLogger('i',"Getting Average Ratings ");

        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        CustomLogger.customLogger('i',"Setting Average Ratings");

        this.averageRating = averageRating;
    }

    public Author getAuthor() {
        CustomLogger.customLogger('i',"Fetching Author");

        return author;
    }


    public void setAuthor(long id, String name)
    {
        CustomLogger.customLogger('i',"Setting Author");

        this.author.setId(id);
        this.author.setName(name);
    }
}