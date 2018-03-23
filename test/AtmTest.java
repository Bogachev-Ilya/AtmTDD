import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import static org.junit.Assert.*;

public class AtmTest {

    @Test
    public void testInsertCreditCard() throws UnsupportedEncodingException {
        String mockInput1 = "0\n";
        InputStream mockinputStream = new ByteArrayInputStream(mockInput1.getBytes(StandardCharsets.UTF_8.name()));
        CreditCard creditCard = new CreditCard(0, 1234);
        Atm atm = new Atm(mockinputStream);
        atm.insertCard(creditCard);
        assertEquals(creditCard, atm.getCreditCard());
        atm.insertCard(creditCard);
        assertEquals(null,atm.getCreditCard());

    }

    @Test
    public void testRemoveCreditCard(){
        CreditCard creditCard = new CreditCard(0, 1234);
        Atm atm = new Atm();
        assertEquals(null, atm.getCreditCard());
    }

    @Test
    public void testDepositMoney(){
        CreditCard creditCard = new CreditCard(0, 1234);
        Atm atm = new Atm();
        atm.insertCard(creditCard);
        assertTrue(atm.depositMoney(35));
        assertEquals(35, atm.getCreditCard().getAmount());
        assertFalse(atm.depositMoney(-45));
    }

    @Test
    public void testWithdrawMoney(){
        CreditCard creditCard = new CreditCard(0, 1234);
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
        CreditCard creditCard = new CreditCard(0, 1234);
        Atm atm = new Atm(mockinputStream);
        atm.insertCard(creditCard);
        assertEquals(Atm.Menu.CHECKBALANCE, atm.atmMenu());
    }
    @Test
    public void testAtmDepositMenu() throws UnsupportedEncodingException {
        String mockInput2 = "2\n";
        InputStream mockinputStream = new ByteArrayInputStream(mockInput2.getBytes(StandardCharsets.UTF_8.name()));
        CreditCard creditCard = new CreditCard(0, 1234);
        Atm atm = new Atm(mockinputStream);
        atm.insertCard(creditCard);
        assertEquals(Atm.Menu.DEPOSIT, atm.atmMenu());
    }

    @Test
    public void testAtmWithdrawMenu() throws UnsupportedEncodingException {
        String mockInput3 = "3\n";
        InputStream mockinputStream = new ByteArrayInputStream(mockInput3.getBytes(StandardCharsets.UTF_8.name()));
        CreditCard creditCard = new CreditCard(0, 1234);
        Atm atm = new Atm(mockinputStream);
        atm.insertCard(creditCard);
        assertEquals(Atm.Menu.WITHDRAW, atm.atmMenu());
    }

    @Test
    public void testCancelMenu () throws UnsupportedEncodingException {
        String mockInput4 = "0\n";
        InputStream mockinputStream = new ByteArrayInputStream(mockInput4.getBytes(StandardCharsets.UTF_8.name()));
        CreditCard creditCard = new CreditCard(0, 1234);
        Atm atm = new Atm(mockinputStream);
        atm.insertCard(creditCard);
        assertEquals(Atm.Menu.CANCEL, atm.atmMenu());
    }
    
   @Test
    public void testPassword() throws UnsupportedEncodingException {
       String mockInput = "1234\n";
       InputStream mockinputStream = new ByteArrayInputStream(mockInput.getBytes(StandardCharsets.UTF_8.name()));
       CreditCard creditCard = new CreditCard(0, 1234);
       Atm atm = new Atm((mockinputStream));
       atm.insertCard(creditCard);
       assertTrue(atm.checkPassword());
   }

   @Test
    public void testIncorrectPasswordthreetimes() throws UnsupportedEncodingException {
       String mockInput = "2222\n4567\n9873";
       InputStream mockinputStream = new ByteArrayInputStream(mockInput.getBytes(StandardCharsets.UTF_8.name()));
       CreditCard creditCard = new CreditCard(0, 1234);
       Atm atm = new Atm((mockinputStream));
       atm.insertCard(creditCard);
       assertFalse(atm.checkPassword());
       assertEquals(null, atm.getCreditCard());
   }
   @Test
    public void testInitAtm(){
        User user = new User();
        user.setName("TestName");
        Bank bank = new Bank();
        bank.setName(Bank.List.VTB);
        user.setBanks(Bank.List.VTB);
        long accountNumber = 1234567890L;
        int password =1234;
        bank.emittedCard(user, accountNumber,password);
        Atm atm = new Atm(Bank.List.VTB);
        assertEquals("VTB", atm.getBankName());
   }
   @Test
    public void testATM() throws UnsupportedEncodingException {
       String mockInput = "1234\n1\n2\n400\n3\n200\n1\n0\n";
       InputStream mockinputStream = new ByteArrayInputStream(mockInput.getBytes(StandardCharsets.UTF_8.name()));
       User user = new User();
       user.setName("TestName");
       Bank bank = new Bank();
       bank.setName(Bank.List.VTB);
       user.setBanks(Bank.List.VTB);
       long accountNumber = 1234567890L;
       int password =1234;
       CreditCard creditCard =  bank.emittedCard(user, accountNumber,password);
       Atm atm = new Atm(mockinputStream);
       atm.insertCard(creditCard);
       assertTrue(atm.checkPassword());
       atm.startWork();
       assertEquals(null, atm.getCreditCard());
       atm.insertCard(creditCard);
       assertEquals(200, atm.getCreditCard().getAmount());

   }
}
