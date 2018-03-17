public class Atm {
    private CreditCard creditCard;

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
}
