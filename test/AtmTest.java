import org.junit.Test;

import static org.junit.Assert.*;

public class AtmTest {

    @Test
    public void testInsertCreditCard(){
        CreditCard creditCard = new CreditCard(0);
        Atm atm = new Atm();
        atm.insertCard(creditCard);
        assertEquals(creditCard, atm.getCreditCard());
    }
}