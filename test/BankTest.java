import org.junit.Test;

import static org.junit.Assert.*;

public class BankTest {

    @Test
    public void testCreateBank(){
        Bank bank = new Bank();
        bank.setName(Bank.List.SBERBANK);
        User user = new User();
        user.setName("Petrov");
        bank.setUser(user);
        assertEquals(user, bank.getUser("Petrov"));

    }
}