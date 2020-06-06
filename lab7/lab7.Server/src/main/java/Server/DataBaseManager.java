package Server;

import Common.StudyGroup.StudyGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DataBaseManager {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);
    private static final String URL = "jdbc:postgresql://127.0.0.1:5411/studs";
    private static final String LOGIN = "postgres";
    private static final String PASS = "1023";

    DataBaseManager(){
        try {
            Class.forName("org.postgresql.Driver");
            logger.info("PostgreSQL JDBC Driver успешно загружен");
        } catch (ClassNotFoundException e){
            logger.warn("PostgreSQL JDBC Driver не найден. Укажите его в classpath");
            System.exit(-1);
        }
    }

    public void load() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASS);
            if (connection != null) logger.info("Соединение с базой данных успешно установлено");
            else throw new SQLException();
            Statement statement;
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM STUDS");
            while (resultSet.next()){
                String id = resultSet.getNString("_ID");
            }
        } catch (SQLException e){
            logger.warn("Соединение с базой данных не установлено");
        }
    }

//    public boolean insertNewElement(StudyGroup studyGroup){}
//
//    public boolean deleteNewElement(){}
//
//    public boolean deleteAllElements(){}
//
//    public boolean updateElement(StudyGroup studyGroup){}
}
