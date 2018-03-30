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
        Controller.getInstance().setBank(bank);
        Controller.getInstance().setAtm(atm);
        atmMenu.insertCardWindow();
    }
}
