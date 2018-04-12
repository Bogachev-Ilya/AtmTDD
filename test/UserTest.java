import model.CreditCard;
import model.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testUser(){
        User user = new User();
        user.setName("Petrov");
        String name ="Petrov";
        CreditCard creditCard = new CreditCard();
        user.setBank("VTB");
        user.setCreditCard(creditCard);
        assertEquals(name, user.getName());
        assertEquals("VTB", user.getBank());
    }
}