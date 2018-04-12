package server;

import controller.Controller;
import model.ConnectionFactory;
import model.DataBase;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;

public class Server {
    public static void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(8844)) {
            Socket client = serverSocket.accept();
            System.out.println("Connected...");
            DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());
            System.out.println("OutputStream created");
            DataInputStream inputStream = new DataInputStream(client.getInputStream());
            System.out.println("InputStream created");
            try (Connection connection = ConnectionFactory.getConnection()) {
                DataBase dataBase = new DataBase();
                dataBase.initDataBase();

                while (!client.isClosed()) {
                    /**Проверяем правильность ввода пароля*/
                    String name = inputStream.readUTF();
                }
            } catch (SQLException e) {
                e.printStackTrace();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


