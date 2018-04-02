package controller;

import model.Atm;
import model.Bank;
import model.DataBase;
import view.AtmMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public  class Controller {
    private Atm atm;
    private Bank bank;

    public AtmMenu.Menu getMenu() {
        return menu;
    }

    private AtmMenu.Menu menu;

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

    public DataBase getDataBase() {
        return dataBase;
    }

    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    private DataBase dataBase;
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

    public void setMenu(AtmMenu.Menu menu) {
        this.menu = menu;
    }

    public boolean checkPassword(int passwordNumb) {
        if (Controller.getInstance().getAtm().getCreditCard().getPassword()==passwordNumb){
            return true;
        }else return false;
    }

    public Vector<String> getUsers() {
        if (!Controller.getInstance().getDataBase().getUsers().isEmpty()) {
            return new Vector<>  (Controller.getInstance().getDataBase().getUsers());
        }return null;
    }
}
