package service;

import model.ConnectionFactory;
import model.CreditCard;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardsDao implements Serializable{
    private List<CreditCardDto> creditCards = new ArrayList<>();

    /**получить коллекцию карт пользователя по номеру iD*/
    public List<CreditCardDto> getAllCardsForUser(Integer UseriD) {
        try(Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement preparedStatement =connection.prepareStatement
                    ("SELECT CId, CardType, CardNumber, Password, Amount, Id FROM Cards WHERE Id=?");
            preparedStatement.setInt(1, UseriD);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                CreditCardDto creditCardDto = new CreditCardDto
                        (rs.getInt("CId"), rs.getString("CardType"),
                                rs.getString("CardNumber"), rs.getInt("Password"),
                                rs.getFloat("Amount"), rs.getInt("Id"));
                creditCards.add(creditCardDto);
            }
            return creditCards;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**получить коллекцию карт пользователя по номеру iD за исключением уже выбранной карты*/
    public List<CreditCardDto> getCardsForUser(Integer UseriD, String cardNumber) {
        try(Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement preparedStatement =connection.prepareStatement
                    ("SELECT CId, CardType, CardNumber, Password, Amount, Id FROM Cards WHERE Id=? AND CardNumber<>?");
            preparedStatement.setInt(1, UseriD);
            preparedStatement.setString(2, cardNumber);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                CreditCardDto creditCardDto = new CreditCardDto
                        (rs.getInt("CId"), rs.getString("CardType"),
                                rs.getString("CardNumber"), rs.getInt("Password"),
                                rs.getFloat("Amount"), rs.getInt("Id"));
                creditCards.add(creditCardDto);
            }
            return creditCards;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean updateCardAmount(Float amount, String cardNumber) {
        try(Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement ps = connection.prepareStatement("UPDATE Cards SET Amount=? "+
                    "WHERE CardNumber=?;");
            ps.setFloat(1, amount);
            ps.setString(2, cardNumber);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public CreditCard getSelectedCard(String selectedCardNumber) {
        try(Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement ps = connection.prepareStatement("SELECT CId, CardType, CardNumber, Password, Amount, Id FROM Cards WHERE CardNumber=?");
            ps.setString(1, selectedCardNumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return extractCardFromResult(rs);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private CreditCard extractCardFromResult(ResultSet rs) throws SQLException {
        CreditCard creditCard = new CreditCard();
        creditCard.setcId(rs.getInt("CId"));
        creditCard.setCardType(rs.getString("CardType"));
        creditCard.setCardNumber(rs.getString("CardNumber"));
        creditCard.setPassword(rs.getInt("Password"));
        creditCard.setAmount(rs.getFloat("Amount"));
        creditCard.setiD(rs.getInt("Id"));
        return creditCard;
    }

    public void addCard(String cardType, String cardNumber, Integer password, Float amount, Integer userId, String URL){
        try (Connection connection = DriverManager.getConnection(URL)) {
            if (!isCardExist(cardType, cardNumber, URL)) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Cards (CardType, CardNumber, Password, Amount, Id )" +
                    "VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, cardType);
            ps.setString(2, cardNumber);
            ps.setInt(3, password);
            ps.setFloat(4, amount);
            ps.setInt(5, userId);
            ps.executeUpdate();
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isCardExist(String cardType, String cardNumber, String url) {
        try(Connection connection =DriverManager.getConnection(url)) {
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT (*) FROM Cards" +
                    " WHERE (CardType = ? AND CardNumber = ?)");
            ps.setString(1, cardType);
            ps.setString(2, cardNumber);
            ResultSet rs=ps.executeQuery();
            if(rs.getInt("CardNumber")>0){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
