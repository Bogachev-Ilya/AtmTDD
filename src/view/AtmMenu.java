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
        GridLayout gridLayout = new GridLayout(2,2);
        atmMenu.setLayout(gridLayout);
        JButton checkBalance = new JButton("Check balance");
        JButton deposit = new JButton("Deposit money");
        JButton withdraw = new JButton("Withdraw");
        JButton cancel = new JButton("Cancel");
        checkBalance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int balance =  Controller.getInstance().getBalance();
                JOptionPane jOptionPane = new JOptionPane();
                jOptionPane.showConfirmDialog(null, "Balance on card:\n "+balance, "Balance", jOptionPane.PLAIN_MESSAGE);
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().getAtm().removeCard();
                setVisible(false);
                insertCardWindow();
            }
        });
        withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                windowEnterAmount();

            }
        });

        deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                windowEnterAmount();
            }
        });
        atmMenu.add(checkBalance);
        atmMenu.add(deposit);
        atmMenu.add(withdraw);
        atmMenu.add(cancel);
        pack();
        setVisible(true);
    }

    private void windowEnterAmount() {
        JFrame windowForWithdraw = new JFrame();
        windowForWithdraw.setFocusable(true);
        windowForWithdraw.setTitle("Enter Amount");
        windowForWithdraw.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        JTextField dispFieldText = new JTextField("0");
        dispFieldText.setEnabled(false);
        BorderLayout borderLayout = new BorderLayout();
        panel.setLayout(borderLayout);
        panel.add("North", dispFieldText);
        JPanel buttonsPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(4, 3);
        buttonsPanel.setLayout(gridLayout);
        ArrayList <JButton> buttons = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            buttons.add(new JButton(String.valueOf(i)));
        }
        buttons.add(new JButton("0"));
        JButton cancel = new JButton("Cancel");
        JButton delete = new JButton("Delete");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                windowForWithdraw.setVisible(false);
            }
        });
        buttons.add(cancel);
        buttons.add(delete);

        for (int i = 0; i <buttons.size()-2 ; i++){
            int finalI = i;
            buttons.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cmd =e.getActionCommand();
                    if ('0'<=cmd.charAt(0)&&cmd.charAt(0)<='9'){
                        dispFieldText.setText(dispFieldText.getText()+cmd);
                    }
                    JButton clickedButton = (JButton) e.getSource();
                    double displayValue = 0;
                    String dispVal =  dispFieldText.getText();
                    if (!"".equals(dispFieldText)){
                        displayValue= Double.parseDouble(dispVal);
                }
                    /*String clickedButtonLabel = clickedButton.getText();
                    String line =e.getActionCommand();
                    dispFieldText.setText(dispVal +line);*/

            }
        });}

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!dispFieldText.getText().equals("")){
                String temp = dispFieldText.getText();
                dispFieldText.setText(temp.substring(0, temp.length()-1));

                }
            }
        });
        for (int i = 0; i < buttons.size(); i++) {
            buttonsPanel.add(buttons.get(i));
        }
        panel.add("Center", buttonsPanel);
        windowForWithdraw.add(panel);
        windowForWithdraw.setSize(400, 400);
        pack();
        windowForWithdraw.setVisible(true);
    }

    public void insertCardWindow(){
        JFrame insertCard = new JFrame();
        insertCard.setDefaultCloseOperation(EXIT_ON_CLOSE);
        insertCard.setTitle("Insert Card");
        insertCard.setFocusable(true);
        insertCard.setLocationRelativeTo(null);
        JPanel card = new JPanel();
        insertCard.add("Center",card);
        JButton button = new JButton("Insert Card");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().insertCard();
                showMenu();
                insertCard.setVisible(false);
            }
        });
        card.add("Center",button);
        insertCard.setVisible(true);
        insertCard.pack();
    }
}
