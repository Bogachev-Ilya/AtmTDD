package controller;

import model.Atm;
import model.Bank;
import model.DataBase;
import view.AtmMenu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Controller {
    private Atm atm;
    private Bank bank;
    private AtmMenu.Menu menu;
    private DataBase dataBase;
    private String userName;
    private String cardNumber;
    private String URL;
    private String bankName;

    private Controller() {
    }

    private static final Controller INSTANCE = new Controller();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String  getCardNumber() {
        return cardNumber;
    }

    public AtmMenu.Menu getMenu() {
        return menu;
    }

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

    public static Controller getInstance() {
        return INSTANCE;
    }

    public boolean insertCard() {
        if (Controller.getInstance().getAtm().insertCardGui(Controller.getInstance().getBank().getCreditCard())) {
            return true;
        } else {
            return false;
        }
    }

    public double getBalance() {
        double amount = bank.getCreditCard().getAmount();
        return amount;
    }

    public void setAmount(float amount) {
        bank.getCreditCard().setAmount(amount);
    }

    public void setMenu(AtmMenu.Menu menu) {
        this.menu = menu;
    }

    public boolean checkPassword(int passwordNumb) {
        if (Controller.getInstance().getAtm().getCreditCard().getPassword() == passwordNumb) {
            return true;
        } else return false;
    }

    public Vector<String> getUsers() {

        if (!Controller.getInstance().getDataBase().getUsers().isEmpty()) {
            return new Vector<>(Controller.getInstance().getDataBase().getUsers());
        }
        return null;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getURL() {
        return URL;
    }


    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
