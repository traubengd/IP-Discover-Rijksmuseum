package ip.rijksmuseumquiz.api.models;

public class UserscoreInputDTO {
    String username;
    int scoreIncrease;

    public UserscoreInputDTO(String username, int scoreIncrease){
        this.username = username;
        this.scoreIncrease = scoreIncrease;
    }

    public String getUsername(){
        return username;
    }

    public int getScoreIncrease(){
        return scoreIncrease;
    }
}
