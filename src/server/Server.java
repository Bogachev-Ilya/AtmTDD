package server;

import model.Atm;
import model.CreditCard;
import model.DataBase;
import model.User;
import service.CardsDao;
import service.UsersDao;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    public static final int PORT = 8844;

    public static void main(String[] args) throws InterruptedException, ClassNotFoundException {

        try (ServerSocket serverSocket = new ServerSocket(PORT, 10)) {
            /**инициализация базы данных на стороне сервера*/
            DataBase dataBase = new DataBase();
            dataBase.initDataBase();
            Socket client = serverSocket.accept();
            System.out.println("Connected...");
            //отправить данные пользователю
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            ObjectOutputStream obOut = new ObjectOutputStream(client.getOutputStream());
            System.out.println("OutputStream created");
            //получить данные от пользователя
            DataInputStream in = new DataInputStream(client.getInputStream());
            ObjectInputStream obIn = new ObjectInputStream(client.getInputStream());
            System.out.println("InputStream created");
            while (!client.isClosed()) {
                /**сначала считываем из базы данных имена пользователей и отправляем их клиенту*/
                UsersDao usersDao = new UsersDao();
                obOut.writeObject(usersDao.getAllUserNames());
                Thread.sleep(3000);
                /**получаем имя выбранного пользователя от клиента и создаем юзера, передаем его клиенту*/
                String userName = in.readUTF();
                System.out.println(userName);
                User user = usersDao.getUserByUserName(userName);
                obOut.writeObject(user);
                obOut.flush();
                CardsDao cardsDao = new CardsDao();
                /**создать массив карт для отображения в меню выбора*/
                Object[] cardsListForUser = new Object[cardsDao.getAllCardsForUser(user.getId()).size()];
                for (int i = 0; i < cardsListForUser.length; i++) {
                    /**записать в массив номера карт пользователя*/
                    cardsListForUser[i] = cardsDao.getAllCardsForUser(user.getId()).get(i).getCardNumber();
                }
                /**передать массив клиенту для отображения в меню выбора карт*/
                obOut.writeObject(cardsListForUser);
                Thread.sleep(3000);
                /**получить от клиента номер выбранной карты, создать объект и отправить его клиенту*/
                CreditCard creditCard= cardsDao.getSelectedCard((String) obIn.readObject());
                Thread.sleep(300);
                obOut.writeObject(creditCard);
                /**создать ATM и передать клиенту*/
                Atm atm = new Atm();
                Thread.sleep(200);
                obOut.writeObject(atm);


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}





