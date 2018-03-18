

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

    public CreditCard emittedCard(User user) {
        return null;
    }

    public enum List {
        VTB, SBERBANK, MOSCOWCREDIT, ALPHABANK
    }
}
