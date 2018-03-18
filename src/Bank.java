

public class Bank {
    private String name;
    private User user;

    public void setName(List name) {
        this.name = name.toString();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser(String name) {
        return user;
    }

    public CreditCard emittedCard(User user, long accountNumber, int password) {
        return new CreditCard(accountNumber, password);
    }

    public enum List {
        VTB, SBERBANK, MOSCOWCREDIT, ALPHABANK
    }
}
