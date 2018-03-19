import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

public class Atm {
    private CreditCard creditCard;
    private InputStream inputStream;
    private Scanner scanner;
    private String bankName;
    private boolean flag=true;

    public Atm() {
        this(System.in);

    }

    public Atm(InputStream mockinputStream) {
        this.inputStream = mockinputStream;
        scanner = new Scanner(mockinputStream);
    }

    public Atm(Bank.List bankName) {
        this(System.in);
        this.bankName = bankName.toString();

    }

    public String getBankName() {
        return bankName;
    }

    public void init() {
        User user = new User();

        user.setName("Petrov");
        Bank bank = new Bank();
        bank.setName(Bank.List.VTB);
        user.setBanks(Bank.List.VTB);
        long accountNumber = 1234567890L;
        int password = 1234;
        creditCard = bank.emittedCard(user, accountNumber, password);
    }

    public void start() {
        insertCard(creditCard);
        if(checkPassword()){
            startWork();
        }
    }

    public void startWork() {

        while (flag) {
            switch (atmMenu()) {
                case CHECKBALANCE:
                    System.out.println("Баланс на карте: " + this.getCreditCard().getAmount());
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
        if (getCreditCard()==null){
            this.creditCard = creditCard;
        }else {
            System.out.println("Карта уже вставлена, введите 0 чтобы извлечть карту: ");
            while (true){
                String number =scanner.nextLine();
                if (Integer.valueOf(number)==0){
                    removeCard();
                    break;
                }else {
                    continue;
                }
            }
        }
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void removeCard() {
        this.creditCard = null;
    }

    public boolean depositMoney(int money) {
        if (money>=0){
           this.creditCard.setAmount(money);
            return true;
        }else {
          return false;
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atm atm = (Atm) o;
        return Objects.equals(creditCard, atm.creditCard) && Objects.equals(bankName, atm.bankName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(creditCard, bankName);
    }
}
