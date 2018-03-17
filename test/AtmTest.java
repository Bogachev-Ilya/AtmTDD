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

    @Test
    public void testRemoveCreditCard(){
        CreditCard creditCard = new CreditCard(0);
        Atm atm = new Atm();
        assertTrue(atm.removeCard());
        assertEquals(null, atm.getCreditCard());
    }

    @Test
    public void testDepositMoney(){

        CreditCard creditCard = new CreditCard(0);
        Atm atm = new Atm();
        atm.insertCard(creditCard);
        atm.depositMoney(35);
        assertEquals(35, atm.getCreditCard().getAmount());
    }

}