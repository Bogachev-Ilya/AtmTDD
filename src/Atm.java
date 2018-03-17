import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Atm {
    private CreditCard creditCard;
    private InputStream inputStream;
    private Scanner scanner;

    public enum Menu{
        DEPOSIT, CHECKBALANCE
    }

    public Atm(){
        this(System.in);

    }

    public Atm(InputStream mockinputStream) {
        this.inputStream = mockinputStream;
        scanner=new Scanner(mockinputStream);
    }

    public void insertCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public boolean removeCard() {
        this.creditCard = null;
        return true;
    }

    public void depositMoney(int money) {
        this.creditCard.setAmount(money);
    }

    public boolean withdraw(int money) {
        if (this.creditCard.getAmount() >= money) {
            this.creditCard.setAmount(this.creditCard.getAmount() - money);
            return true;
        } else {
            System.out.println("На карте недостаточно средств для снятия, пожалуйста уменьшите сумму и повторите запрос");
            return false;
        }
    }

    public Menu atmMenu() {
        String menuNumber=scanner.nextLine();
        switch (Integer.valueOf(menuNumber)){
            case 1:
                return Menu.CHECKBALANCE;
            case 2:
                return Menu.DEPOSIT;
        }
        return null;
    }
}
