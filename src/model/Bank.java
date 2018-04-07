package model;

import controller.Controller;

public class Bank {
    private String name;
    private User user;

    public Bank (String name){
        this.name = name;
    }
    public Bank (){
    creditCard=emittedCard();
    }
    public Bank(List name){
        this.setName(name);
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    private CreditCard creditCard;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    private long accountNumber;

    private int password;

    public void setName(List name) {
        this.name = name.toString();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser(String name) {
        return user;
    }

    public CreditCard emittedCard() {
        return new CreditCard();
    }

    public enum List {
        VTB, SBERBANK, MOSCOWCREDIT, ALPHABANK
    }
    public void init() {
        this.setName(Controller.getInstance().getDataBase().getBankName());
        user = new User();
        user.setName(Controller.getInstance().getUserName());
        user.setBank(Controller.getInstance().getDataBase().getBankName());
        creditCard.setPassword(Controller.getInstance().getDataBase().getPassword());
        creditCard.setAmount(Controller.getInstance().getDataBase().getAmount());
        creditCard.setAccountNumber(Controller.getInstance().getCardNumber());
        user.setCreditCard(creditCard);
        this.setUser(user);
    }
}
