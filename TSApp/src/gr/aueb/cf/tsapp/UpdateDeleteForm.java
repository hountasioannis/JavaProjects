package gr.aueb.cf.tsapp;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.tsapp.util.DBUtil;

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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateDeleteForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtLastname;
	private JTextField txtFirstname;
	private Connection conn;
	private PreparedStatement p;
	private ResultSet rs;

	public UpdateDeleteForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				String sql = "SELECT ID, FIRSTNAME, LASTNAME FROM TEACHERS WHERE LASTNAME LIKE ?";
				
				
				try {
					conn = DBUtil.getConnection();
					p = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, 
							ResultSet.CONCUR_UPDATABLE);
					
					p.setString(1, Main.getSearchForm().getInputLastname() + '%');
					rs = p.executeQuery();
					
					if (rs.next()) {
						txtId.setText(Integer.toString(rs.getInt("ID")));
						txtFirstname.setText(rs.getString("FIRSTNAME"));
						txtLastname.setText(rs.getString("LASTNAME"));
					} else {
						txtId.setText("");
						txtFirstname.setText("");
						txtLastname.setText("");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}
			
			
			@Override
			public void windowClosed(WindowEvent e) {
				try {
					if (rs != null) rs.close();
					if (p != null) p.close();
					if (conn != null) conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(UpdateDeleteForm.class.getResource("/resources/eduv2.png")));
		setTitle("Ενημέρωση / Διαγραφή Εκπαιδευτή");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 423, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLastname = new JLabel("Επώνυμο");
		lblLastname.setHorizontalAlignment(SwingConstants.TRAILING);
		lblLastname.setForeground(new Color(165, 42, 42));
		lblLastname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLastname.setBounds(22, 55, 68, 20);
		contentPane.add(lblLastname);
		
		txtId = new JTextField();
		txtId.setBackground(new Color(245, 245, 220));
		txtId.setEditable(false);
		txtId.setBounds(100, 21, 38, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		txtLastname = new JTextField();
		txtLastname.setBounds(100, 55, 170, 20);
		contentPane.add(txtLastname);
		txtLastname.setColumns(50);
		
		txtFirstname = new JTextField();
		txtFirstname.setBounds(100, 91, 170, 20);
		contentPane.add(txtFirstname);
		txtFirstname.setColumns(50);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(22, 146, 370, 1);
		contentPane.add(separator);
		
		JButton btnFirst = new JButton("");
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.first()) {
						txtId.setText(rs.getString("ID"));
						txtLastname.setText(rs.getString("LASTNAME"));
						txtFirstname.setText(rs.getString("FIRSTNAME"));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnFirst.setIcon(new ImageIcon(UpdateDeleteForm.class.getResource("/resources/First record.png")));
		btnFirst.setBounds(22, 158, 46, 23);
		contentPane.add(btnFirst);
		
		JButton btnPrevious = new JButton("");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.previous()) {
						txtId.setText(rs.getString("ID"));
						txtLastname.setText(rs.getString("LASTNAME"));
						txtFirstname.setText(rs.getString("FIRSTNAME"));
					} else {
						rs.first();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnPrevious.setIcon(new ImageIcon(UpdateDeleteForm.class.getResource("/resources/Previous_record.png")));
		btnPrevious.setBounds(69, 158, 46, 23);
		contentPane.add(btnPrevious);
		
		JButton btnNext = new JButton("");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.next()) {
						txtId.setText(rs.getString("ID"));
						txtLastname.setText(rs.getString("LASTNAME"));
						txtFirstname.setText(rs.getString("FIRSTNAME"));
					} else {
						rs.last();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNext.setIcon(new ImageIcon(UpdateDeleteForm.class.getResource("/resources/Next_track.png")));
		btnNext.setBounds(116, 158, 46, 23);
		contentPane.add(btnNext);
		
		JButton btnLast = new JButton("");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.last()) {
						txtId.setText(rs.getString("ID"));
						txtLastname.setText(rs.getString("LASTNAME"));
						txtFirstname.setText(rs.getString("FIRSTNAME"));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnLast.setIcon(new ImageIcon(UpdateDeleteForm.class.getResource("/resources/Last_Record.png")));
		btnLast.setBounds(163, 158, 46, 23);
		contentPane.add(btnLast);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "DELETE FROM TEACHERS WHERE ID = ?";
				int response;
				int numberOfRowsAffected;
				
				if (txtId.getText().equals("")) return;
				try {
					conn = DBUtil.getConnection();
					p = conn.prepareStatement(sql);
					p.setInt(1, Integer.parseInt(txtId.getText().trim()));
					
					response = JOptionPane.showConfirmDialog(null, "Είστε σίγουρος", "Delete", 
							JOptionPane.YES_NO_OPTION );
					
					if (response == JOptionPane.YES_OPTION) {
						numberOfRowsAffected = p.executeUpdate();
						JOptionPane.showMessageDialog(null, numberOfRowsAffected + " rows deleted", 
								"Delete", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
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
				String sql = "UPDATE TEACHERS SET FIRSTNAME = ?, LASTNAME = ? WHERE ID = ?";
				String inputLastname;
				String inputFirstname;
				String inputId;
				int n;
				
				try {
					Connection conn = DBUtil.getConnection();
					p = conn.prepareStatement(sql);
					
					inputLastname = txtLastname.getText().trim();
					inputFirstname = txtFirstname.getText().trim();
					inputId = txtId.getText();
					
					if (inputLastname.equals("") || (inputFirstname.equals(""))) {
						return;
					}
					
					p.setString(1, inputFirstname);
					p.setString(2, inputLastname);
					p.setInt(3, Integer.parseInt(inputId));
					
					n = p.executeUpdate();
					JOptionPane.showMessageDialog(null, n + " records updated", 
							"UPDATE", JOptionPane.PLAIN_MESSAGE);
				} catch (SQLException e1) {
					e1.printStackTrace();
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
				Main.getSearchForm().setEnabled(true);
				Main.getUpdateDeleteForm().setVisible(false);
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
		
		JLabel lblFirstname = new JLabel("Όνομα");
		lblFirstname.setHorizontalAlignment(SwingConstants.TRAILING);
		lblFirstname.setForeground(new Color(165, 42, 42));
		lblFirstname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFirstname.setBounds(22, 91, 68, 20);
		contentPane.add(lblFirstname);
	}

}
