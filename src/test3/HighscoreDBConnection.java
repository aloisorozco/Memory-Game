package test3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author matt
 */
public class HighscoreDBConnection {

    private String DB_Path = "..\\Project\\database";
    private static HighscoreDBConnection hdbcInstance;

    private HighscoreDBConnection() {

    }

    public Connection getConnection(String dbName){

        String engine = "jdbc:sqlite";
        String connectionString = engine + ":" + DB_Path + "\\" + dbName;

        try {
            Connection connection = DriverManager.getConnection(connectionString);

            return connection;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

    public static HighscoreDBConnection getHdbcInstance() {

        if(hdbcInstance == null){
            hdbcInstance = new HighscoreDBConnection();
        }

        return hdbcInstance;

    }
}

