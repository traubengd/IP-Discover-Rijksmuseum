package ip.rijksmuseumquiz.domain;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;

public interface IQuestionMaker {
    
    public BufferedImage getFullImage(String jsonString) throws URISyntaxException, IOException;

    public BufferedImage getSubImage(String jsonString) throws URISyntaxException, IOException;

    public BufferedImage getColourSchemeImage(String jsonString);

    public int getNumberOfPages(String jsonString, int resultsPerPage);

    public int getNumberOfObjects(String jsonString);

    public String getRandomObjectCode(String jsonString, int objectIndex);

    public String[] getMultipleTitles(String jsonString, int firstIndex, int secondIndex, int thirdIndex);

    public Question createQuestion(String jsonString, String[] wrongAnswers);
}
