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
        Bank bank = new Bank(Bank.List.VTB);
        Atm atm = new Atm(Bank.List.VTB);
        bank.init();
        DataBase dataBase = new DataBase();
        String URL = "jdbc:sqlite:banks.db";
        dataBase.initDataBase(URL);
        AtmMenu atmMenu = new AtmMenu();
        Controller.getInstance().setURL(URL);
        Controller.getInstance().setBank(bank);
        Controller.getInstance().setAtm(atm);
        Controller.getInstance().setDataBase(dataBase);
         atmMenu.selectUserName();
        //dataBase.selUsers("jdbc:sqlite:banks.db");
        //dataBase.selUserCard("jdbc:sqlite:banks.db",Controller.getInstance().getUserName());
        // atmMenu.insertCardWindow();

    }
}
