package model;

import controller.Controller;

import java.sql.*;


public class DataBase {



    public void initDataBase() {
        if (!(checkDBExist())) {
            try (Connection connection = ConnectionFactory.getConnection()) {
                Statement statement = connection.createStatement();
                /**база данных содержащая имя, счет и банк, являтся primary*/
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users" +
                        "(Id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, User TEXT, Bank TEXT, Account TEXT);");
                PreparedStatement ps = connection.prepareStatement("INSERT INTO Users (User, Bank, Account)" +
                        "VALUES (?, ?, ?);");
                ps.setString(1,"Jonson");
                ps.setString(2,"SBERBANK");
                ps.setString(3, "222244442222");
                ps.setString(1,"Valeriya");
                ps.setString(2,"VTB");
                ps.setString(3, "445588889494");
                ps.setString(1,"Brayn");
                ps.setString(2,"ALPHABANK");
                ps.setString(3, "332245678332");
                ps.setString(1,"Kirill");
                ps.setString(2,"MOSCOWCREDIT");
                ps.setString(3, "656577773344");
                ps.executeUpdate();

                /**таблица с номерами карт пользователей*/
                statement.execute("PRAGMA foreign_keys=on;");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS Cards" +
                        "(CId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, CardType TEXT, CardNumber TEXT, Password INTEGER, Amount FLOAT, Id Integer, FOREIGN KEY (Id) REFERENCES Users(Id) );");
                PreparedStatement ps1 = connection.prepareStatement("INSERT INTO Cards (CardType, CardNumber, Password, Amount, Id )"+
                        "VALUES (?, ?, ?, ?, ?)");
                ps1.setString(1,"VISA");
                ps1.setString(2, "4567888899993456");
                ps1.setInt(3, 1234);
                ps1.setFloat(4, 200F);
                ps1.setInt(5, 1);
                ps1.setString(1,"VISA");
                ps1.setString(2, "3322888899998976");
                ps1.setInt(3, 5678);
                ps1.setFloat(4, 14500F);
                ps1.setInt(5, 2);
                ps1.setString(1,"MasterCard");
                ps1.setString(2, "245688889990876");
                ps1.setInt(3, 7890);
                ps1.setFloat(4, 58700F);
                ps1.setInt(5, 3);
                ps1.setString(1,"MasterCard");
                ps1.setString(2, "456788889994563");
                ps1.setInt(3, 2345);
                ps1.setFloat(4, 67899F);
                ps1.setInt(5, 1);
                ps1.setString(1,"VISA");
                ps1.setString(2, "678988669995454");
                ps1.setInt(3, 3344);
                ps1.setFloat(4, 6900F);
                ps1.setInt(5, 4);
                ps1.executeUpdate();

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





    /**метод обновляет данные по карте в базе данных, после совершения всех операций*/
    public void updateCardAmount(float amount) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement=connection.prepareStatement("UPDATE Cards SET Amount=? "+
            "WHERE CardNumber="+Controller.getInstance().getCardNumber());
            statement.setFloat(1, amount);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**создать новую базу данных*/
    public void createNewDataBase(String DBName){
        String url = "jdbc: sqlite: users/iliabogachev/IdeaProjects/AtmTDD/"+ DBName;
        try (Connection connection = DriverManager.getConnection(url)){
            if (connection!=null){
                DatabaseMetaData databaseMetaData = connection.getMetaData();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

