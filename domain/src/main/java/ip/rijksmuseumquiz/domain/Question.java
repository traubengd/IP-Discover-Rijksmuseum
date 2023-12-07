package ip.rijksmuseumquiz.domain;

public class Question implements IQuestion{
    private String correctAnswer;
    private String[] wrongAnswers;
    private int artworkDate;
    private String plaqueDescription;

    public Question(String correctAnswer, String[] wrongAnswers, int correctAnswerDate, String plaqueDescription) {
        this.correctAnswer = correctAnswer;
        this.wrongAnswers = wrongAnswers;
        this.artworkDate = correctAnswerDate;
        this.plaqueDescription = plaqueDescription;
    }

    @Override
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public String[] getWrongAnswers() {
        return wrongAnswers;
    }

    @Override
    public String getPlaqueDescription() {
        return plaqueDescription;
    }

    @Override
    public int getCorrectDate() {
        return artworkDate;
    }
}
