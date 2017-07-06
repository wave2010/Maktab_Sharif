import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField idtext;
	private JTextField nametext;
	private JTextField profidtext;
	private JButton add_button;
	private JButton delete_button;
	private JButton update_button;
	private JButton restore_button;
	Student student = new Student();
	private static Connection con = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	StudentEntityManager sem = new StudentEntityManager();

	public void keyType(KeyEvent evt) {
		char vchar = evt.getKeyChar();
		if (!(Character.isDigit(vchar)) || vchar == KeyEvent.VK_BACK_SPACE)
			evt.consume();
	}

	public void keyTypeString(KeyEvent evt) {
		char vchar = evt.getKeyChar();
		if ((Character.isDigit(vchar)) || vchar == KeyEvent.VK_BACK_SPACE)
			evt.consume();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setTitle("GUI CRUD");
		setAutoRequestFocus(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 226, 231);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		add_button = new JButton("ADD");
		add_button.setEnabled(false);
		add_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!idtext.getText().equals(""))
				{
					student.setId(Integer.valueOf(idtext.getText()));
				}
				student.setName(nametext.getText());
				if(profidtext.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Prof_id Must Was Not Empty");
				
				}
				else
				{
					student.setId_prof(Integer.valueOf(profidtext.getText()));
					try {
						sem.insertToTable(student.getId(), student.getName(), student.getId_prof());
						
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "This Prof_id Not Found !");
					}
				}
			}

		});

		add_button.setBounds(10, 139, 89, 23);
		contentPane.add(add_button);

		idtext = new JTextField();
		idtext.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				keyType(evt);
			}
		});

		idtext.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				changed();
			}

			public void removeUpdate(DocumentEvent e) {
				changed();
			}

			public void insertUpdate(DocumentEvent e) {
				changed();
			}

			public void changed() {
				if (idtext.getText().equals("")) {
					delete_button.setEnabled(false);
					restore_button.setEnabled(false);
					update_button.setEnabled(false);
					add_button.setEnabled(false);
				} else {
					delete_button.setEnabled(true);
					restore_button.setEnabled(true);
					update_button.setEnabled(true);
					add_button.setEnabled(true);
				}
			}
		});
		idtext.setForeground(Color.RED);
		idtext.setFont(new Font("Tahoma", Font.BOLD, 11));
		idtext.setBounds(112, 11, 99, 30);
		contentPane.add(idtext);
		idtext.setColumns(10);
		nametext = new JTextField();
		nametext.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				keyTypeString(evt);
			}
		});
		nametext.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				changed();
			}

			public void removeUpdate(DocumentEvent e) {
				changed();

			}

			public void insertUpdate(DocumentEvent e) {
				changed();
			}

			public void changed() {
				if (nametext.getText().isEmpty()) {
					delete_button.setEnabled(false);

				} else {
					delete_button.setEnabled(true);
					

				}
			}
		});
		nametext.setForeground(Color.BLUE);
		nametext.setFont(new Font("Tahoma", Font.BOLD, 11));
		nametext.setColumns(10);
		nametext.setBounds(112, 52, 99, 30);
		contentPane.add(nametext);

		profidtext = new JTextField();
		profidtext.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				keyType(evt);
			}
		});
		profidtext.setForeground(Color.BLUE);
		profidtext.setFont(new Font("Tahoma", Font.BOLD, 11));
		profidtext.setColumns(10);
		profidtext.setBounds(112, 91, 99, 30);
		profidtext.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				changed();
			}

			public void removeUpdate(DocumentEvent e) {
				changed();

			}

			public void insertUpdate(DocumentEvent e) {
				changed();
			}

			public void changed() {
				if (profidtext.getText().isEmpty()) {
					add_button.setEnabled(false);

				} else {

					add_button.setEnabled(true);

				}
			}
		});
		contentPane.add(profidtext);

		JLabel lblId = new JLabel("ID :");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblId.setBounds(10, 19, 46, 14);
		contentPane.add(lblId);

		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblName.setBounds(10, 60, 63, 14);
		contentPane.add(lblName);

		JLabel lblProfesoorId = new JLabel("Professor ID :");
		lblProfesoorId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProfesoorId.setBounds(10, 99, 79, 14);
		contentPane.add(lblProfesoorId);

		update_button = new JButton("UPDATE");
		update_button.setEnabled(false);
		update_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					int id = Integer.valueOf(idtext.getText());
					student.setId(Integer.valueOf(idtext.getText()));
					student.setName(nametext.getText());
					student.setId_prof(Integer.valueOf(profidtext.getText()));
					try {
						sem.updateStudent(student.getId(), student.getName(), student.getId_prof(), id);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "This Prof_id Not Found ! ");
					}
					
				
			}
		});
		update_button.setBounds(10, 173, 89, 23);
		contentPane.add(update_button);

		delete_button = new JButton("DELETE");
		delete_button.setEnabled(false);
		delete_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (idtext.getText().isEmpty()) {

					student.setName(nametext.getText());
					sem.deleteFromTable(0, student.getName());
				} else if (nametext.getText().equals("")) {
					student.setId(Integer.valueOf(idtext.getText()));
					sem.deleteFromTable(student.getId(), "");
				} else {
					JOptionPane.showMessageDialog(null, "Please Just Enter One Field ! ");
				}
			}
		});
		delete_button.setBounds(122, 139, 89, 23);
		contentPane.add(delete_button);

		restore_button = new JButton("RESTORE");
		restore_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				student.setId(Integer.valueOf(idtext.getText()));
				con = ConnectionManager.getConnection();
				try {
					stmt = con.createStatement();
					rs = stmt.executeQuery("Select * FROM student WHERE id ='" + student.getId() + "';");
					while (rs.next()) {
						idtext.setText(String.valueOf(rs.getInt(1)));
						nametext.setText(String.valueOf(rs.getString(2)));
						profidtext.setText(String.valueOf(rs.getInt(3)));

					}
					sem.informationStudent(student.getId());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		restore_button.setEnabled(false);
		restore_button.setBounds(122, 173, 89, 23);
		contentPane.add(restore_button);
	}
}
