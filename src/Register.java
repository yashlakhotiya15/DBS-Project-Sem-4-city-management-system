import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.util.UUID;
public class Register extends JFrame {
	private String id = "";

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_5;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { Register frame = new Register();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } } });
	 * }
	 */

	/**
	 * Create the frame.
	 */
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, -26, 580, 530);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblRegister = new JLabel("REGISTER");
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblRegister.setBounds(147, 13, 238, 55);
		contentPane.add(lblRegister);

		textField = new JTextField();
		textField.setBounds(296, 89, 175, 22);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(296, 129, 175, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(298, 170, 175, 22);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Dialog", Font.PLAIN, 25));
		lblName.setBounds(71, 81, 116, 29);
		contentPane.add(lblName);

		JLabel lblIdProofNumber = new JLabel("IPN");
		lblIdProofNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdProofNumber.setToolTipText("ID Proof Number");
		lblIdProofNumber.setFont(new Font("Dialog", Font.PLAIN, 25));
		lblIdProofNumber.setBounds(66, 119, 107, 36);
		contentPane.add(lblIdProofNumber);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Dialog", Font.PLAIN, 25));
		lblPassword.setBounds(87, 247, 137, 42);
		contentPane.add(lblPassword);

		JLabel lblStreet = new JLabel("Street");
		lblStreet.setHorizontalAlignment(SwingConstants.CENTER);
		lblStreet.setFont(new Font("Dialog", Font.PLAIN, 25));
		lblStreet.setBounds(58, 162, 148, 29);
		contentPane.add(lblStreet);

		JLabel lblHouseNo = new JLabel("House No.");
		lblHouseNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblHouseNo.setFont(new Font("Dialog", Font.PLAIN, 25));
		lblHouseNo.setBounds(94, 203, 127, 38);
		contentPane.add(lblHouseNo);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(297, 216, 175, 22);
		contentPane.add(textField_4);

		JButton btnSubmit = new JButton("SUBMIT");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = textField.getText();
				String aid = textField_5.getText();
				String ipn = textField_1.getText();
				String street = textField_2.getText();
				String password = passwordField.getText();
				String house = textField_4.getText();
				pressSubmit(name,ipn,street,house,password,aid);
			}
		});
		btnSubmit.addActionListener(e -> this.dispose());
		btnSubmit.setFont(new Font("Dialog", Font.PLAIN, 25));
		btnSubmit.setBounds(204, 378, 150, 29);
		contentPane.add(btnSubmit);

		JLabel lblAreaId = new JLabel("Area ID");
		lblAreaId.setToolTipText("");
		lblAreaId.setHorizontalAlignment(SwingConstants.CENTER);
		lblAreaId.setFont(new Font("Dialog", Font.PLAIN, 25));
		lblAreaId.setBounds(91, 301, 107, 36);
		contentPane.add(lblAreaId);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(299, 309, 175, 22);
		contentPane.add(textField_5);

		JLabel label = new JLabel("Don't Know your Area Id, click here");
		label.setToolTipText("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.ITALIC, 15));
		label.setBounds(58, 431, 328, 25);
		contentPane.add(label);

		JButton button = new JButton("Area ID");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Area a = new Area();
				a.setVisible(true);
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 13));
		button.setBounds(372, 432, 97, 25);
		contentPane.add(button);

		passwordField = new JPasswordField();
		passwordField.setBounds(299, 261, 173, 22);
		contentPane.add(passwordField);

		JCheckBox chckbxShowPassword = new JCheckBox("Show");
		chckbxShowPassword.setBounds(480, 261, 61, 25);
		contentPane.add(chckbxShowPassword);
		chckbxShowPassword.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					passwordField.setEchoChar((char) 0);
				} else {
					passwordField.setEchoChar(('\u25CF'));
				}
			}
		});
	}
	public void pressSubmit(String name,String ipn,String street,String house,String password,String aid) {
		if(!(!name.equals("") && !ipn.equals("") && !street.equals("") && !house.equals("") && !password.equals("") && !aid.equals(""))) {
			JOptionPane.showMessageDialog(null,"Fill the form Completely","Some Fields are Empty", JOptionPane.WARNING_MESSAGE);
		}
		else {
			try {  
				Class.forName("oracle.jdbc.driver.OracleDriver");  
				Connection conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","B2170905466");
				Statement st = conn.createStatement();
				st.executeUpdate("insert into citizen values(seq.nextval" + ",'" + ipn + "','" + name + "','" + street + "','" + house + "','" + password + "','" + aid + "')");
				displayId(ipn);
				conn.close();
			} 
			catch (Exception e) {  
				if(((SQLException)e).getErrorCode() == 1)
				{
					JOptionPane.showMessageDialog(null,"User Already Registered","ERROR", JOptionPane.WARNING_MESSAGE);
				}
				if(((SQLException)e).getErrorCode() == 2291) {
					JOptionPane.showMessageDialog(null,"Enter Valid Area ID","ERROR", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}
	public void displayId(String ipn) {
		try {  
			Class.forName("oracle.jdbc.driver.OracleDriver");  

			Connection conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","B2170905466");  
			Statement st = conn.createStatement();
			ResultSet r=st.executeQuery("select citizen_id from citizen where id_proof_number = '" + ipn + "'");
			while (r.next()) {  
				id = r.getString(1);
			}
			RegistrationComplete reg = new RegistrationComplete(id);
			reg.setVisible(true);
			conn.close();
		} catch (Exception e) {  
			JOptionPane.showMessageDialog(null,"Failed to Connect to Database","Error Connection", JOptionPane.WARNING_MESSAGE);  
			System.exit(0);  
		}
	}
}
