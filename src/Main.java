import controller.Controller;
import model.Atm;
import model.Bank;
import model.DataBase;
import view.AtmMenu;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank(Bank.List.VTB);
        Atm atm = new Atm(Bank.List.VTB);
        bank.init();
        DataBase dataBase = new DataBase();
        dataBase.initDataBase("jdbc:sqlite:banks.db");
        AtmMenu atmMenu = new AtmMenu();
        Controller.getInstance().setBank(bank);
        Controller.getInstance().setAtm(atm);
        atmMenu.insertCardWindow();
    }
}
