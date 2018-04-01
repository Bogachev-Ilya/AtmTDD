package model;

public class CreditCard {
    private double amount;
    private int password;

    public int getTestamount() {
        return testamount;
    }

    public void setTestamount(int testamount) {
        this.testamount = testamount;
    }

    private int testamount;

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    private long accountNumber;

    public CreditCard(){

    }

    public CreditCard(double amount, int password) {
        this.password =password;
        this.amount = amount;
    }

    public CreditCard(int testamount, int password){
        this.testamount =testamount;
        this.password = password;
    }

    public CreditCard(long accoutNumber, int password) {
        this.accountNumber =accoutNumber;
        this.password=password;
        setAmount(0);
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
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
