import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testUser(){
        User user = new User();
        user.setName("Petrov");
        user.setBanks(Bank.List.VTB);
    }

}