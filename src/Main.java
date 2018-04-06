import controller.Controller;
import model.Atm;
import model.Bank;
import model.DataBase;
import view.AtmMenu;

public class Main {

    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Atm atm = new Atm(Bank.List.VTB);
        DataBase dataBase = new DataBase();
        String URL = "jdbc:sqlite:banks.db";
        Controller.getInstance().setURL(URL);
        dataBase.initDataBase(URL);
        AtmMenu atmMenu = new AtmMenu();
        Bank bank = new Bank();
        Controller.getInstance().setBank(bank);
        Controller.getInstance().setAtm(atm);
        Controller.getInstance().setDataBase(dataBase);
        atmMenu.selectUserName();
    }
}
