package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AtmMenu extends JFrame {
    private JButton deposit = new JButton("Deposit money");
    private JButton withdraw = new JButton("Withdraw");

    /**
     * служит для определения вызванного меню, для того, чтобы правильно релизовать методы снятия и внесения денег
     */
    public enum Menu {
        WITHDRAW, DEPOSIT, CANCEL
    }

    public void showMenu() {
        JFrame mainMenuFrame = new JFrame();
        mainMenuFrame.setTitle("Main menu");
        mainMenuFrame.setLocationRelativeTo(null);
        mainMenuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel atmMenu = new JPanel();
        mainMenuFrame.add(atmMenu);
        GridLayout gridLayout = new GridLayout(2, 2);
        atmMenu.setLayout(gridLayout);
        JButton checkBalance = new JButton("Check balance");
        JButton cancel = new JButton("Cancel");
        checkBalance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double balance = Controller.getInstance().getBalance();
                JOptionPane jOptionPane = new JOptionPane();
                jOptionPane.showConfirmDialog(null, "Balance on card:\n " + balance, "Balance", jOptionPane.PLAIN_MESSAGE);
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
                Controller.getInstance().setMenu(Menu.WITHDRAW);
            }
        });

        deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                windowEnterAmount();
                Controller.getInstance().setMenu(Menu.DEPOSIT);
            }
        });
        atmMenu.add(checkBalance);
        atmMenu.add(deposit);
        atmMenu.add(withdraw);
        atmMenu.add(cancel);
        mainMenuFrame.pack();
        mainMenuFrame.setVisible(true);
    }

    private void windowEnterAmount() {
        JFrame windowForWithdraw = new JFrame();
        windowForWithdraw.setFocusable(true);
        windowForWithdraw.setTitle("Enter Amount");
        windowForWithdraw.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        BorderLayout borderLayout = new BorderLayout();
        panel.setLayout(borderLayout);
        /**выравниваем поле ввода по провую сторону*/
        JFormattedTextField dispFormattedTextField = new JFormattedTextField();
        dispFormattedTextField.setHorizontalAlignment(SwingConstants.RIGHT);
        dispFormattedTextField.setEnabled(false);
        panel.add("North", dispFormattedTextField);
        JPanel buttonsPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(4, 3);
        buttonsPanel.setLayout(gridLayout);
        ArrayList<JButton> buttons = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            buttons.add(new JButton(String.valueOf(i)));
        }
        buttons.add(new JButton("0"));
        JButton cancel = new JButton("Cancel");
        JButton delete = new JButton("Delete");
        JButton enter = new JButton("Enter");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                windowForWithdraw.setVisible(false);
            }
        });
        buttons.add(cancel);
        buttons.add(delete);

        windowForWithdraw.add("South", enter);
        for (int i = 0; i < buttons.size() - 2; i++) {
            int finalI = i;
            buttons.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cmd = e.getActionCommand();
                    if ('0' <= cmd.charAt(0) && cmd.charAt(0) <= '9') {
                        dispFormattedTextField.setText(dispFormattedTextField.getText() + cmd);
                    }
                    double displayValue = 0;
                    String dispVal = dispFormattedTextField.getText();
                    if (!"".equals(dispFormattedTextField)) {
                        displayValue = Double.parseDouble(dispVal);
                    }
                    /**ввести значение и передать его в модель*/
                    enter.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            /**если выбрано меню внести наличные, то передать значение на счет*/
                            switch (Controller.getInstance().getMenu()) {
                                case DEPOSIT:
                                    Controller.getInstance().setAmount(Double.parseDouble(dispFormattedTextField.getText()));
                                    windowForWithdraw.setVisible(false);
                                    Controller.getInstance().setMenu(Menu.CANCEL);
                                    break;
                                case WITHDRAW:
                                    if (Controller.getInstance().getAtm().withdraw(Double.parseDouble(dispVal))) {
                                        windowForWithdraw.setVisible(false);
                                        dispFormattedTextField.setText("0");
                                        JOptionPane jOptionPane = new JOptionPane();
                                        jOptionPane.showConfirmDialog(windowForWithdraw, "Take your money:\n " + dispVal, "Take your money", jOptionPane.PLAIN_MESSAGE);

                                        Controller.getInstance().setMenu(Menu.CANCEL);
                                        break;
                                    } else {
                                        JOptionPane jOptionPane = new JOptionPane();
                                        jOptionPane.showConfirmDialog(windowForWithdraw, "Reduce amount and try again\n Balance on card:\n " + Controller.getInstance().getBalance() + "", "Balance", jOptionPane.PLAIN_MESSAGE);
                                        dispFormattedTextField.setText("0");
                                        Controller.getInstance().setMenu(Menu.CANCEL);
                                        break;
                                    }
                                case CANCEL:
                                    return;

                            }
                        }
                    });
                }
            });
        }

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!dispFormattedTextField.getText().equals("")) {
                    String temp = dispFormattedTextField.getText();
                    dispFormattedTextField.setText(temp.substring(0, temp.length() - 1));
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

    public void insertCardWindow() {
        JFrame insertCard = new JFrame();
        insertCard.setDefaultCloseOperation(EXIT_ON_CLOSE);
        insertCard.setTitle("Insert Card");
        insertCard.setFocusable(true);
        insertCard.setLocationRelativeTo(null);
        JPanel card = new JPanel();
        insertCard.add("Center", card);
        JButton button = new JButton();
        ImageIcon imageIcon = new ImageIcon("res/creditCard.png");
        button.setIcon(imageIcon);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        insertCard.setPreferredSize(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight()+40));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().insertCard();
                showMenu();
                insertCard.setVisible(false);
            }
        });
        card.add("Center", button);
        insertCard.pack();
        insertCard.setVisible(true);
    }
}
