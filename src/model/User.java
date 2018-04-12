package model;

import java.util.Objects;

public class User {
    private String name;
    private String bank;
    private Integer id;
    private String account;
    private CreditCard creditCard;
    private Bank.List banks;

    public User(){ }

    public User(String name, String bank, Integer id, String account) {
        this.name = name;
        this.bank = bank;
        this.id = id;
        this.account = account;
    }

    public User(String name, String bank, String account) {
        this.name = name;
        this.bank = bank;
        this.account = account;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
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


    public void setBanks(Bank.List banks) {
        this.banks = banks;
    }
}