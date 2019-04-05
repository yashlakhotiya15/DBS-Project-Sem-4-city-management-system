import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class EmployeeLogin extends JFrame {
	private JLabel lblEnterEmpId;
	private JLabel label;
	
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeLogin frame = new EmployeeLogin();
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
	public EmployeeLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 530);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEmployeeLogin = new JLabel("EMPLOYEE LOGIN");
		lblEmployeeLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmployeeLogin.setFont(new Font("Verdana", Font.BOLD, 30));
		lblEmployeeLogin.setBounds(122, 38, 319, 69);
		contentPane.add(lblEmployeeLogin);
		
		JLabel lblEmpId = new JLabel("Emp ID");
		lblEmpId.setToolTipText("");
		lblEmpId.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpId.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblEmpId.setBounds(12, 143, 138, 31);
		contentPane.add(lblEmpId);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(158, 148, 226, 22);
		contentPane.add(textField);
		
		JButton button = new JButton("LOGIN");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String empId = textField.getText().toString();
				if(empId.equals("")){
					JOptionPane.showMessageDialog(null,"Enter Employee ID","Error Connection", JOptionPane.WARNING_MESSAGE);
				}
				else {
					pressSubmit(empId);
				}
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 25));
		button.setBounds(424, 146, 115, 29);
		contentPane.add(button);
		
		JLabel lblComplainId = new JLabel("Complain ID");
		lblComplainId.setToolTipText("");
		lblComplainId.setHorizontalAlignment(SwingConstants.CENTER);
		lblComplainId.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblComplainId.setBounds(34, 240, 138, 31);
		contentPane.add(lblComplainId);
		
		lblEnterEmpId = new JLabel("Enter Emp Id In the field above");
		lblEnterEmpId.setBounds(270, 240, 205, 31);
		contentPane.add(lblEnterEmpId);
		
		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setToolTipText("");
		lblCustomerName.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomerName.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblCustomerName.setBounds(34, 289, 177, 31);
		contentPane.add(lblCustomerName);
		
		label = new JLabel("Enter Emp Id In the field above");
		label.setBounds(270, 284, 205, 31);
		contentPane.add(label);
		
		JButton btnServiced = new JButton("SERVICED");
		btnServiced.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String empId = textField.getText().toString();
				pressServiced(empId);
			}
		});
		btnServiced.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnServiced.setBounds(334, 398, 115, 25);
		contentPane.add(btnServiced);
		
		JLabel lblIfYouHave = new JLabel("Serviced the complaint, press serviced");
		lblIfYouHave.setToolTipText("");
		lblIfYouHave.setHorizontalAlignment(SwingConstants.CENTER);
		lblIfYouHave.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lblIfYouHave.setBounds(24, 398, 265, 25);
		contentPane.add(lblIfYouHave);
	}
	public void pressSubmit(String empId) {
		String compId = "";
		String custName = "";
		
		try {  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","B2170905466");  
			Statement st = conn.createStatement();
			ResultSet r=st.executeQuery("select complaint_id,citizen_name from complaint,citizen where citizen.citizen_id = complaint.citizen_id and complaint.employee_id = " + "'" + empId + "'");
			while (r.next()) {  
				compId = r.getString(1);
				custName = r.getString(2);
			}
			lblEnterEmpId.setText(compId);
			label.setText(custName);
			conn.close();
		} catch (Exception e) {  
			JOptionPane.showMessageDialog(null,"You have no pending complaints","Voila!", JOptionPane.WARNING_MESSAGE);  
			System.exit(0);  
		}
	}
	
	public void pressServiced(String empId) {
		try {  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","B2170905466");  
			Statement st = conn.createStatement();
			ResultSet r = st.executeQuery("select complaint_id from complaint where employee_id = " + "'" + empId + "'");
			if(r.next() == false) {
				JOptionPane.showMessageDialog(null,"No complaints registered with this account","Voila!", JOptionPane.WARNING_MESSAGE);
			}
			else {
				st.executeUpdate("delete from complaint where employee_id = " + "'" + empId + "'");
				st.executeUpdate("commit");
				JOptionPane.showMessageDialog(null,"Complaint serviced","Voila!", JOptionPane.WARNING_MESSAGE);
				lblEnterEmpId.setText("");
				label.setText("");
				conn.close();
			}
		} catch (Exception e) {  
			JOptionPane.showMessageDialog(null,"Error Servicing Complaints","ERROR!", JOptionPane.WARNING_MESSAGE);  
			System.exit(0);  
		}
	}
}
