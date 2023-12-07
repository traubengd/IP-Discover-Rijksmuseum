package ip.rijksmuseumquiz.domain;

public interface IQuestion {
    public String getCorrectAnswer();

    public String[] getWrongAnswers();

    public int getCorrectDate();

    public String getPlaqueDescription();
}
