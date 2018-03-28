public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank(Bank.List.VTB);
        Atm atm = new Atm(Bank.List.VTB);
        bank.init();
        atm.start(bank.getUser());
    }
}
