package ip.rijksmuseumquiz.api.models;

public class AnswerDTO {
    private String longTitle;
    private Boolean correctAnswer;

    public AnswerDTO(String answerContents, boolean correctAnswer) {
        this.longTitle = answerContents;
        this.correctAnswer = correctAnswer;
    }
    
    public String getLongTitle(){
        return longTitle;
    }

    public Boolean getCorrectAnswer(){
        return correctAnswer;
    }
}
