package Server;


import Common.StudyGroup.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DataBaseManager {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);
    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/studs";
    private static final String LOGIN = "s284711";
    private static final String PASS = "rfx299";
    private final CollectionManager collectionManager;

    DataBaseManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        collectionManager.setDataBaseManager(this);
        try {
            Class.forName("org.postgresql.Driver");
            logger.info("PostgreSQL JDBC Driver успешно загружен");
            load();
            collectionManager.sort();
        } catch (ClassNotFoundException e) {
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
            Statement statement = connection.createStatement();
//            byte[] bytes = new byte[]{4};
//            HashMap<String, byte[]> myMap = new HashMap<String, byte[]>();
//            myMap.put("cawa2001",bytes);
//            addUser("Sasha",myMap);
            ResultSet entrySet = statement.executeQuery("SELECT * FROM StudyGroups" +
                    " LEFT JOIN GroupAdmins ON StudyGroups.id = GroupAdmins.Person_id" +
                    " LEFT JOIN Locations ON StudyGroups.id = Locations.Location_id;");
            Counter counter = new Counter(collectionManager);
            while (entrySet.next()) {
                StudyGroup st = new StudyGroup();
                st.setId(entrySet.getInt("id"));
                st.setName(entrySet.getString("name"));
                st.setCoordinates(new Coordinates(entrySet.getFloat("x"), entrySet.getInt("y")));
                st.setCreation(entrySet.getDate("creationDate").toString());
                st.setStudentsCount(entrySet.getLong("studentsCount"));
                st.setFormOfEducation(FormOfEducation.parse(entrySet.getString("formOfEducation")));
                st.setSemesterEnum(Semester.parse(entrySet.getString("semester")));
                if (entrySet.getBoolean("groupAdmin")) {
                    if (entrySet.getBoolean("location")) {
                        st.setGroupAdmin(new Person(entrySet.getString("nameOfGroupAdmin"), entrySet.getLong("height"),
                                Color.parse(entrySet.getString("eyeColor")), Color.parse(entrySet.getString("hairColor")),
                                Country.parse(entrySet.getString("country")), new Location(entrySet.getFloat("z"),
                                entrySet.getDouble("w"), entrySet.getString("locationOfGroupAdmin"))));
                    } else
                        st.setGroupAdmin(new Person(entrySet.getString("nameOfGroupAdmin"), entrySet.getLong("height"),
                                Color.parse(entrySet.getString("eyeColor")), Color.parse(entrySet.getString("hairColor")),
                                Country.parse(entrySet.getString("country")), null));
                } else st.setGroupAdmin(null);
                st.setLogin(entrySet.getString("userLogin"));
                int key = counter.increase().get();
                st.setThisKey(key);
                collectionManager.getStudyGroupMap().put(key, st);
            }
            logger.info("Коллекция загружена");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.warn("Соединение с базой данных не установлено. Коллекция не заргужена");
        }
    }
    public static  Map<String, Map<String, byte[]>> loadUsers(){
        logger.info("Загружаем пользователей");
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASS)) {
            Statement statement = connection.createStatement();
            ResultSet entrySet = statement.executeQuery("SELECT * FROM Users;");
            Map<String, Map<String, byte[]>> users = new HashMap<String, Map<String, byte[]>>();
            while (entrySet.next()){
                Map<String, byte[]> pass = new HashMap<String, byte[]>();
                pass.put(entrySet.getString("password"),entrySet.getBytes("hash"));
                users.put(entrySet.getString("userLogin"),pass);
            }
            logger.info("Пользователи загружены");
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void addUser(String login,HashMap<String, byte[]> password) {
        logger.info("Добавляем нового пользователя");
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASS)) {
            String pass = "";
            byte[] bytes = null;
            for(Map.Entry<String,byte[]> entry:password.entrySet()){
                pass = entry.getKey();
                bytes = entry.getValue();
            }
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Users" +
                    "(userLogin, password, hash)" +
                    "VALUES (?,?,?);");
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,pass);
            preparedStatement.setBytes(3,bytes);
            preparedStatement.execute();
            logger.info("Пользователь {} добавлен", login);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Не удалось создать аккаунт, возможно, ваш логин {} занят или содержит недопустимые символы", login);
        }
    }

    public int addElement(StudyGroup studyGroup) {
        try {
            Connection connection = DriverManager.getConnection(URL, LOGIN, PASS);
            int id = 0;
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO StudyGroups " +
                    "(id, name, x, y, creationDate, studentsCount, formOfEducation, semester, groupAdmin, userLogin) " +
                    "VALUES (nextval('element_id'), ?, ?, ?, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, studyGroup.getName());
            preparedStatement.setDouble(2, studyGroup.getCoordinates().getX());
            preparedStatement.setInt(3, studyGroup.getCoordinates().getY());
            preparedStatement.setDate(4, Date.valueOf(studyGroup.getCreationDate()));
            preparedStatement.setLong(5, studyGroup.getStudentsCount());
            preparedStatement.setString(6, studyGroup.getFormOfEducation().toString());
            if (studyGroup.getSemesterEnum() != null)
                preparedStatement.setString(7, studyGroup.getSemesterEnum().toString());
            else preparedStatement.setString(7, null);
            if (studyGroup.getGroupAdmin() != null) preparedStatement.setBoolean(8, true);
            else preparedStatement.setBoolean(8, false);
            preparedStatement.setString(9,studyGroup.getLogin());
            preparedStatement.executeUpdate();
            ResultSet entry = preparedStatement.getGeneratedKeys();
            if (entry.next()) id = entry.getInt("id");
            logger.info("Значение id = {}", id);
            if (studyGroup.getGroupAdmin() != null) {
                PreparedStatement personStatement = connection.prepareStatement("INSERT INTO GroupAdmins " +
                        "(Person_id, nameOfGroupAdmin, height, eyeColor, hairColor, country, location) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?);");
                personStatement.setInt(1, id);
                personStatement.setString(2, studyGroup.getGroupAdmin().getName());
                personStatement.setLong(3, studyGroup.getGroupAdmin().getHeight());
                if (studyGroup.getGroupAdmin().getEyeColor() != null)
                    personStatement.setString(4, studyGroup.getGroupAdmin().getEyeColor().toString());
                else personStatement.setString(4, null);
                if (studyGroup.getGroupAdmin().getHairColor() != null)
                    personStatement.setString(5, studyGroup.getGroupAdmin().getHairColor().toString());
                else personStatement.setString(5, null);
                personStatement.setString(6, studyGroup.getGroupAdmin().getNationality().toString());
                if (studyGroup.getGroupAdmin().getLocation() != null) personStatement.setBoolean(7, true);
                else personStatement.setBoolean(7, false);
                personStatement.executeUpdate();
                if (studyGroup.getGroupAdmin().getLocation() != null) {
                    PreparedStatement locationStatement = connection.prepareStatement("INSERT INTO Locations " +
                            "(location_id, z, w, locationOfGroupAdmin) VALUES (?, ?, ?, ?);");
                    locationStatement.setInt(1, id);
                    locationStatement.setFloat(2, studyGroup.getGroupAdmin().getLocation().getX());
                    locationStatement.setDouble(3, studyGroup.getGroupAdmin().getLocation().getY());
                    locationStatement.setString(4, studyGroup.getGroupAdmin().getLocation().toString());
                    locationStatement.executeUpdate();
                }
            }
            logger.info("Объект добавлен в БД");
            connection.close();
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean updateElement(StudyGroup studyGroup, int st_id) {
        try {
            Connection connection = DriverManager.getConnection(URL, LOGIN, PASS);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE StudyGroups " +
                    " SET name = ?, x = ?, y = ?, creationDate = ?, studentsCount = ?, formOfEducation = ?, semester = ?, groupAdmin = ? " +
                    "WHERE StudyGroups.id = ?;");
            preparedStatement.setString(1, studyGroup.getName());
            preparedStatement.setDouble(2, studyGroup.getCoordinates().getX());
            preparedStatement.setInt(3, studyGroup.getCoordinates().getY());
            preparedStatement.setDate(4, Date.valueOf(studyGroup.getCreationDate()));
            preparedStatement.setLong(5, studyGroup.getStudentsCount());
            preparedStatement.setString(6, studyGroup.getFormOfEducation().toString());
            if (studyGroup.getSemesterEnum() != null)
                preparedStatement.setString(7, studyGroup.getSemesterEnum().toString());
            else preparedStatement.setString(7, null);
            if (studyGroup.getGroupAdmin() != null) preparedStatement.setBoolean(8, true);
            else preparedStatement.setBoolean(8, false);
            preparedStatement.setInt(9, st_id);
            preparedStatement.executeUpdate();
            if (studyGroup.getGroupAdmin() != null) {
                PreparedStatement personStatement = connection.prepareStatement("UPDATE GroupAdmins " +
                        "SET nameOfGroupAdmin = ?, height = ?, eyeColor = ?, hairColor = ?, country = ?, location = ? " +
                        "WHERE GroupAdmins.Person_id = ?;");
                personStatement.setString(1, studyGroup.getGroupAdmin().getName());
                personStatement.setLong(2, studyGroup.getGroupAdmin().getHeight());
                if (studyGroup.getGroupAdmin().getEyeColor() != null)
                    personStatement.setString(3, studyGroup.getGroupAdmin().getEyeColor().toString());
                else personStatement.setString(3, null);
                if (studyGroup.getGroupAdmin().getHairColor() != null)
                    personStatement.setString(4, studyGroup.getGroupAdmin().getHairColor().toString());
                else personStatement.setString(4, null);
                personStatement.setString(5, studyGroup.getGroupAdmin().getNationality().toString());
                if (studyGroup.getGroupAdmin().getLocation() != null) personStatement.setBoolean(6, true);
                else personStatement.setBoolean(6, false);
                personStatement.setInt(7, st_id);
                personStatement.executeUpdate();
                if (studyGroup.getGroupAdmin().getLocation() != null) {
                    PreparedStatement locationStatement = connection.prepareStatement("UPDATE Locations " +
                            "SET z = ?, w = ?, locationOfGroupAdmin = ? WHERE Locations.Location_id = ?;");
                    locationStatement.setFloat(1, studyGroup.getGroupAdmin().getLocation().getX());
                    locationStatement.setDouble(2, studyGroup.getGroupAdmin().getLocation().getY());
                    locationStatement.setString(3, studyGroup.getGroupAdmin().getLocation().toString());
                    locationStatement.setInt(4, st_id);
                    locationStatement.executeUpdate();
                }
            }
            logger.info("Объект обновлен в БД");
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteElement(int st_id) {
        try {
            Connection connection = DriverManager.getConnection(URL, LOGIN, PASS);
            PreparedStatement locationStatement = connection.prepareStatement("DELETE FROM Locations " +
                    "WHERE Location_id = ?;");
            locationStatement.setInt(1, st_id);
            locationStatement.executeUpdate();
            PreparedStatement personStatement = connection.prepareStatement("DELETE FROM GroupAdmins " +
                    "WHERE Person_id = ?;");
            personStatement.setInt(1, st_id);
            personStatement.executeUpdate();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM StudyGroups " +
                    "WHERE id = ?;");
            preparedStatement.setInt(1, st_id);
            preparedStatement.executeUpdate();
            logger.info("Объект удален");
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAllElements() {
        try {
            Connection connection = DriverManager.getConnection(URL, LOGIN, PASS);
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM StudyGroups");
            statement.executeUpdate("DELETE FROM GroupAdmins");
            statement.executeUpdate("DELETE FROM Locations");
            logger.info("Удалены все строки из таблиц");
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void createTable() {
        try {
            Connection connection = DriverManager.getConnection(URL, LOGIN, PASS);
            String table = "CREATE TABLE StudyGroups\n" +
                    "(\n" +
                    "  id integer DEFAULT 0 NOT NULL,\n" +
                    "  name varchar(255) NOT NULL,\n" +
                    "  x double precision NOT NULL,\n" +
                    "  y integer NOT NULL,\n" +
                    "  creationDate date NOT NULL,\n" +
                    "  studentsCount bigint NOT NULL,\n" +
                    "  formOfEducation varchar(50) NOT NULL,\n" +
                    "  semester varchar(50),\n" +
                    "  groupAdmin boolean,\n" +
                    "  userLogin varchar(500)" +
                    ");";
            Statement statement = connection.createStatement();
            statement.executeUpdate(table);
            connection.close();
            logger.info("Table StudyGroups created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createTableUsers(){
        try {
            Connection connection = DriverManager.getConnection(URL, LOGIN, PASS);
            String request = "CREATE TABLE Users\n" +
                    "(\n" +
                    "  userLogin varchar(500) DEFAULT 0 NOT NULL,\n" +
                    "  password varchar(500) NOT NULL,\n" +
                    "  hash bytea NOT NULL\n" +
                    ");";
            Statement statement = connection.createStatement();
            statement.executeUpdate(request);
            connection.close();
            logger.info("Table Users created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createTablePerson() {
        try {
            Connection connection = DriverManager.getConnection(URL, LOGIN, PASS);
            String request = "CREATE TABLE GroupAdmins\n" +
                    "(\n" +
                    "  Person_id integer DEFAULT 0 NOT NULL,\n" +
                    "  nameOfGroupAdmin varchar(500) NOT NULL,\n" +
                    "  height bigint,\n" +
                    "  eyeColor varchar(500),\n" +
                    "  hairColor varchar(500),\n" +
                    "  country varchar(500) NOT NULL,\n" +
                    "  location boolean\n" +
                    ");";
            Statement statement = connection.createStatement();
            statement.executeUpdate(request);
            connection.close();
            logger.info("Table GroupAdmins created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTablePersonLocation() {
        try {
            Connection connection = DriverManager.getConnection(URL, LOGIN, PASS);
            String request = "CREATE TABLE Locations\n" +
                    "(\n" +
                    "  Location_id integer DEFAULT 0 NOT NULL,\n" +
                    "  z float NOT NULL,\n" +
                    "  w double precision NOT NULL,\n" +
                    "  locationOfGroupAdmin varchar(500) NOT NULL\n" +
                    ");";
            Statement statement = connection.createStatement();
            statement.executeUpdate(request);
            connection.close();
            logger.info("Table Locations created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createSequence() {
        try {
            Connection connection = DriverManager.getConnection(URL, LOGIN, PASS);
            String sequence = "CREATE SEQUENCE element_id INCREMENT BY 1 START 100";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sequence);
            connection.close();
            logger.info("Последовательност создана");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable(String tableName) {
        try {
            Connection connection = DriverManager.getConnection(URL, LOGIN, PASS);
            String table = "DROP TABLE " + tableName + "\n";
            Statement statement = connection.createStatement();
            statement.executeUpdate(table);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showTablesName() {
        try {
            Connection connection = DriverManager.getConnection(URL, LOGIN, PASS);
            DatabaseMetaData db = connection.getMetaData();
            ResultSet rs = db.getTables(null, null, "%", null);
            while (rs.next()) {
                System.out.println(rs.getString(3));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
