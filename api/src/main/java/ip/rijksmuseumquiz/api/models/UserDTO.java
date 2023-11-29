package ip.rijksmuseumquiz.api.models;

public class UserDTO {
    String username;
    double userscore;

    public UserDTO(String username, double userscore){
        this.username = username;
        this.userscore = userscore;
    }

    public String getUsername(){
        return username;
    }

    public double getUserscore(){
        return userscore;
    }
}
