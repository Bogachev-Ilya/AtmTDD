package model;

import java.sql.*;

public class DataBase {

    public void initDataBase(String URL){

            try (Connection connection = DriverManager.getConnection(URL)) {
               Statement statement = connection.createStatement();
               /**база данных содержащая имя, счет и банк, являтся primary*/
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
                /**таблица с номерами карт пользователей*/
                statement.execute("PRAGMA foreign_keys=on;");
                statement.executeUpdate("DROP TABLE IF EXISTS Cards");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS Cards"  +
                        "(CId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, CardType TEXT, CardNumber INTEGER, Password INTEGER, Amount FLOAT, Id Integer, FOREIGN KEY (Id) REFERENCES Users(Id) );");
                statement.executeUpdate("INSERT INTO Cards (CardType, CardNumber, Password, Amount, Id )" +
                        "VALUES ('VISA', 4567888899993456, 1234, 200, 1)");
                statement.executeUpdate("INSERT INTO Cards (CardType, CardNumber, Password, Amount, Id )" +
                        "VALUES ('VISA', 3322888899998976, 5678, 14500, 2)");
                statement.executeUpdate("INSERT INTO Cards (CardType, CardNumber, Password, Amount, Id )" +
                        "VALUES ('MasterCard', 245688889990876, 7890, 58700, 3)");
                statement.executeUpdate("INSERT INTO Cards (CardType, CardNumber, Password, Amount, Id )" +
                        "VALUES ('MasterCard', 456788889994563, 2345, 67899, 1)");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void selUserCard(String URL, String UserName){
            try (Connection connection = DriverManager.getConnection(URL)) {
                PreparedStatement statement = connection.prepareStatement("SELECT CardType, CardNumber, Password, Amount FROM Cards, Users " +
                        " WHERE Users.Id=Cards.Id AND User= ?;");
                 statement.setString(1, UserName);
                 ResultSet resultSet = statement.executeQuery();
                 int count = resultSet.getInt(1);
                System.out.printf("Card data for user %s is:\n", UserName);
                while (resultSet.next()){
                    String cardType = resultSet.getString("CardType");
                    long cardNumber =resultSet.getLong("CardNumber");
                    int password =resultSet.getInt("Password");
                    long amount =resultSet.getLong("Amount");
                    System.out.printf("Card Type: %s, card number: %d, password: %d, amount: %d\n", cardType, cardNumber, password, amount);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

