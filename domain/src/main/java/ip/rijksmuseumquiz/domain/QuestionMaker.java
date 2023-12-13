package ip.rijksmuseumquiz.domain;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.ArrayList;

import ip.rijksmuseumquiz.domain.jsonParsers.ParsedArtObjectQuery;
import ip.rijksmuseumquiz.domain.jsonParsers.ParsedMultiPageQuery;
import ip.rijksmuseumquiz.domain.jsonParsers.ParsedPageQuery;

public class QuestionMaker implements IQuestionMaker {

    @Override
    public BufferedImage getFullImage(String jsonArtObjectString) {
        ParsedArtObjectQuery parsedArtObjectQuery = new ParsedArtObjectQuery(jsonArtObjectString);
        String imageUrl = parsedArtObjectQuery.getImageUrl();
        ImageEditor imageEditor = new ImageEditor();
        BufferedImage image = null;
        try {
            image = imageEditor.getImageFromUrl(imageUrl);
        } catch (URISyntaxException e) {
			System.out.println("There was a problem getting the URI");
		} catch (IOException e) {
			System.out.println("There was a problem with the input or output");
		}
        return image;
    }

    @Override
    public int getNumberOfPages(String jsonMultiplePagesString, int resultsPerPage) {
        ParsedMultiPageQuery parsedMultiPageQuery = new ParsedMultiPageQuery(jsonMultiplePagesString);
        int numberOfResults = parsedMultiPageQuery.getNumberOfResults();
        int numberOfPages = parsedMultiPageQuery.getNumberOfPages(numberOfResults, resultsPerPage);
        return numberOfPages;
    }

    @Override
    public int getNumberOfObjects(String jsonSinglePageString) {
        ParsedPageQuery parsedSinglePageQuery = new ParsedPageQuery(jsonSinglePageString);
        int numberOfObjects = parsedSinglePageQuery.getNumberOfObjects();
        return numberOfObjects;
    }

    @Override
    public String[] getMultipleTitles(String jsonSinglePageString, int firstIndex, int secondIndex,
            int thirdIndex) {
        ParsedPageQuery parsedSinglePageQuery = new ParsedPageQuery(jsonSinglePageString);        
        String[] multipleTitles = parsedSinglePageQuery.getMultipleLongTitles(firstIndex, secondIndex, thirdIndex);
        return multipleTitles;
    }

    @Override
    public String getObjectCodeOfSpecificIndex(String jsonSinglePageString, int objectIndex) {
        ParsedPageQuery parsedSinglePageQuery = new ParsedPageQuery(jsonSinglePageString);
        String objectCode = parsedSinglePageQuery.getObjectCode(objectIndex);
        return objectCode;
    }

    String getLongTitle(String jsonArtObjectString) {
        String artworkName = getArtworkTitle(jsonArtObjectString);
        String artistName = getArtistName(jsonArtObjectString);
        String artworkDate = String.valueOf(getDate(jsonArtObjectString));
        String longTitle = artworkName + ", " + artistName + ", " + artworkDate;
        return longTitle;
    }

    private String getArtworkTitle(String jsonArtObjectString){
        ParsedArtObjectQuery parsedArtObjectQuery = new ParsedArtObjectQuery(jsonArtObjectString);
        String title = parsedArtObjectQuery.getNameOfArtwork();
        return title;
    }

    private String getArtistName(String jsonArtObjectString) {
        ParsedArtObjectQuery parsedArtObjectQuery = new ParsedArtObjectQuery(jsonArtObjectString);
        String artistName = parsedArtObjectQuery.getNameOfArtist();
        return artistName;
    }

    private int getDate(String jsonArtObjectString) {
        ParsedArtObjectQuery parsedArtObjectQuery = new ParsedArtObjectQuery(jsonArtObjectString);
        int sortingDate = parsedArtObjectQuery.getDate();
        return sortingDate;
    }

    private String getObjectCode(String jsonArtObjectString) {
        ParsedArtObjectQuery parsedArtObjectQuery = new ParsedArtObjectQuery(jsonArtObjectString);
        String objectCode = parsedArtObjectQuery.getObjectCode();
        return objectCode;
    }

    @Override
    public Question createQuestion(String jsonArtObjectString, String[] wrongAnswers) {
        ParsedArtObjectQuery parsedArtObjectQuery = new ParsedArtObjectQuery(jsonArtObjectString);
        String correctAnswer = getLongTitle(jsonArtObjectString);
        int correctAnswerDate = parsedArtObjectQuery.getDate();
        String plaqueDescription = getArtworkDescription(jsonArtObjectString);
        
        Question question = new Question(correctAnswer, wrongAnswers, correctAnswerDate, plaqueDescription);
        return question;
    }

    String getArtworkDescription(String jsonArtObjectString) {
        ParsedArtObjectQuery parsedArtObjectQuery = new ParsedArtObjectQuery(jsonArtObjectString);
        String plaqueDescription;
        try {
            plaqueDescription = parsedArtObjectQuery.getPlaqueDescription();
        } catch (Exception e) {
            try {
                plaqueDescription = parsedArtObjectQuery.getDescription();
            } catch (Exception f) {
                plaqueDescription = "";
            }
        }
        return plaqueDescription;
    }

    @Override
    public BufferedImage getSubImage(String jsonArtObjectString) throws URISyntaxException, IOException {
        ImageEditor imageEditor = new ImageEditor();
        BufferedImage fullImage = getFullImage(jsonArtObjectString);
        Random random = new Random();
        int subImageSize = Math.min((int)Math.round(fullImage.getWidth() * 0.25), (int)Math.round(fullImage.getHeight() * 0.25));
        int randomXAxisSeed = Math.abs(random.nextInt(fullImage.getWidth() - subImageSize));
        int randomYAxisSeed = Math.abs(random.nextInt(fullImage.getHeight() - subImageSize));
        return imageEditor.getSubImage(fullImage, subImageSize, subImageSize, randomXAxisSeed, randomYAxisSeed);
    }

    @Override
    public BufferedImage getColourSchemeImage(String jsonArtObjectString) {
        ParsedArtObjectQuery parsedArtObjectQuery = new ParsedArtObjectQuery(jsonArtObjectString);
        ArrayList<ColourData> coloursInPainting = parsedArtObjectQuery.getColours();
        ImageEditor imageEditor = new ImageEditor();
        BufferedImage colourScheme = imageEditor.createColourScheme(coloursInPainting);
        return colourScheme;
    }

    @Override
    public ArtworkOfTheDay createArtworkOfTheDay(String jsonArtObjectString) {
        String title = getArtworkTitle(jsonArtObjectString);
        String artist = getArtistName(jsonArtObjectString);
        int year = getDate(jsonArtObjectString);
        String description = getArtworkDescription(jsonArtObjectString);
        String objectCode = getObjectCode(jsonArtObjectString);
        String rijksmuseumUrl = "http://www.rijksmuseum.nl/nl/collectie/" + objectCode;
        ArtworkOfTheDay artwork = new ArtworkOfTheDay(title, artist, year, description, rijksmuseumUrl, objectCode);
        return artwork;
    }
}
