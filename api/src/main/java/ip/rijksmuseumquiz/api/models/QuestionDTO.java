package ip.rijksmuseumquiz.api.models;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ip.rijksmuseumquiz.domain.IQuestion;

public class QuestionDTO {
    private String correctObjectCode;
    private AnswerDTO[] answers;
    private String correctAnswer;
    private int artworkDate;
    private String plaqueDescription;

    public QuestionDTO(IQuestion question, String correctObjectCode) {
        this.correctObjectCode = correctObjectCode;
        this.plaqueDescription = question.getPlaqueDescription();
        this.artworkDate = question.getCorrectDate();
        this.correctAnswer = question.getCorrectAnswer();
        this.answers = new AnswerDTO[4];
        this.answers[0] = new AnswerDTO(question.getCorrectAnswer(), true);
        this.answers[1] = new AnswerDTO(question.getWrongAnswers()[0], false);
        this.answers[2] = new AnswerDTO(question.getWrongAnswers()[1], false);
        this.answers[3] = new AnswerDTO(question.getWrongAnswers()[2], false);

        List<AnswerDTO> answerList = Arrays.asList(answers);

        Collections.shuffle(answerList);

        answerList.toArray(this.answers);
    }

    public String getCorrectObjectCode(){
        return correctObjectCode;
    }

    public AnswerDTO[] getAnswers(){
        return answers;
    }

    public String getCorrectAnswer(){
        return correctAnswer;
    }

    public String getPlaqueDescription(){
        return plaqueDescription;
    }

    public int getArtworkDate(){
        return artworkDate;
    }
}
