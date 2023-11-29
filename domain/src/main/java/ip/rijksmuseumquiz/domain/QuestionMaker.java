package ip.rijksmuseumquiz.domain;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;

import ip.rijksmuseumquiz.domain.jsonParsers.ParsedArtObjectQuery;
import ip.rijksmuseumquiz.domain.jsonParsers.ParsedMultiPageQuery;
import ip.rijksmuseumquiz.domain.jsonParsers.ParsedPageQuery;

public class QuestionMaker implements IQuestionMaker {

    @Override
    public BufferedImage getImage(String jsonArtObjectString) {
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
    public String getRandomObjectCode(String jsonSinglePageString, int objectIndex) {
        ParsedPageQuery parsedSinglePageQuery = new ParsedPageQuery(jsonSinglePageString);
        String objectCode = parsedSinglePageQuery.getObjectCode(objectIndex);
        return objectCode;
    }

    @Override
    public String getLongTitle(String jsonArtObjectString) {
        ParsedArtObjectQuery parsedArtObjectQuery = new ParsedArtObjectQuery(jsonArtObjectString);
        String longTitle = parsedArtObjectQuery.getLongTitle();
        return longTitle;
    }

    @Override
    public String getPlaqueDescription(String jsonArtObjectString) {
        ParsedArtObjectQuery parsedArtObjectQuery = new ParsedArtObjectQuery(jsonArtObjectString);
        String plaqueDescription = parsedArtObjectQuery.getPlaqueDescription();
        return plaqueDescription;
    }

    @Override
    public Question createQuestion(String correctAnswer, String[] wrongAnswers, String plaqueDescription) {
        Question question = new Question(correctAnswer, wrongAnswers, plaqueDescription);
        return question;
    }

    @Override
    public BufferedImage getSubImage(String jsonString) throws URISyntaxException, IOException {
        ImageEditor imageEditor = new ImageEditor();
        BufferedImage fullImage = getImage(jsonString);
        Random random = new Random();
        int subImageSize = Math.min((int)Math.round(fullImage.getWidth() * 0.25), (int)Math.round(fullImage.getHeight() * 0.25));
        int randomXAxisSeed = Math.abs(random.nextInt(fullImage.getWidth() - subImageSize));
        int randomYAxisSeed = Math.abs(random.nextInt(fullImage.getHeight() - subImageSize));
        return imageEditor.getSubImage(fullImage, subImageSize, subImageSize, randomXAxisSeed, randomYAxisSeed);
    }
}
