
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import service.UsersDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class UsersDaoTest {
    private String URL = "jdbc:sqlite:testDB.db";
    @Before
    public void createTestDB() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(URL)) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS Users");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users" +
                    "(Id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, User TEXT, Bank TEXT, Account TEXT);");
            statement.executeUpdate("INSERT INTO Users (User, Bank, Account)" +
                    "VALUES ('Jonson', 'TestBank1', '333444');");
            statement.executeUpdate("INSERT INTO Users (User, Bank, Account)" +
                    "VALUES ('Vera', 'TestBank2', '555666');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        @Test
        public void testAddUser() {
            UsersDao usersDao = new UsersDao();
            usersDao.addUser("Jonson", "NewBank", "333444", URL);
            usersDao.addUser("Vera", "NewBank", "555666", URL);
            assertTrue(usersDao.isUserExist("Vera",  "555666", URL));
            assertFalse(usersDao.isUserExist("Jonson", "333222", URL));
        }
    }
