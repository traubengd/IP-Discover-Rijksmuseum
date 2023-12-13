package ip.rijksmuseumquiz.api.models;

import ip.rijksmuseumquiz.domain.IArtworkOfTheDay;

public class ArtworkOfTheDayDTO {
    private String title;
    private String objectCode;
    private String artist;
    private int year;
    private String artworkDescription;
    private String rijksmuseumUrl;

    public ArtworkOfTheDayDTO(IArtworkOfTheDay artwork){
        this.title = artwork.getTitle();
        this.objectCode = artwork.getObjectCode();
        this.artist = artwork.getArtist();
        this.year = artwork.getYear();
        this.artworkDescription = artwork.getDescription();
        this.rijksmuseumUrl = artwork.getArtworkUrl();
    }

    public String getTitle(){
        return title;
    }

    public String getObjectCode(){
        return objectCode;
    }

    public String getArtist() {
        return artist;
    }

    public int getYear() {
        return year;
    }

    public String getArtworkDescription() {
        return artworkDescription;
    }

    public String getRijksmuseumUrl() {
        return rijksmuseumUrl;
    }
}
