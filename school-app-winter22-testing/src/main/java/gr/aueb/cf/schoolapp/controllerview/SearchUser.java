package gr.aueb.cf.schoolapp.controllerview;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolapp.Main;




import javax.swing.JLabel;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class SearchUser extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUsername;
    private String inputUsername;


    public SearchUser() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                txtUsername.setText("");

            }
        });
        setTitle("Αναζήτηση Χρηστών");
        setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 428, 354);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblLastname = new JLabel("Username");
        lblLastname.setForeground(new Color(165, 42, 42));
        lblLastname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblLastname.setBounds(154, 29, 80, 25);
        contentPane.add(lblLastname);


        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnClose.setForeground(new Color(0, 0, 255));
        btnClose.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnClose.setBounds(248, 284, 96, 33);
        contentPane.add(btnClose);

        JButton btnInsert = new JButton("Εισαγωγή");
        btnInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getSearchUser().setEnabled(false);
                Main.getInsertUser().setVisible(true);
            }
        });
        btnInsert.setForeground(Color.BLUE);
        btnInsert.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnInsert.setBounds(122, 210, 144, 53);
        contentPane.add(btnInsert);



        JButton btnSearch2 = new JButton("Αναζήτηση");
        btnSearch2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inputUsername = txtUsername.getText().trim();



                Main.getSearchUser().setEnabled(false);
                Main.getUpdateDeleteUser().setVisible(true);

            }
        });
        btnSearch2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnSearch2.setForeground(new Color(0, 0, 255));
        btnSearch2.setBounds(122, 121, 144, 51);
        contentPane.add(btnSearch2);

        txtUsername = new JTextField();
        txtUsername.setBounds(66, 78, 257, 33);
        contentPane.add(txtUsername);
        txtUsername.setColumns(50);

        JPanel panel = new JPanel();
        panel.setBounds(20, 10, 340, 190);
        contentPane.add(panel);
    }

    public String getInputUsername() {
        return inputUsername;
    }
}

