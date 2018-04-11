package model;

public class CreditCard {
    private Float amount;
    private Integer password;
    private String cardType;
    private String cardNumber;
    private int testamount;
    private Integer cId;
    private Integer iD;

    public int getTestamount() {
        return testamount;
    }

    public void setTestamount(int testamount) {
        this.testamount = testamount;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public CreditCard(){ }

    public CreditCard(Float amount, Integer password) {
        this.password =password;
        this.amount = amount;
    }

    public CreditCard(Float amount, Integer password, String cardType, String cardNumber, Integer cId, Integer iD) {
        this.amount = amount;
        this.password = password;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.cId = cId;
        this.iD = iD;
    }

    public CreditCard(Float amount, Integer password, String cardType, String cardNumber) {
        this.amount = amount;
        this.password = password;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
    }

    public CreditCard(int testamount, int password){
        this.testamount =testamount;
        this.password = password;
    }

    public CreditCard(String cardNumber, int password) {
        this.cardNumber = cardNumber;
        this.password=password;
        setTestamount(0);
    }

    public Float getAmount() {
        return amount;
    }


    public Integer getPassword() {
        return password;
    }


    public String getCardNumber() {
        return cardNumber;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Integer getiD() {
        return iD;
    }

    public void setiD(Integer iD) {
        this.iD = iD;
    }
}
