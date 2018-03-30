package controller;

import model.Atm;
import model.Bank;

public  class Controller {
    private Atm atm;

    public Atm getAtm() {
        return atm;
    }

    public void setAtm(Atm atm) {
        this.atm = atm;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    private Bank bank;
    private static final Controller INSTANCE = new Controller();

    private Controller(){}

    public static Controller getInstance(){
        return INSTANCE;
    }

    public boolean insertCard(){
        if (atm.insertCardGui(bank.getCreditCard())){
            return true;
        }else {
            return false;
        }
    }

    public double getBalance() {
        double amount = bank.getCreditCard().getAmount();
        return amount;
    }

    public void setAmount(double amount) {
        bank.getCreditCard().setAmount(amount);
    }
}
