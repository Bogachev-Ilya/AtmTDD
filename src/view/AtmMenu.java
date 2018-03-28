package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AtmMenu extends JFrame {


    public void showMenu (){
        setTitle("Main menu");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel atmMenu = new JPanel();
        add(atmMenu);
        GridLayout gridLayout = new GridLayout();
        JButton checkBalance = new JButton("Check balance");
        JButton deposit = new JButton("Deposit money");
        JButton withdraw = new JButton("Withdraw");
        JButton cancel = new JButton("Cancel");
        checkBalance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int balance =  Controller.getInstance().getBalance();
                System.out.println("Balance is: " + balance);
            }
        });
        atmMenu.add(checkBalance);
        atmMenu.add(deposit);
        atmMenu.add(withdraw);
        atmMenu.add(cancel);
        pack();
        setVisible(true);
    }
}
