package model;

import controller.Controller;
import service.CardsDao;
import service.UsersDao;

import java.sql.*;


public class DataBase {


    public void initDataBase() {
        if (!(checkDBExist())) {
            try (Connection connection = ConnectionFactory.getConnection()) {
                Statement statement = connection.createStatement();
                /**база данных содержащая имя, счет и банк, являтся primary*/
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users" +
                        "(Id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, User TEXT, Bank TEXT, Account TEXT);");
                /**добавляем всех пользователей через UsersDao*/
                UsersDao usersDao = new UsersDao();
                usersDao.addUser("Jonson", "SBERBANK", "222244442222", ConnectionFactory.URL);
                usersDao.addUser("Valeriya", "VTB", "445588889494", ConnectionFactory.URL);
                usersDao.addUser("Brayn", "ALPHABANK", "332245678332", ConnectionFactory.URL);
                usersDao.addUser("Kirill", "MOSCOWCREDIT", "656577773344", ConnectionFactory.URL);
                /**таблица с номерами карт пользователей*/
                statement.execute("PRAGMA foreign_keys=on;");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS Cards" +
                        "(CId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, CardType TEXT, CardNumber TEXT, Password INTEGER, Amount FLOAT, Id Integer, FOREIGN KEY (Id) REFERENCES Users(Id) );");
                CardsDao cardsDao = new CardsDao();
                cardsDao.addCard("VISA", "4567888899993456", 1234, 200F, 1, ConnectionFactory.URL);
                cardsDao.addCard("VISA", "3322888899998976", 5678, 45232F, 2, ConnectionFactory.URL);
                cardsDao.addCard("MasterCard", "245688889990876", 7890, 79F, 3, ConnectionFactory.URL);
                cardsDao.addCard("MasterCard", "456788889994563", 2345, 67899F, 1, ConnectionFactory.URL);
                cardsDao.addCard("VISA", "678988669995454", 3344, 1000000F, 4, ConnectionFactory.URL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean checkDBExist() {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM sqlite_master WHERE type='table';");
            while (resultSet.next()) {
                String databaseName = resultSet.getString(1);
                if (databaseName.equals("table")) {
                    return true;
                }
            }
            resultSet.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        /**создать базу данных*/
        createNewDataBase("banks.db");
        return false;
    }

    /**
     * создать новую базу данных
     */
    public void createNewDataBase(String DBName) {
        String url = "jdbc: sqlite: users/iliabogachev/IdeaProjects/AtmTDD/" + DBName;
        try (Connection connection = DriverManager.getConnection(url)) {
            if (connection != null) {
                DatabaseMetaData databaseMetaData = connection.getMetaData();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

