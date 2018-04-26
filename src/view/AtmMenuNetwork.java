package view;

import controller.Controller;
import service.CardsDao;
import service.UsersDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AtmMenuNetwork extends JFrame{
    private JButton deposit = new JButton("Deposit money");
    private JButton withdraw = new JButton("Withdraw");
    private Controller controller=Controller.getInstance();
    private boolean flag;

    /**
     * служит для определения вызванного меню, для того, чтобы правильно релизовать методы снятия и внесения денег
     */
    public enum Menu {
        WITHDRAW, DEPOSIT, CANCEL, PASSWORD
    }

    /**меню выбора пользователя-начало работы программы*/
    public void selectUserName(){
        JFrame usersFrame = new JFrame();
        usersFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        usersFrame.setTitle("Select Name");
        usersFrame.setLocationRelativeTo(null);
        Font font = new Font("Arial", Font.PLAIN, 20);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        final JLabel label = new JLabel(" ");
        label.setAlignmentX(LEFT_ALIGNMENT);
        label.setFont(font);
        usersFrame.add(label);
        /**данные получаем из контроллера, который их передает через клиент от запроса на сервере*/
        JComboBox userNameBox = new JComboBox(controller.getUsersName());
        userNameBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        userNameBox.setFont(font);
        panel.add(userNameBox);
        usersFrame.add(panel);
        usersFrame.setPreferredSize(new Dimension(300, 150));
        usersFrame.pack();
        usersFrame.setVisible(true);
        userNameBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox box = (JComboBox) e.getSource();
                String name = (String) box.getSelectedItem();
                /**установить выбранное имя в контроллер*/
                label.setText(name);
                controller.setUserName(name);
                /**отобразить список карт для выбранного пользователя*/
                if (controller.getUserCardsList() != null) {
                    /**окно выбора карт*/
                    JOptionPane selCard = new JOptionPane();
                    selCard.setInitialSelectionValue(controller.getUserCardsList()[0]);
                    String selectedCardNumber = (String) JOptionPane.showInputDialog
                            (usersFrame, "Select card number", "Card number", JOptionPane.QUESTION_MESSAGE, null,
                                    controller.getUserCardsList(), controller.getUserCardsList()[0]);
                    controller.setCardNumber(selectedCardNumber);
                    usersFrame.setVisible(false);
                    insertCardWindow();
                }

            }
        });
    }

   /* public void showMenu() {
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
                *записываем сумму на карте в базу данных после всех операций
                CardsDao cardsDao = new CardsDao();
                *получаем остаток по карте и номер карты для записи данных
                cardsDao.updateCardAmount(controller.getAtm().getCreditCard().getAmount(), controller.getAtm().getCreditCard().getCardNumber());                ;
                controller.getAtm().removeCard();
                setVisible(false);
                System.exit(1);
            }
        });
        withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                windowEnterAmount("Withdraw");
                controller.setMenu(Menu.WITHDRAW);
            }
        });

        deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                windowEnterAmount("Deposit");
                controller.setMenu(Menu.DEPOSIT);
            }
        });
        atmMenu.add(checkBalance);
        atmMenu.add(deposit);
        atmMenu.add(withdraw);
        atmMenu.add(cancel);
        mainMenuFrame.pack();
        mainMenuFrame.setVisible(true);
    }

    private void windowEnterAmount(String menuName) {
        JFrame windowForEnter = new JFrame();
        windowForEnter.setFocusable(true);
        windowForEnter.setTitle(menuName);
        windowForEnter.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        BorderLayout borderLayout = new BorderLayout();
        panel.setLayout(borderLayout);
        Font font = new Font("Arial", Font.PLAIN, 20);
        *выравниваем поле ввода по правую сторону
        JFormattedTextField dispFormattedTextField = new JFormattedTextField();
        dispFormattedTextField.setFont(font);
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
                windowForEnter.setVisible(false);
            }
        });
        buttons.add(cancel);
        buttons.add(delete);

        windowForEnter.add("South", enter);
        *для меню ввода паролья нет нужды в кнопке enter
        if (controller.getMenu().equals(Menu.PASSWORD)) {
            windowForEnter.remove(enter);
        }
        for (int i = 0; i < buttons.size() - 2; i++) {
            int finalI = i;
            buttons.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cmd = e.getActionCommand();
                    if ('0' <= cmd.charAt(0) && cmd.charAt(0) <= '9') {
                        dispFormattedTextField.setText(dispFormattedTextField.getText() + cmd);
                    }

                    String dispVal = dispFormattedTextField.getText();

                    *если выбрано поле ввода пароля, необходимо дождаться ввода 4 цифр и передать на проверку в модель
                    if (controller.getMenu().equals(Menu.PASSWORD)) {
                        int passwordNumb = 0;
                        if (!"".equals(dispFormattedTextField)) {
                            passwordNumb = Integer.parseInt(dispVal);
                            if (dispVal.length() == 4) {
                                *если пароль введен верно передать управление основному меню
                                if (controller.checkPassword(passwordNumb)) {
                                    showMenu();
                                    windowForEnter.setVisible(false);
                                    controller.setMenu(Menu.CANCEL);
                                    return;
                                }
                                *если не верен пароль, вывести предупреждение, вернуть карту

                                JOptionPane incorrectPassword = new JOptionPane();
                                incorrectPassword.showConfirmDialog(windowForEnter, "Incorrect password!\n Take your card!", "Incorrect Password", incorrectPassword.PLAIN_MESSAGE);
                                windowForEnter.setVisible(false);
                                controller.getAtm().removeCard();
                                controller.setMenu(Menu.CANCEL);
                                insertCardWindow();

                            }
                        }
                    }

                    *ввести значение и передать его в модель
                    enter.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            *если выбрано меню внести наличные, то передать значение на счет
                            switch (controller.getMenu()) {
                                case DEPOSIT:
                                    controller.getAtm().depositMoney(Float.parseFloat(dispFormattedTextField.getText()));
                                    windowForEnter.setVisible(false);
                                    controller.setMenu(Menu.CANCEL);
                                    break;
                                case WITHDRAW:
                                    if (controller.getAtm().withdraw(Float.parseFloat(dispVal))) {
                                        windowForEnter.setVisible(false);
                                        dispFormattedTextField.setText("0");
                                        JOptionPane jOptionPane = new JOptionPane();
                                        jOptionPane.showConfirmDialog(windowForEnter, "Take money:\n " + dispVal, "Take money", jOptionPane.PLAIN_MESSAGE);
                                        *необходим для выхода из цикла проверок
                                        controller.setMenu(Menu.CANCEL);
                                        break;
                                    } else {
                                        JOptionPane jOptionPane = new JOptionPane();
                                        jOptionPane.showConfirmDialog(windowForEnter, "Reduce amount and try again\n Balance on card:\n " + Controller.getInstance().getBalance() + "", "Balance", jOptionPane.PLAIN_MESSAGE);
                                        dispFormattedTextField.setText("0");
                                        controller.setMenu(Menu.CANCEL);
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
        for (JButton button : buttons) {
            buttonsPanel.add(button);
        }
        panel.add("Center", buttonsPanel);
        windowForEnter.add(panel);
        windowForEnter.setSize(400, 400);
        pack();
        windowForEnter.setVisible(true);
    }*/

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
        insertCard.setPreferredSize(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight() + 40));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.insertCard();
               // passwordEnter();
                insertCard.setVisible(false);
            }
        });
        card.add("Center", button);
        insertCard.pack();
        insertCard.setVisible(true);
    }

   /* public void passwordEnter() {
        controller.setMenu(Menu.PASSWORD);
        windowEnterAmount("Password");
    }*/


}
