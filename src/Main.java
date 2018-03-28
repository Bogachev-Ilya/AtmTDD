import controller.Controller;
import model.Atm;
import model.Bank;
import view.AtmMenu;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank(Bank.List.VTB);
        Atm atm = new Atm(Bank.List.VTB);
        bank.init();
        //atm.start(bank.getUser());
        AtmMenu atmMenu = new AtmMenu();
        //времмено для теста меню
        atm.insertCard(bank.getCreditCard());
        Controller.getInstance().setBank(bank);
        Controller.getInstance().setAtm(atm);
        atmMenu.showMenu();
    }
}
