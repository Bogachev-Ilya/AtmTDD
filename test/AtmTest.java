import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

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

    @Test
    public void testWithdrawMoney(){
        CreditCard creditCard = new CreditCard(0);
        Atm atm = new Atm();
        atm.insertCard(creditCard);
        atm.depositMoney(100);
        atm.withdraw(40);
        assertEquals(60, atm.getCreditCard().getAmount());
        assertFalse( atm.withdraw(80));
    }

    @Test
    public void testAtmCheckBalanceMenu() throws UnsupportedEncodingException {
        String mockInput1 = "1\n";
        InputStream mockinputStream = new ByteArrayInputStream(mockInput1.getBytes(StandardCharsets.UTF_8.name()));
        CreditCard creditCard = new CreditCard(0);
        Atm atm = new Atm(mockinputStream);
        atm.insertCard(creditCard);
        assertEquals(Atm.Menu.CHECKBALANCE, atm.atmMenu());
    }
    @Test
    public void testAtmDepositMenu() throws UnsupportedEncodingException {
        String mockInput2 = "2\n";
        InputStream mockinputStream = new ByteArrayInputStream(mockInput2.getBytes(StandardCharsets.UTF_8.name()));
        CreditCard creditCard = new CreditCard(0);
        Atm atm = new Atm(mockinputStream);
        atm.insertCard(creditCard);
        assertEquals(Atm.Menu.DEPOSIT, atm.atmMenu());
    }

}