package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
                JOptionPane jOptionPane = new JOptionPane();
                jOptionPane.showConfirmDialog(null, "Your balance on card:\n "+balance, "Balance", jOptionPane.PLAIN_MESSAGE);
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().getAtm().removeCard();
                System.out.println(Controller.getInstance().getAtm().getCreditCard());
            }
        });
        withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openWindowForWithdraw();

            }
        });
        atmMenu.add(checkBalance);
        atmMenu.add(deposit);
        atmMenu.add(withdraw);
        atmMenu.add(cancel);
        pack();
        setVisible(true);
    }

    private void openWindowForWithdraw() {
        JFrame windowForWithdraw = new JFrame();
        windowForWithdraw.setTitle("Enter Amount");
        windowForWithdraw.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        JTextArea jTextArea = new JTextArea("Text");
        BorderLayout borderLayout = new BorderLayout();
        panel.setLayout(borderLayout);
        panel.add("North", jTextArea);
        JPanel buttonsPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(4, 3);
        buttonsPanel.setLayout(gridLayout);
        ArrayList <JButton> buttons = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            buttons.add(new JButton(String.valueOf(i)));
        }
        buttons.add(new JButton("0"));
        buttons.add(new JButton("Delete"));
        buttons.add(new JButton("Cancel"));
        for (int i = 0; i < buttons.size(); i++) {
            buttonsPanel.add(buttons.get(i));
        }
        panel.add("Center", buttonsPanel);
        windowForWithdraw.add(panel);
        windowForWithdraw.setSize(400, 400);
        pack();
        windowForWithdraw.setVisible(true);
    }
}
