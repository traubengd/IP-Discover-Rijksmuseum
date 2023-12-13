package ip.rijksmuseumquiz.domain;

public interface IArtworkOfTheDay {
    public String getTitle();
    public String getArtist();
    public int getYear();
    public String getDescription();
    public String getArtworkUrl();
    public String getObjectCode();
}