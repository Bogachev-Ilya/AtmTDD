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
}
