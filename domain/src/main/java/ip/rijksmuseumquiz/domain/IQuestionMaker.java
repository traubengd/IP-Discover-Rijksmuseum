package ip.rijksmuseumquiz.domain;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;

public interface IQuestionMaker {
    
    public BufferedImage getImage(String jsonString) throws URISyntaxException, IOException;

    public BufferedImage getSubImage(String jsonString) throws URISyntaxException, IOException;

    public int getNumberOfPages(String jsonString, int resultsPerPage);

    public int getNumberOfObjects(String jsonString);

    public String getRandomObjectCode(String jsonString, int objectIndex);

    public String getLongTitle(String jsonString);

    public String getPlaqueDescription(String jsonString);

    public String[] getMultipleTitles(String jsonString, int firstIndex, int secondIndex, int thirdIndex);

    public Question createQuestion(String correctAnswer, String[] wrongAnswers, String plaqueDescription);
}
