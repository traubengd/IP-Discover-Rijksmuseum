package ip.rijksmuseumquiz.persistence;

import java.sql.*;
import java.util.HashMap;

public class UserRepositoryDatabase implements IUserRepository {

    @Override
    public double getUserScore(String username) {
        double userScore = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "[DATABASEURL]]", "[DATABASEUSERNAME]",
                    "[DATABASEPASSWORD]");
            Statement statement;
            statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM iprijksmuseum.players WHERE playername='" + username + "'");
            resultSet.next();
            userScore = resultSet.getDouble("playerscore");
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userScore;
    }

    @Override
    public void updateUserScore(String username, int scoreToAdd) {
        try {
            double previousScore = getUserScore(username);
            double newScore = previousScore + scoreToAdd;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "[DATABASEURL]]", "[DATABASEUSERNAME]",
                    "[DATABASEPASSWORD]");
            Statement statement;
            statement = connection.createStatement();
            statement.executeUpdate(
                    "UPDATE iprijksmuseum.players SET playerscore=" + newScore + " WHERE playername='" + username
                            + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public HashMap<String, Double> getLeaderboard() {
        HashMap<String, Double> leaderboard = new HashMap<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "[DATABASEURL]]", "[DATABASEUSERNAME]",
                    "[DATABASEPASSWORD]");
            Statement statement;
            statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM iprijksmuseum.players ORDER BY playerscore DESC");
            int i = 0;
            while (resultSet.next() && i < 10) {
                String username = resultSet.getString("playername");
                double userscore = resultSet.getDouble("playerscore");
                leaderboard.put(username, userscore);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return leaderboard;
    }

    @Override
    public double logInUser(String username) {
        double userScore = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "[DATABASEURL]]", "[DATABASEUSERNAME]",
                    "[DATABASEPASSWORD]");
            Statement statement;
            statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery(
                            "SELECT EXISTS (SELECT * FROM iprijksmuseum.players WHERE playername='" + username + "')");
            resultSet.next();
            if (resultSet.getInt(1) == 0) {
                createUser(username, connection);
            } else {
                userScore = getUserScore(username);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userScore;
    }

    private void createUser(String username, Connection connection) throws SQLException {
        Statement statement;
        statement = connection.createStatement();
        statement.executeUpdate(
                "INSERT iprijksmuseum.players (playername, playerscore) VALUES ('" + username + "', 0)");
        statement.close();
    }
}
