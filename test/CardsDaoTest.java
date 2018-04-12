
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import service.CardsDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class CardsDaoTest {
    private String URL = "jdbc:sqlite:testDB.db";
    @Before
    public void setUp() throws Exception {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(URL)) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS Cards");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Cards" +
                    "(CardId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, CardType TEXT, CardNumber TEXT, Password INTEGER, Amount FLOAT, Id INTEGER);");
            statement.executeUpdate("INSERT INTO Cards (CardType, CardNumber, Password, Amount, Id)" +
                    "VALUES ('VISA', '45678888', 1234, 200, 1);");
            statement.executeUpdate("INSERT INTO Cards (CardType, CardNumber, Password, Amount, Id)" +
                    "VALUES ('MAESTRO', '35679292', 9871, 56000, 2);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddCard() {
        CardsDao cardsDao =new CardsDao();
        cardsDao.addCard("VISA", "45678888", 3333, 500F, 1, URL);
        cardsDao.addCard("VISA", "45678888", 4454, 500F, 2, URL);
        assertTrue(cardsDao.isCardExist("VISA", "45678888", URL));
    }
}