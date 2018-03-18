public class CreditCard {
    private int amount;
    private int password;

    public CreditCard(int amount, int password) {
        this.password =password;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password){
        this.password =password;
    }
}
