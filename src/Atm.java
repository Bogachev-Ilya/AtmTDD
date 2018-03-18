import java.io.InputStream;
import java.util.Scanner;

public class Atm {
    private CreditCard creditCard;
    private InputStream inputStream;
    private Scanner scanner;
    private String bankName;
    private boolean flag=true;

    public Atm(Bank.List bankName) {
        this(System.in);
        this.bankName = bankName.toString();

    }

    public String getBankName() {
        return bankName;
    }

    public void init() {
        User user = new User();
        user.setName("TestName");
        Bank bank = new Bank();
        bank.setName(Bank.List.VTB);
        user.setBanks(Bank.List.VTB);
        long accountNumber = 1234567890L;
        int password = 1234;
        creditCard = bank.emittedCard(user, accountNumber, password);
    }

    public void start() {
        this.insertCard(creditCard);
        if(this.checkPassword()){
            this.startWork();
        }
    }

    public void startWork() {

        while (flag) {
            switch (atmMenu()) {
                case CHECKBALANCE:
                    this.getCreditCard().getAmount();
                    continue;
                case DEPOSIT:
                    System.out.println("Введите сумму внесения");
                    this.depositMoney(Integer.valueOf(scanner.nextLine()));
                    continue;
                case WITHDRAW:
                    System.out.println("Введите сумму снятия");
                    if (this.withdraw(Integer.valueOf(scanner.nextLine()))) {
                        continue;
                    }
                case CANCEL:
                    removeCard();
                    flag=false;
                    break;
            }
        }
    }


    public enum Menu {
        DEPOSIT, WITHDRAW, CANCEL, CHECKBALANCE
    }

    public Atm() {
        this(System.in);

    }

    public Atm(InputStream mockinputStream) {
        this.inputStream = mockinputStream;
        scanner = new Scanner(mockinputStream);
    }

    public boolean checkPassword() {
        int countTries = 0;
        while (countTries != 3) {
            System.out.println("Введите пароль: ");
            String password = scanner.nextLine();
            if (Integer.valueOf(password) == creditCard.getPassword()) {
                return true;
            } else {
                System.out.println("Пароль не верен");
                countTries++;
                continue;
            }
        }
        removeCard();
        return false;
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
        while (true) {
            System.out.println("Введите номер меню:\n 1 - проверить баланс\n 2 - внести наличные\n 3 - снять наличные\n 0 - вернуть карту");
            String menuNumber = scanner.nextLine();
            if (Integer.valueOf(menuNumber) < 0 || Integer.valueOf(menuNumber) > 3) {
                continue;
            }
            switch (Integer.valueOf(menuNumber)) {
                case 1:
                    return Menu.CHECKBALANCE;
                case 2:
                    return Menu.DEPOSIT;
                case 3:
                    return Menu.WITHDRAW;
                case 0:
                    return Menu.CANCEL;
            }
            return null;
        }
    }
}
