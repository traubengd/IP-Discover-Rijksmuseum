package ip.rijksmuseumquiz.domain;

public class Question implements IQuestion{
    private String correctAnswer;
    private String[] wrongAnswers;
    private String plaqueDescription;

    public Question(String correctAnswer, String[] wrongAnswers, String plaqueDescription) {
        this.correctAnswer = correctAnswer;
        this.wrongAnswers = wrongAnswers;
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
}
