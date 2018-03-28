import org.junit.Test;

import static org.junit.Assert.*;

public class BankTest {

    @Test
    public void testCreateBank(){
        Bank bank = new Bank(Bank.List.SBERBANK);
        User user = new User();
        user.setName("Petrov");
        bank.setUser(user);
        assertEquals(user, bank.getUser("Petrov"));

    }
    @Test
    public void testEmittedCard(){
        Bank bank = new Bank(Bank.List.SBERBANK);
        User user = new User();
        user.setName("Petrov");
        bank.setUser(user);
        int password = 1234;
        long accoutNumber = 3456_7549_3452L;
        CreditCard creditCard = bank.emittedCard();
        creditCard.setAccountNumber(accoutNumber);
        creditCard.setAmount(0);
        creditCard.setPassword(password);
        assertEquals(password, creditCard.getPassword());
        assertEquals(accoutNumber, creditCard.getAccountNumber());
        assertEquals(0, creditCard.getAmount());
    }
}