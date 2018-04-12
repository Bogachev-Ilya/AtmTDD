import controller.Controller;
import model.Atm;
import model.Bank;
import model.DataBase;
import view.AtmMenu;

public class Main {

    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        dataBase.initDataBase();
        AtmMenu atmMenu = new AtmMenu();
        atmMenu.selectUserName();

    }
}
