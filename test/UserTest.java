import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testUser(){
        User user = new User();
        user.setName("Petrov");
        String name ="Petrov";
        CreditCard creditCard = new CreditCard();
        user.setBanks(Bank.List.VTB);
        user.setCreditCard(creditCard);
        assertEquals(name, user.getName());
        assertEquals(Bank.List.VTB, user.getBanks());
        assertEquals(creditCard, user.getCreditCard());
    }
}