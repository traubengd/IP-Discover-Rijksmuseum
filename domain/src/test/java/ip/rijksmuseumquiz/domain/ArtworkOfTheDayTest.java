package ip.rijksmuseumquiz.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArtworkOfTheDayTest {
    ArtworkOfTheDay testArtwork = new ArtworkOfTheDay("This is my title", "John Smith", 1991, "My description is not very long", "http://www.rijksmuseum.nl/nl/collectie/SK-C-5", "SK-C-5");

    @Test
    public void artworkKnowsItsTitle(){
        String title = testArtwork.getTitle();
        assertTrue(title.equals("This is my title"));
    }

    @Test
    public void artworkKnowsItsArtist(){
        String artist = testArtwork.getArtist();
        assertTrue(artist.equals("John Smith"));
    }

    @Test
    public void artworkKnowsItsYearOfCreation(){
        int year = testArtwork.getYear();
        assertEquals(1991, year);
    }

    @Test
    public void artworkKnowsItsDescription(){
        String description = testArtwork.getDescription();
        assertTrue(description.equals("My description is not very long"));
    }

    @Test
    public void artworkKnowsItsUrl(){
        String url = testArtwork.getArtworkUrl();
        assertTrue(url.equals("http://www.rijksmuseum.nl/nl/collectie/SK-C-5"));
    }

    @Test
    public void artworkKnowsItsObjectCode(){
        String objectCode = testArtwork.getObjectCode();
        assertTrue(objectCode.equals("SK-C-5"));
    }
}
