package ip.rijksmuseumquiz.persistence;

import java.util.HashMap;

public interface IUserRepository {
    double logInUser(String username);

    double getUserScore(String username);

    void updateUserScore(String username, int scoreToAdd);

    HashMap<String, Double> getLeaderboard();
}
