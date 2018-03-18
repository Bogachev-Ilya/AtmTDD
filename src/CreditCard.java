public class CreditCard {
    private int amount;
    private int password;
    private long accountNumber;

    public CreditCard(int amount, int password) {
        this.password =password;
        this.amount = amount;
    }

    public CreditCard(long accoutNumber, int password) {
        this.accountNumber =accoutNumber;
        this.password=password;
        setAmount(0);
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

    public long getAccountNumber() {
        return accountNumber;
    }
}
