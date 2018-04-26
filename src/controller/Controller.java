package controller;

import model.*;
import view.AtmMenu;

import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Controller {
    private Atm atm;
    private AtmMenu.Menu menu;
    private DataBase dataBase;
    private String userName;
    private String cardNumber;
    private String bankName;
    private int password;
    private float amount;
    private String cardType;
    private User user;
    private CreditCard creditCard;
    private Bank bank;
    private Vector<String> usersName;
    private Object[] userCardsList;
    private boolean flag;

    private volatile static Controller INSTANCE;

    private Controller() {}
    public static Controller getInstance() {
        if (INSTANCE ==null){
            synchronized (Controller.class){
                if (INSTANCE ==null){
                    INSTANCE =new Controller();
                }
            }
        }
        return INSTANCE;
    }

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

    public DataBase getDataBase() {
        return dataBase;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public float getAmount() {
        return amount;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
    }



    public boolean insertCard() {
        if (atm.insertCardGui(creditCard)) {
            return true;
        } else {
            return false;
        }
    }

    public double getBalance() {
        float amount = atm.getCreditCard().getAmount();
        return amount;
    }

    public void setAmount(float amount) {
        atm.getCreditCard().setAmount(amount);
    }

    public void setMenu(AtmMenu.Menu menu) {
        this.menu = menu;
    }

    public boolean checkPassword(int passwordNumb) {
        if (atm.getCreditCard().getPassword() == passwordNumb) {
            return true;
        } else return false;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public void initBank() {
        bank = new Bank();
        bank.init();
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void initAtm() {
        atm = new Atm();
    }

    public Vector<String> getUsersName() {
        return usersName;
    }

    public void setUsersName(Vector<String> usersName) {
        this.usersName = usersName;
    }

    public void setUserCardsList(Object[] userCardsList) {
        this.userCardsList = userCardsList;
    }

    public Object[] getUserCardsList() {
        return userCardsList;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
