package model;

public class CreditCard {
    private float amount;
    private int password;

    public int getTestamount() {
        return testamount;
    }

    public void setTestamount(int testamount) {
        this.testamount = testamount;
    }

    private int testamount;

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    private String accountNumber;

    public CreditCard(){

    }

    public CreditCard(float amount, int password) {
        this.password =password;
        this.amount = amount;
    }

    public CreditCard(int testamount, int password){
        this.testamount =testamount;
        this.password = password;
    }

    public CreditCard(String accoutNumber, int password) {
        this.accountNumber =accoutNumber;
        this.password=password;
        setAmount(0);
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password){
        this.password =password;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
