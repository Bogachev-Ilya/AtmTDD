import java.util.Objects;

public class User {
    private String name;
    private Bank.List bank;
    private CreditCard creditCard;

    public Bank.List getBank() {
        return bank;
    }

    public void setBank(Bank.List bank) {
        this.bank = bank;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBanks(Bank.List bank) {
        this.bank = bank;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && bank == user.bank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, bank);
    }

    public Bank.List getBanks() {
        return bank;
    }
}
