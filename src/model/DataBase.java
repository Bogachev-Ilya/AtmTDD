package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    public void initDataBase(String URL){
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
            try (Connection connection = DriverManager.getConnection(URL)) {
               Statement statement = connection.createStatement();
                statement.executeUpdate("DROP TABLE IF EXISTS Users;");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users"  +
                        "(Id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, User TEXT, Bank TEXT, Account INTEGER);");
                statement.executeUpdate("INSERT INTO Users (User, Bank, Account)" +
                        "VALUES ('Jonson', 'SBERBANK', 222244442222);");
                statement.executeUpdate("INSERT INTO Users (User, Bank, Account)" +
                        "VALUES ('Valeriya', 'VTB', 445588889494);");
                statement.executeUpdate("INSERT INTO Users (User, Bank, Account)" +
                        "VALUES ('Brayn', 'ALPHABANK', 332245678332);");
                statement.executeUpdate("INSERT INTO Users (User, Bank, Account)" +
                        "VALUES ('Kirill', 'MOSCOWCREDIT', 656577773344);");



            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

