import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testUser(){
        User user = new User();
        user.setName("Petrov");
        String name ="Petrov";
        user.setBanks(Bank.List.VTB);
        assertEquals(name, user.getName());
        assertEquals(Bank.List.VTB, user.getBanks());
    }

}