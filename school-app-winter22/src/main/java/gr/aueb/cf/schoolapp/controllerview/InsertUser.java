package gr.aueb.cf.schoolapp.controllerview;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolapp.Main;

import gr.aueb.cf.schoolapp.dao.IUserDAO;

import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;

import gr.aueb.cf.schoolapp.dto.UserDTO;

import gr.aueb.cf.schoolapp.model.User;

import gr.aueb.cf.schoolapp.service.IUserService;

import gr.aueb.cf.schoolapp.service.UserServiceImpl;
import org.mindrot.jbcrypt.BCrypt;



import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;


import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JPasswordField;

public class InsertUser extends JFrame {
    private static final long serialVersionUID = 1L;

    private IUserDAO userDAO = new UserDAOImpl();
    private IUserService userService = new UserServiceImpl(userDAO);
    private JPanel contentPane;
    private JTextField txtUsername;
    private JPasswordField txtPassword;


    public InsertUser() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                txtUsername.setText("");
                txtPassword.setText("");
            }
        });
        setTitle("Εισαγωγή Χρήστη");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(248, 248, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setHorizontalAlignment(SwingConstants.TRAILING);
        lblUsername.setForeground(new Color(139, 0, 0));
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblUsername.setBounds(74, 68, 80, 26);
        contentPane.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(162, 71, 150, 20);
        contentPane.add(txtUsername);
        txtUsername.setColumns(50);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setHorizontalAlignment(SwingConstants.TRAILING);
        lblPassword.setForeground(new Color(139, 0, 0));
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblPassword.setBounds(74, 105, 80, 26);
        contentPane.add(lblPassword);

        JButton btnInsert = new JButton("Insert");
        btnInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputPassword = String.valueOf(txtPassword.getPassword()).trim();
                String inputUsername = txtUsername.getText().trim();

                    if (inputPassword.equals("") || (inputUsername.equals(""))) {
                        JOptionPane.showMessageDialog(null, "not valid input", "insert error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {
                    int workload = 12;
                    String salt = BCrypt.gensalt(workload);
                    String hashedPassword = BCrypt.hashpw(inputPassword, salt);

                        UserDTO userDTO = new UserDTO();
                        userDTO.setUsername(inputUsername);
                        userDTO.setPassword(hashedPassword);

                        User user = userService.insertUser(userDTO);
                        JOptionPane.showMessageDialog(null, "record inserted", "insert", JOptionPane.PLAIN_MESSAGE);

                } catch (UserDAOException e1) {
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(null, message,"error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnInsert.setForeground(new Color(0, 0, 255));
        btnInsert.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnInsert.setBounds(175, 188, 96, 43);
        contentPane.add(btnInsert);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getSearchUser().setEnabled(true);
                Main.getInsertUser().setVisible(false);
            }
        });
        btnClose.setForeground(new Color(0, 0, 255));
        btnClose.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnClose.setBounds(263, 188, 96, 43);
        contentPane.add(btnClose);

        JSeparator separator = new JSeparator();
        separator.setBounds(10, 170, 390, 1);
        contentPane.add(separator);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(162, 108, 150, 20);
        contentPane.add(txtPassword);

        JPanel panel = new JPanel();
        panel.setBounds(37, 36, 322, 123);
        contentPane.add(panel);
    }
}

