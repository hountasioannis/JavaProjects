package gr.aueb.cf.schoolapp.controllerview;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.TeacherServiceImpl;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class InsertForm extends JFrame {
        private static final long serialVersionUID = 1L;

        private ITeacherDAO teacherDAO = new TeacherDAOImpl();
        private ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);
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
                String inputLastname = txtLastname.getText().trim();
                String inputFirstname = txtFirstname.getText().trim();

                if (inputLastname.equals("") || inputFirstname.equals("")) {
                    JOptionPane.showMessageDialog(null, "not valid input", "insert error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    TeacherDTO teacherDTO = new TeacherDTO();
                    teacherDTO.setFirstname(inputFirstname);
                    teacherDTO.setLastname(inputLastname);

                    Teacher teacher = teacherService.insertTeacher(teacherDTO);
                    JOptionPane.showMessageDialog(null, "record inserted", "insert", JOptionPane.PLAIN_MESSAGE);
                } catch (TeacherDAOException e1) {
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(null, message,"error", JOptionPane.ERROR_MESSAGE);
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