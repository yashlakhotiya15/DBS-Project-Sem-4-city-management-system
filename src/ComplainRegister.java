import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ComplainRegister extends JFrame {
	private JComboBox comboBox;
	private JTextArea textArea;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComplainRegister frame = new ComplainRegister();
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
	public ComplainRegister(String userId) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 530);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegisterComplaint = new JLabel("REGISTER COMPLAINT");
		lblRegisterComplaint.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisterComplaint.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblRegisterComplaint.setBounds(64, 49, 436, 55);
		contentPane.add(lblRegisterComplaint);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setHorizontalAlignment(SwingConstants.CENTER);
		lblDepartment.setFont(new Font("Dialog", Font.PLAIN, 25));
		lblDepartment.setBounds(83, 145, 156, 29);
		contentPane.add(lblDepartment);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setToolTipText("ID Proof Number");
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setFont(new Font("Dialog", Font.PLAIN, 25));
		lblDescription.setBounds(90, 199, 138, 36);
		contentPane.add(lblDescription);
		
		JButton button = new JButton("SUBMIT");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String dept_name = comboBox.getSelectedItem().toString();
				String description = textArea.getText();
				pressSubmit(dept_name,description,userId);
			}
		});
		button.addActionListener(e -> this.dispose());
		button.setFont(new Font("Dialog", Font.PLAIN, 25));
		button.setBounds(202, 409, 150, 29);
		contentPane.add(button);
		
		comboBox = new JComboBox();
		comboBox.setBounds(294, 153, 175, 22);
		contentPane.add(comboBox);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
		textArea.setBounds(100, 248, 369, 130);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		contentPane.add(textArea);
		
		try {  
			Class.forName("oracle.jdbc.driver.OracleDriver");  

			Connection conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","B2170905466");  
			Statement st = conn.createStatement();

			 ResultSet r=st.executeQuery("select department_name from department");

			 while (r.next()) {  

			     comboBox.addItem(r.getString("department_name"));  
			 }


			    conn.close();
			    } catch (Exception e) {  
			JOptionPane.showMessageDialog(null,"Failed to Connect to Database","Error Connection", JOptionPane.WARNING_MESSAGE);  
			System.exit(0);  
			}
	}
	public void pressSubmit(String dept_name,String description,String userId) {
		
		String department_id = "";
		String employee_id = "";
		if(dept_name.equals("")) {
			JOptionPane.showMessageDialog(null,"Fill the department name","Some Fields are Empty", JOptionPane.WARNING_MESSAGE);
		}
		else {
			try {
				
				Class.forName("oracle.jdbc.driver.OracleDriver");  
				Connection conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","B2170905466");
				Statement st = conn.createStatement();
				ResultSet r = st.executeQuery("select department_id from department where department_name = '" + dept_name +"'");
				while (r.next()) {  
					 department_id = r.getString(1);
				}
				r = st.executeQuery("(select employee_id from employee where department_id = '" + department_id + "') minus (select employee_id from complaint where department_id = '" + department_id + "')");
				if(r.next() == false) {
					JOptionPane.showMessageDialog(null,"Employee Not Available Right Now","Come after some time", JOptionPane.WARNING_MESSAGE);
				}
				else {
					r = st.executeQuery("(select employee_id from employee where department_id = '" + department_id + "') minus (select employee_id from complaint where department_id = '" + department_id + "')");
					
					while (r.next()) {  
						 employee_id = r.getString(1);
					}
					st.executeUpdate("insert into complaint values(seq_comp.nextval,SYSDATE," + "'" + description + "','" + userId + "','" + department_id + "','" + employee_id + "')");
					JOptionPane.showMessageDialog(null,"Complaint registered successfully","SUCCESS", JOptionPane.WARNING_MESSAGE);
					Complaint comp = new Complaint(userId);
					comp.setVisible(true);
					conn.close();
				}
			} 
			catch (Exception e) {  
				if(((SQLException)e).getErrorCode() == 1)
				{
					JOptionPane.showMessageDialog(null,"User Already Registered","ERROR", JOptionPane.WARNING_MESSAGE);
				}
				if(((SQLException)e).getErrorCode() == 2291) {
					JOptionPane.showMessageDialog(null,"Enter Valid Area ID","ERROR", JOptionPane.WARNING_MESSAGE);
				}
				//System.out.println(((SQLException)e).getErrorCode());
				//JOptionPane.showMessageDialog(null,e,"ERROR", JOptionPane.WARNING_MESSAGE);
				//JOptionPane.showMessageDialog(null,"Enter Correct ID proof no. or Correct Area ID","ERROR", JOptionPane.WARNING_MESSAGE);  
				System.exit(0);  
			}
		}
	}
}
