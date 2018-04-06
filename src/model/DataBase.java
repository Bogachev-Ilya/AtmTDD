package model;

import controller.Controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DataBase {
    private Vector<String> users;
    private String[] userCardNumbs;
    private Vector<Float> userCardAmounts = new Vector<>();
    private List <Integer> passwords=new ArrayList<>();
    private List<String> cardTypes=new ArrayList<>();
    private List<String> bankNames=new ArrayList<>();
    private String bankName;
    private Integer password;
    private String cardType;
    private Float amount;

    public Object[] getUserCardNumbs() {
        return userCardNumbs;
    }

    public void setUserCardNumbs(String[] userCardNumbs) {
        this.userCardNumbs = userCardNumbs;
    }

    public Vector<Float> getUserCardAmount() {
        return userCardAmounts;
    }

    public void setUserCardAmount(Vector<Float> userCardAmount) {
        this.userCardAmounts = userCardAmount;
    }

    public Vector<String> getUsers() {
        return users;
    }

    public void setUsers(Vector<String> users) {
        this.users = users;
    }

    public String getBankName() {
        return bankName;
    }

    public Integer getPassword() {
        return password;
    }

    public String getCardType() {
        return cardType;
    }

    public Float getAmount() {
        return amount;
    }

    public void initDataBase(String URL) {
        if (!(checkDBExist())) {
            try (Connection connection = DriverManager.getConnection(URL)) {
                Statement statement = connection.createStatement();
                /**база данных содержащая имя, счет и банк, являтся primary*/
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users" +
                        "(Id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, User TEXT, Bank TEXT, Account TEXT);");
                statement.executeUpdate("INSERT INTO Users (User, Bank, Account)" +
                        "VALUES ('Jonson', 'SBERBANK', '222244442222');");
                statement.executeUpdate("INSERT INTO Users (User, Bank, Account)" +
                        "VALUES ('Valeriya', 'VTB', '445588889494');");
                statement.executeUpdate("INSERT INTO Users (User, Bank, Account)" +
                        "VALUES ('Brayn', 'ALPHABANK', '332245678332');");
                statement.executeUpdate("INSERT INTO Users (User, Bank, Account)" +
                        "VALUES ('Kirill', 'MOSCOWCREDIT', '656577773344');");
                /**таблица с номерами карт пользователей*/
                statement.execute("PRAGMA foreign_keys=on;");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS Cards" +
                        "(CId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, CardType TEXT, CardNumber TEXT, Password INTEGER, Amount FLOAT, Id Integer, FOREIGN KEY (Id) REFERENCES Users(Id) );");
                statement.executeUpdate("INSERT INTO Cards (CardType, CardNumber, Password, Amount, Id )" +
                        "VALUES ('VISA', '4567888899993456', 1234, 200, 1)");
                statement.executeUpdate("INSERT INTO Cards (CardType, CardNumber, Password, Amount, Id )" +
                        "VALUES ('VISA', '3322888899998976', 5678, 14500, 2)");
                statement.executeUpdate("INSERT INTO Cards (CardType, CardNumber, Password, Amount, Id )" +
                        "VALUES ('MasterCard', '245688889990876', 7890, 58700, 3)");
                statement.executeUpdate("INSERT INTO Cards (CardType, CardNumber, Password, Amount, Id )" +
                        "VALUES ('MasterCard', '456788889994563', 2345, 67899, 1)");
                statement.executeUpdate("INSERT INTO Cards (CardType, CardNumber, Password, Amount, Id )" +
                        "VALUES ('VISA', '678988669995454', 3344, 67899, 4)");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean checkDBExist() {
        try {
            Class.forName("org.sqlite.JDBC"); //Register JDBC Driver
            Connection conn = DriverManager.getConnection(Controller.getInstance().getURL());
            Statement statement = conn.createStatement();
            ResultSet resultSet =  statement.executeQuery("SELECT * FROM sqlite_master WHERE type='table';");
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
        return false;
    }


    public void selUserCard(String URL, String UserName) {
        try (Connection connection = DriverManager.getConnection(URL)) {
            PreparedStatement statement1 = connection.prepareStatement("SELECT CardType, CardNumber, Password, Amount FROM Cards, Users " +
                    " WHERE Users.Id=Cards.Id AND User= ?;");
            statement1.setString(1, UserName);
            ResultSet resultSetTemp = statement1.executeQuery();
            /**узнать количество строк*/
            int count = 0;
            /**задать размер массива*/
            int i = 0;
            System.out.printf("Card data for user %s is:\n", UserName);
            /**узнать количество строк массива*/
            while (resultSetTemp.next()) {
                count++;
            }
            //необходим повторный вызор,так как предыдущий зарыт после перебора
            userCardNumbs = new String[count];
            PreparedStatement statement = connection.prepareStatement("SELECT CardType, CardNumber, Password, Amount, Bank FROM Cards, Users " +
                    " WHERE Users.Id=Cards.Id AND User= ?;");
            statement.setString(1, UserName);
            ResultSet userCardsDataSet = statement.executeQuery();

            while (userCardsDataSet.next()) {
                cardTypes.add(userCardsDataSet.getString("CardType"));
                userCardNumbs[i] = (userCardsDataSet.getString("CardNumber"));
                passwords.add(userCardsDataSet.getInt("Password"));
                userCardAmounts.add(userCardsDataSet.getFloat("Amount"));
                bankNames.add("Bank");
                i++;
            }
            /**перебрать коллекцию карт и установить значения параметров карты*/
            if (userCardNumbs.length!=0){
                    int cardPosition = 0;
                /**нати позицию карты в массиве, и по этой позиции выбрать остальные значения карт*/
                for (int j = 0; j < userCardNumbs.length; j++) {
                    if (userCardNumbs[j].equals(Controller.getInstance().getCardNumber())){
                        cardPosition=j;
                    }
                }
                cardType = cardTypes.get(cardPosition);
                password = passwords.get(cardPosition);
                amount = userCardAmounts.get(cardPosition);
                bankName =bankNames.get(cardPosition);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selUsers(String URL) {
        try (Connection connection = DriverManager.getConnection(URL)) {
            Statement statement = connection.createStatement();
             ResultSet usersNameSet = statement.executeQuery("SELECT User From Users;");
            users = new Vector<>();
            while (usersNameSet.next()) {
                users.add(usersNameSet.getString("User"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

