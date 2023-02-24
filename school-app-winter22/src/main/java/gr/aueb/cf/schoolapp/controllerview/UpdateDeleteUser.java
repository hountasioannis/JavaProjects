package gr.aueb.cf.schoolapp.controllerview;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolapp.Main;

import gr.aueb.cf.schoolapp.dao.IUserDAO;

import gr.aueb.cf.schoolapp.dao.UserDAOImpl;

import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;

import gr.aueb.cf.schoolapp.dto.UserDTO;

import gr.aueb.cf.schoolapp.model.User;

import gr.aueb.cf.schoolapp.service.IUserService;

import gr.aueb.cf.schoolapp.service.UserServiceImpl;

import gr.aueb.cf.schoolapp.service.exceptions.UserNotFoundException;
import org.mindrot.jbcrypt.BCrypt;



import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class UpdateDeleteUser extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtId;
    private JTextField txtUsername;
    private JTextField txtPassword;

    private IUserDAO userDAO = new UserDAOImpl();
    private IUserService userService = new UserServiceImpl(userDAO);
    private int listPosition;
    private int listSize;
    List<User> users;

    public UpdateDeleteUser() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                try {
                    users = userService.getUsersByUsername(Main.getSearchUser().getInputUsername());
                    listPosition = 0;
                    listSize = users.size();

                    if (listSize == 0) {
                        txtId.setText("");
                        txtUsername.setText("");
                        txtPassword.setText("");
                        return;
                    }

                    txtId.setText(Integer.toString(users.get(listPosition).getId()));
                    txtUsername.setText(users.get(listPosition).getUsername());
                    txtPassword.setText(users.get(listPosition).getPassword());


                } catch (UserDAOException e1) {
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(null, message, "error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
        setTitle("Ενημέρωση / Διαγραφή Χρήστη");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 423, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 255, 250));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setHorizontalAlignment(SwingConstants.TRAILING);
        lblUsername.setForeground(new Color(165, 42, 42));
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblUsername.setBounds(22, 55, 68, 20);
        contentPane.add(lblUsername);

        txtId = new JTextField();
        txtId.setBackground(new Color(245, 245, 220));
        txtId.setEditable(false);
        txtId.setBounds(100, 21, 38, 20);
        contentPane.add(txtId);
        txtId.setColumns(10);

        txtUsername = new JTextField();
        txtUsername.setBounds(100, 55, 170, 20);
        contentPane.add(txtUsername);
        txtUsername.setColumns(50);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(100, 91, 170, 20);
        contentPane.add(txtPassword);
        txtPassword.setColumns(50);

        JSeparator separator = new JSeparator();
        separator.setBounds(22, 146, 370, 1);
        contentPane.add(separator);

        JButton btnFirst = new JButton("");
        btnFirst.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listSize > 0) {
                    listPosition = 0;
                    txtId.setText(String.format("%s", users.get(listPosition).getId()));
                    txtUsername.setText(users.get(listPosition).getUsername());
                    txtPassword.setText(users.get(listPosition).getPassword());
                }
            }
        });
        btnFirst.setIcon(new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("First record.png")));
        btnFirst.setBounds(22, 158, 46, 23);
        contentPane.add(btnFirst);

        JButton btnPrevious = new JButton("");
        btnPrevious.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listPosition > 0) {
                    listPosition--;
                    txtId.setText(String.format("%s", users.get(listPosition).getId()));
                    txtUsername.setText(users.get(listPosition).getUsername());
                    txtPassword.setText(users.get(listPosition).getPassword());
                }
            }
        });
        btnPrevious.setIcon(new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("Previous_record.png")));
        btnPrevious.setBounds(69, 158, 46, 23);
        contentPane.add(btnPrevious);

        JButton btnNext = new JButton("");
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listPosition <= listSize - 2) {
                    listPosition++;
                    txtId.setText(String.format("%s", users.get(listPosition).getId()));
                    txtUsername.setText(users.get(listPosition).getUsername());
                    txtPassword.setText(users.get(listPosition).getPassword());
                }
            }
        });
        btnNext.setIcon(new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("Next_track.png")));
        btnNext.setBounds(116, 158, 46, 23);
        contentPane.add(btnNext);

        JButton btnLast = new JButton("");
        btnLast.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listSize > 0) {
                    listPosition = listSize - 1;
                    txtId.setText(String.format("%s", users.get(listPosition).getId()));
                    txtUsername.setText(users.get(listPosition).getUsername());
                    txtPassword.setText(users.get(listPosition).getPassword());
                }
            }
        });
        btnLast.setIcon(new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("Last_Record.png")));
        btnLast.setBounds(163, 158, 46, 23);
        contentPane.add(btnLast);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int response;
                if (txtId.getText().equals("")) return;
                try {
                    int id =  Integer.parseInt(txtId.getText().trim());

                    response = JOptionPane.showConfirmDialog(null, "Είστε σίγουρος", "Delete",
                            JOptionPane.YES_NO_OPTION );

                    if (response == JOptionPane.YES_OPTION) {
                        userService.deleteUser(id);
                        JOptionPane.showMessageDialog(null,  " record deleted",
                                "Delete", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (UserDAOException | UserNotFoundException e1) {
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(null, message,"error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnDelete.setForeground(new Color(0, 0, 255));
        btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnDelete.setBounds(22, 202, 98, 36);
        contentPane.add(btnDelete);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtId.getText().equals("")) return;
                String inputUsername = txtUsername.getText().trim();
                String inputPassword = String.valueOf(((JPasswordField) txtPassword).getPassword()).trim();
                String inputId = txtId.getText();

                if (inputUsername.equals("") || (inputPassword.equals(""))) {
                    JOptionPane.showMessageDialog(null, "not valid input", "update error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    int workload = 12;
                    String salt = BCrypt.gensalt(workload);
                    String hashedPassword = BCrypt.hashpw(inputPassword, salt);

                    UserDTO userDTO = new UserDTO();
                    userDTO.setId(Integer.parseInt(inputId));
                    userDTO.setUsername(inputUsername);
                    userDTO.setPassword(hashedPassword);

                    User user = userService.updateUser(userDTO);
                    JOptionPane.showMessageDialog(null,  " record updated",
                            "UPDATE", JOptionPane.PLAIN_MESSAGE);
                } catch (UserDAOException | UserNotFoundException e1) {
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(null, message,"error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        btnUpdate.setForeground(new Color(0, 0, 255));
        btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnUpdate.setBounds(121, 202, 98, 36);
        contentPane.add(btnUpdate);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getSearchUser().setEnabled(true);
                Main.getUpdateDeleteUser().setVisible(false);
            }

        });
        btnClose.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnClose.setForeground(new Color(0, 0, 255));
        btnClose.setBounds(220, 202, 98, 36);
        contentPane.add(btnClose);

        JLabel lblId = new JLabel("Κωδικός");
        lblId.setHorizontalAlignment(SwingConstants.TRAILING);
        lblId.setForeground(new Color(165, 42, 42));
        lblId.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblId.setBounds(22, 19, 68, 20);
        contentPane.add(lblId);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setHorizontalAlignment(SwingConstants.TRAILING);
        lblPassword.setForeground(new Color(165, 42, 42));
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPassword.setBounds(22, 91, 68, 20);
        contentPane.add(lblPassword);
    }

}
