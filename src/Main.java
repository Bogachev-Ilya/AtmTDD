import controller.Controller;
import model.Atm;
import model.Bank;
import model.DataBase;
import view.AtmMenu;

public class Main {

    public static void main(String[] args) {
        AtmMenu atmMenu = new AtmMenu();
        atmMenu.selectUserName();
    }
}
