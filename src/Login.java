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
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 530);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCitizenLogin = new JLabel("CITIZEN LOGIN");
		lblCitizenLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblCitizenLogin.setFont(new Font("Verdana", Font.BOLD, 30));
		lblCitizenLogin.setBounds(112, 72, 319, 69);
		contentPane.add(lblCitizenLogin);
		
		JLabel lblUserId = new JLabel("User ID");
		lblUserId.setToolTipText("");
		lblUserId.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserId.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblUserId.setBounds(67, 183, 138, 25);
		contentPane.add(lblUserId);
		
		textField = new JTextField();
		textField.setBounds(242, 183, 226, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPassword.setBounds(67, 243, 138, 33);
		contentPane.add(lblPassword);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userId = textField.getText().toString();
				String password = passwordField.getText().toString();
				pressSubmit(userId,password);
			}
		});
		btnLogin.addActionListener(e -> this.dispose());
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnLogin.setBounds(98, 344, 115, 29);
		contentPane.add(btnLogin);
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.setToolTipText("If Not Registered");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Register register = new Register();
				register.setVisible(true);
			}
		});
		btnRegister.addActionListener(e -> this.dispose());
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnRegister.setBounds(282, 343, 172, 31);
		contentPane.add(btnRegister);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Show");
		chckbxNewCheckBox.setBounds(476, 246, 71, 25);
		contentPane.add(chckbxNewCheckBox);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(242, 246, 226, 25);
		contentPane.add(passwordField);
		chckbxNewCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					passwordField.setEchoChar((char) 0);
				} else {
					passwordField.setEchoChar(('\u25CF'));
				}
			}
		});
	}
	public void pressSubmit(String userId,String password) {
		String pswd = "";
		if(!(!userId.equals("") && !password.equals(""))) {
			JOptionPane.showMessageDialog(null,"Fill the form Completely","Some Fields are Empty", JOptionPane.WARNING_MESSAGE);
		}
		else {
			try {  
				Class.forName("oracle.jdbc.driver.OracleDriver");  
				Connection conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","B2170905466");
				Statement st = conn.createStatement();
				ResultSet r=st.executeQuery("select password from citizen where citizen_id = '" + userId + "'");
				if(r.next() == false) {
					JOptionPane.showMessageDialog(null,"User Not Registered","ERROR", JOptionPane.WARNING_MESSAGE);
				}
				else{
					r=st.executeQuery("select password from citizen where citizen_id = '" + userId + "'");
					while (r.next()) {  
					 pswd = r.getString(1);
				}
				if(pswd.equals(password)) {
					Complaint comp = new Complaint(userId);
					comp.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null,"Wrong Password","ERROR", JOptionPane.WARNING_MESSAGE);
				}
				conn.close();
			} }
			catch (Exception e) {  
				System.out.println(e);
				if(((SQLException)e).getErrorCode() == 1403)
				{
					JOptionPane.showMessageDialog(null,"User Not Registered","ERROR", JOptionPane.WARNING_MESSAGE);
				}
				if(((SQLException)e).getErrorCode() == 2291) {
					JOptionPane.showMessageDialog(null,"Enter Valid Area ID","ERROR", JOptionPane.WARNING_MESSAGE);
				}
				System.exit(0);  
			}
		}
	}
}
