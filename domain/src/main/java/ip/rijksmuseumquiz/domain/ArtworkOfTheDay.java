package ip.rijksmuseumquiz.domain;

public class ArtworkOfTheDay implements IArtworkOfTheDay {
    private String title;
    private String artist;
    private int year;
    private String artworkDescription;
    private String rijksmuseumUrl;
    private String objectCode;

    public ArtworkOfTheDay(String title, String artist, int year, String description, String url, String objectCode) {
        this.title = title;
        this.artist = artist;
        this.year = year;
        this.artworkDescription = description;
        this.rijksmuseumUrl = url;
        this.objectCode = objectCode;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getArtist() {
        return artist;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public String getDescription() {
        return artworkDescription;
    }

    @Override
    public String getArtworkUrl() {
        return rijksmuseumUrl;
    }

    @Override
    public String getObjectCode() {
        return objectCode;
    }
}
