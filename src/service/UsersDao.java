package service;

import model.ConnectionFactory;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class UsersDao {
    /**метод JOptionPane принимает только массивы или вектора для отображения списка пользователей*/
    private Vector<String> users =new Vector<>();

    /**получить объект пользователь по имени*/

    public User getUserByUserName(String name) {
        try(Connection connection= ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT Id, User, Bank, Account FROM Users " +
                " WHERE User= ?;");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                return extractUserFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("Id"));
        user.setName(resultSet.getString("User"));
        user.setBank(resultSet.getString("Bank"));
        user.setAccount(resultSet.getString("Account"));
        return user;
    }

    /**получить список имен всех пользователей*/

    public Vector<String> getAllUserNames() {
        try(Connection connection = ConnectionFactory.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet usersNameSet = statement.executeQuery("SELECT User From Users;");
            while (usersNameSet.next()) {
                users.add(usersNameSet.getString("User"));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Vector<String> getUsers() {
        return users;
    }

    public void setUsers(Vector<String> users) {
        this.users = users;
    }
}
