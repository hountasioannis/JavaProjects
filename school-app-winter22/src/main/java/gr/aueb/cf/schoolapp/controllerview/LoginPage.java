package gr.aueb.cf.schoolapp.controllerview;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.IUserService;
import gr.aueb.cf.schoolapp.service.UserServiceImpl;
import org.mindrot.jbcrypt.BCrypt;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.util.List;

public class LoginPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUsername;
    private JPasswordField txtPassword;

    private IUserDAO userDAO = new UserDAOImpl();
    private IUserService userService = new UserServiceImpl(userDAO);

    public LoginPage() {
        setTitle("Είσοδος Χρήστη");
        setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblUsername.setForeground(new Color(165, 42, 42));
        lblUsername.setHorizontalAlignment(SwingConstants.TRAILING);
        lblUsername.setBounds(64, 32, 110, 36);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblPassword.setForeground(new Color(165, 42, 42));
        lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPassword.setBounds(45, 77, 130, 36);
        contentPane.add(lblPassword);

        txtUsername = new JTextField();
        txtUsername.setBounds(184, 37, 162, 27);
        contentPane.add(txtUsername);
        txtUsername.setColumns(10);

        JSeparator separator = new JSeparator();
        separator.setBounds(45, 159, 330, 1);
        contentPane.add(separator);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputUsername = txtUsername.getText().trim();
                String inputPassword = String.valueOf(txtPassword.getPassword()).trim();
                String password = System.getenv("TS_ADMIN_PASSWORD");
                String hashedPassword;

                try {
                if (inputUsername.equals("") || inputPassword.equals("")) {
                    JOptionPane.showMessageDialog(null, "you must set username and password", "Login Error",
                            JOptionPane.WARNING_MESSAGE);
                    txtUsername.setText("");
                    txtPassword.setText("");
                    return;
                }

                if (inputUsername.equals("admin") && (inputPassword.equals(password))) {
                    Main.getLoginPage().setVisible(false);
                    Main.getSearchUser().setVisible(true);
                } else {

                    List<User> users = userService.getUsersByUsername(inputUsername);

                    if (users.size() == 0) {
                        JOptionPane.showMessageDialog(null, "User not found", "Error",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    for (int i = 0; i < users.size(); i++) {
                        hashedPassword = users.get(i).getPassword();

                        if (BCrypt.checkpw(inputPassword, hashedPassword)) {
                            Main.getMenu().setVisible(true);
                            Main.getLoginPage().setVisible(false);
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null, "Error in password", "Login Error",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
                    } catch (UserDAOException e1) {
                        String message = e1.getMessage();
                        JOptionPane.showMessageDialog(null, message, "error", JOptionPane.ERROR_MESSAGE);
                    }
                }

        });
        btnLogin.setForeground(new Color(0, 0, 255));
        btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnLogin.setBounds(211, 188, 135, 54);
        contentPane.add(btnLogin);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(185, 82, 162, 27);
        contentPane.add(txtPassword);
    }
}

