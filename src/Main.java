public class Main {
    public static void main(String[] args) {
        Atm atm = new Atm(Bank.List.VTB);
        atm.init();
        atm.start();
    }
}
