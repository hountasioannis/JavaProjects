package gr.aueb.cf.tsapp;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.tsapp.util.DBUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InsertForm extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLastname;
	private JTextField txtFirstname;
	
	public InsertForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				txtLastname.setText("");
				txtFirstname.setText("");
			}
		});
		setTitle("Εισαγωγή Εκπαιδευτή");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLastname = new JLabel("Επώνυμο");
		lblLastname.setHorizontalAlignment(SwingConstants.TRAILING);
		lblLastname.setForeground(new Color(139, 0, 0));
		lblLastname.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLastname.setBounds(74, 68, 80, 26);
		contentPane.add(lblLastname);
		
		txtLastname = new JTextField();
		txtLastname.setBounds(162, 71, 150, 20);
		contentPane.add(txtLastname);
		txtLastname.setColumns(50);
		
		txtFirstname = new JTextField();
		txtFirstname.setBounds(162, 108, 150, 20);
		contentPane.add(txtFirstname);
		txtFirstname.setColumns(50);
		
		JLabel lblFirstname = new JLabel("Όνομα");
		lblFirstname.setHorizontalAlignment(SwingConstants.TRAILING);
		lblFirstname.setForeground(new Color(139, 0, 0));
		lblFirstname.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFirstname.setBounds(74, 105, 80, 26);
		contentPane.add(lblFirstname);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "INSERT INTO TEACHERS (FIRSTNAME, LASTNAME) VALUES (?, ?)";
				String inputLastname;
				String inputFirstname;
				int n;
				
				try (Connection conn = DBUtil.getConnection();
						PreparedStatement p = conn.prepareStatement(sql)) {
					
					
					inputLastname = txtLastname.getText().trim();
					inputFirstname = txtFirstname.getText().trim();
					
					if (inputLastname.equals("") || (inputFirstname.equals(""))) {
						return;
					}
					
					p.setString(1, inputFirstname);
					p.setString(2, inputLastname);
					
					n = p.executeUpdate();
					JOptionPane.showMessageDialog(null, n + " records inserted", 
							"INSERT", JOptionPane.PLAIN_MESSAGE);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnInsert.setForeground(new Color(0, 0, 255));
		btnInsert.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnInsert.setBounds(184, 188, 96, 43);
		contentPane.add(btnInsert);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getSearchForm().setEnabled(true);
				Main.getInsertForm().setVisible(false);
			}
		});
		btnClose.setForeground(new Color(0, 0, 255));
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClose.setBounds(290, 188, 96, 43);
		contentPane.add(btnClose);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 170, 390, 1);
		contentPane.add(separator);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(56, 41, 305, 108);
		contentPane.add(panel);
	}
}
