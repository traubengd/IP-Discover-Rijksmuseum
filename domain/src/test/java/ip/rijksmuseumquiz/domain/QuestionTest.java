package ip.rijksmuseumquiz.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionTest {
    String[] testWrongAnswers = {"firstWrongAnswer", "secondWrongAnswer", "thirdWrongAnswer"};
    Question testQuestion = new Question("testAnswer", testWrongAnswers, "This is an artpiece");

    @Test
    public void questionKnowsItsCorrectAnswer(){
        assertTrue(testQuestion.getCorrectAnswer().equals("testAnswer"));
    }

    @Test
    public void questionHasThreeWrongAnswers(){
        assertTrue(testQuestion.getWrongAnswers()[0].equals("firstWrongAnswer"));
        assertTrue(testQuestion.getWrongAnswers()[1].equals("secondWrongAnswer"));
        assertTrue(testQuestion.getWrongAnswers()[2].equals("thirdWrongAnswer"));
        assertEquals(3, testQuestion.getWrongAnswers().length);
    }

    @Test
    public void questionHasADescriptionForItsCorrectArtwork(){
        assertTrue(testQuestion.getPlaqueDescription().equals("This is an artpiece"));
    }
}
