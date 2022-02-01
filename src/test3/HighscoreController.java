package test3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author matt
 */
public class HighscoreController {

    private String databaseName = "highscore.db";
    private String tableName = "highscores";
    private String scoreColumnName = "score";
    ArrayList<Highscore> highscoreList = new ArrayList<>();

    //Connect to Database
    HighscoreDBConnection hscCon = HighscoreDBConnection.getHdbcInstance();


    //Adds a score to the database
    public void setScore(Highscore newScore){

        //get the connection

        //Command query to add the new score to the database
        String command = "insert into " + tableName +" values (" + newScore.getLevel() + ")";

        try(
            //Try with resources
            Connection dbConnection = hscCon.getConnection(databaseName);
            Statement stmt = dbConnection.createStatement();
            ) {

            //Execute the command statement
            stmt.executeUpdate(command);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    //Returns a list of the top five current highscores in the database
    public ArrayList<Highscore> getHighscoreList(){

        highscoreList.clear();

        //Command query to receive all items in the table in order of the highest score
        String command = "select * from " + tableName + " order by -" + scoreColumnName;

        try(
                //Try with resources
                Connection dbConnection = hscCon.getConnection(databaseName);
                Statement stmt = dbConnection.createStatement();
        ) {
            //Execute the command statement

            ResultSet resultSet = stmt.executeQuery(command);

            //parse through the ResultSet only three times to receive the top three scores
            for(int i=0; i<5; i++){
                if(resultSet.next()) {

                    //Create a new highscore with the score of the current result
                    Highscore score = new Highscore();
                    score.setLevel(resultSet.getInt(scoreColumnName));

                    //Add the score to the list
                    highscoreList.add(score);
                }
            }
            //return the list of the top three scores
            return highscoreList;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

    //Extra method that clears the table of the current database file
    public void clear(){

        //Command query to delete all items in the table
        String command = "delete from " + tableName;

        try(
                //Try with resources
                Connection dbConnection = hscCon.getConnection(databaseName);
                Statement stmt = dbConnection.createStatement();
        ) {
            //Execute the command statement
            stmt.executeUpdate(command);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


}

