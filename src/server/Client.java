package server;

import controller.Controller;
import model.Atm;
import model.CreditCard;
import model.User;
import view.AtmMenuNetwork;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;
import static server.Server.PORT;

public class Client {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Controller controller = Controller.getInstance();
        try (Socket socket = new Socket("localhost", PORT)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
            DataInputStream ois = new DataInputStream(socket.getInputStream());
            ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOut = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Client connected to socket.");
            AtmMenuNetwork atmMenuNetwork = new AtmMenuNetwork();
            while (!socket.isOutputShutdown()) {
                controller.setUsersName((Vector<String>) objectIn.readObject());
                atmMenuNetwork.selectUserName();
                //передаем имя на сервер
                Thread.sleep(5000);
                oos.writeUTF(controller.getUserName());
                //получаем юзера от сервера и передаем его в контроллер
                controller.setUser((User) objectIn.readObject());
                //тест
                System.out.println("User name is " + controller.getUser().getName());
                /**получить список карт от сервер передать их в контроллер для отображения*/
                controller.setUserCardsList((Object[]) objectIn.readObject());
                System.out.println(controller.getCardNumber());
                /**передать номер карты на сервер*/
                objectOut.writeObject(controller.getCardNumber());
                /**получить карту от сервера и устновить ее в контроллер*/
                Thread.sleep(400);
                CreditCard creditCard=(CreditCard) objectIn.readObject();
                controller.setCreditCard(creditCard);
               // System.out.println(controller.getCardNumber() +" "+controller.getCreditCard().getAmount()+ " "+controller.getCreditCard().getCardType());
                /**плучить ATM от серевера*/
                Thread.sleep(300);
                controller.setAtm((Atm) objectIn.readObject());
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
