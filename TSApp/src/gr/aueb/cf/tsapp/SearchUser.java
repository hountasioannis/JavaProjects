package gr.aueb.cf.tsapp;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.mindrot.jbcrypt.BCrypt;

import gr.aueb.cf.tsapp.util.DBUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

public class SearchUser extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private String inputUsername;
	private JPasswordField txtPassword;
	private String inputPassword;
	private String hashedPassword;
	
	public SearchUser() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				txtUsername.setText("");
				txtPassword.setText("");
			}
		});
		setTitle("Αναζήτηση Χρηστών");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SearchUser.class.getResource("/resources/eduv2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 428, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLastname = new JLabel("Username");
		lblLastname.setForeground(new Color(165, 42, 42));
		lblLastname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLastname.setBounds(154, 15, 80, 25);
		contentPane.add(lblLastname);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(87, 38, 214, 25);
		contentPane.add(txtUsername);
		txtUsername.setColumns(50);
		
		
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
		
		JPanel panel = new JPanel();
		panel.setBounds(44, 15, 300, 51);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(44, 200, 300, 74);
		contentPane.add(panel_1);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(204, 0, 51));
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(132, 73, 122, 33);
		contentPane.add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(87, 104, 214, 25);
		contentPane.add(txtPassword);
		
		JButton btnSearch2 = new JButton("Αναζήτηση");
		btnSearch2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputUsername = txtUsername.getText().trim();
				inputPassword = String.valueOf(txtPassword.getPassword()).trim();
				
				if ((inputUsername.equals("")) && (inputPassword.equals(""))) {
					Main.getSearchUser().setEnabled(false);
					Main.getUpdateDeleteUser().setVisible(true);
				} else {
				
				String sql = "SELECT PASSWORD FROM USERS WHERE USERNAME = ?";
				
				try (Connection conn = DBUtil.getConnection();
						PreparedStatement p = conn.prepareStatement(sql)) {
					
					p.setString(1, inputUsername);
					ResultSet rs = p.executeQuery();
					
					if (rs.next()) {
						hashedPassword = rs.getString("PASSWORD");
					} else {
						JOptionPane.showMessageDialog(null, "User not found", "Error", 
								JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					if (BCrypt.checkpw(inputPassword, hashedPassword)) {
						Main.getSearchUser().setEnabled(false);
						Main.getUpdateDeleteUser().setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Error in password", "Login Error", 
								JOptionPane.WARNING_MESSAGE);
					}
		
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
			}		
		});
		btnSearch2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSearch2.setForeground(new Color(0, 0, 255));
		btnSearch2.setBounds(122, 139, 144, 51);
		contentPane.add(btnSearch2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(44, 73, 300, 127);
		contentPane.add(panel_2);
	}

	public String getInputUsername() {
		return inputUsername;
	}
	
	public String getInputPassword() {
		return inputPassword;
	}
}
