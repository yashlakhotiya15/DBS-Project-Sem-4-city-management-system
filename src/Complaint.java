import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Color;

public class Complaint extends JFrame {
	private JLabel label_0;
	private JLabel label_1;
	private JLabel label_2;
	private JTextArea textArea;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_8;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Complaint frame = new Complaint();
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
	public Complaint(String userId) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 530);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRegisterComplaint = new JButton("Register");
		btnRegisterComplaint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ComplainRegister comp = new ComplainRegister(userId);
				comp.setVisible(true);
			}
		});
		btnRegisterComplaint.addActionListener(e -> this.dispose());
		btnRegisterComplaint.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRegisterComplaint.setBounds(382, 443, 123, 26);
		contentPane.add(btnRegisterComplaint);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(313, 86, 110, 22);
		contentPane.add(comboBox);
		
		JLabel label = new JLabel("COMPLAINT DETAILS");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 30));
		label.setBounds(58, 7, 436, 55);
		contentPane.add(label);
		
		JLabel label_3 = new JLabel("Department");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Dialog", Font.PLAIN, 25));
		label_3.setBounds(63, 124, 148, 29);
		contentPane.add(label_3);
		
		JLabel lblEmployeeNo = new JLabel("Date of complaint");
		lblEmployeeNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmployeeNo.setFont(new Font("Dialog", Font.PLAIN, 25));
		lblEmployeeNo.setBounds(69, 190, 201, 42);
		contentPane.add(lblEmployeeNo);
		
		JLabel label_5 = new JLabel("Employee Name");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Dialog", Font.PLAIN, 25));
		label_5.setBounds(63, 228, 201, 38);
		contentPane.add(label_5);
		
		JLabel lblComplaintId = new JLabel("Complaint ID");
		lblComplaintId.setHorizontalAlignment(SwingConstants.CENTER);
		lblComplaintId.setFont(new Font("Dialog", Font.PLAIN, 25));
		lblComplaintId.setBounds(69, 84, 152, 29);
		contentPane.add(lblComplaintId);
		
		JLabel lblWantToRegister = new JLabel("Want to register Complaint, click register");
		lblWantToRegister.setToolTipText("");
		lblWantToRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblWantToRegister.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lblWantToRegister.setBounds(31, 446, 328, 25);
		contentPane.add(lblWantToRegister);
		
		label_0 = new JLabel("Select Complaint ID");
		label_0.setFont(new Font("Tahoma", Font.ITALIC, 17));
		label_0.setBounds(312, 127, 161, 29);
		contentPane.add(label_0);
		
		label_1 = new JLabel("Select Complaint ID");
		label_1.setFont(new Font("Tahoma", Font.ITALIC, 17));
		label_1.setBounds(314, 197, 161, 29);
		contentPane.add(label_1);
		
		label_2 = new JLabel("Select Complaint ID");
		label_2.setFont(new Font("Tahoma", Font.ITALIC, 17));
		label_2.setBounds(312, 239, 162, 23);
		contentPane.add(label_2);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setFont(new Font("Dialog", Font.PLAIN, 25));
		lblDescription.setBounds(65, 347, 136, 38);
		contentPane.add(lblDescription);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		textArea.setBounds(318, 355, 201, 75);
		contentPane.add(textArea);
		
		JButton btnSubmit = new JButton("SUBMIT");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String comp_id = "";
				try{
					comp_id = comboBox.getSelectedItem().toString();
					displayDetails(comp_id,userId);
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(null,"No pending Complaints","Error Connection", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnSubmit.setBounds(445, 84, 77, 25);
		contentPane.add(btnSubmit);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setHorizontalAlignment(SwingConstants.CENTER);
		lblGender.setFont(new Font("Dialog", Font.PLAIN, 25));
		lblGender.setBounds(58, 306, 104, 38);
		contentPane.add(lblGender);
		
		label_6 = new JLabel("Select Complaint ID");
		label_6.setFont(new Font("Tahoma", Font.ITALIC, 17));
		label_6.setBounds(316, 314, 162, 23);
		contentPane.add(label_6);
		
		JLabel lblDepartmentNo = new JLabel("Dept Phone");
		lblDepartmentNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblDepartmentNo.setFont(new Font("Dialog", Font.PLAIN, 25));
		lblDepartmentNo.setBounds(71, 160, 133, 29);
		contentPane.add(lblDepartmentNo);
		
		label_7 = new JLabel("Select Complaint ID");
		label_7.setFont(new Font("Tahoma", Font.ITALIC, 17));
		label_7.setBounds(312, 159, 161, 29);
		contentPane.add(label_7);
		
		JLabel lblEmployeePhone = new JLabel("Employee Phone");
		lblEmployeePhone.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmployeePhone.setFont(new Font("Dialog", Font.PLAIN, 25));
		lblEmployeePhone.setBounds(66, 267, 201, 38);
		contentPane.add(lblEmployeePhone);
		
		label_8 = new JLabel("Select Complaint ID");
		label_8.setFont(new Font("Tahoma", Font.ITALIC, 17));
		label_8.setBounds(313, 278, 162, 23);
		contentPane.add(label_8);
		
		try {  
			Class.forName("oracle.jdbc.driver.OracleDriver");  

			Connection conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","B2170905466");  
			Statement st = conn.createStatement();

			 ResultSet r=st.executeQuery("select complaint_id from complaint where complaint.citizen_id = " + "'" + userId + "'");

			 while (r.next()) {  
			     comboBox.addItem(r.getString("complaint_id"));  
			 }


			    conn.close();
			    } catch (Exception e) {  
			JOptionPane.showMessageDialog(null,"Failed to Connect to Database","Error Connection", JOptionPane.WARNING_MESSAGE);  
			System.exit(0);  
			}
	}
	public void displayDetails(String comp_id,String userId) {
		String dept_name = "";
		String emp_name = "";
		String date = "";
		String description = "";
			try {
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","B2170905466");  
			Statement st = conn.createStatement();
			 /*ResultSet r=st.executeQuery("select department_name from department,complaint where department.department_id = complaint.department_id and complaint.complaint_id = '" + comp_id + "'");
			 while (r.next()) {  
				 dept_name = r.getString(1);
			 }
			 label_0.setText(dept_name);
			 r=st.executeQuery("select employee_name from employee,complaint where employee.employee_id = complaint.employee_id and complaint.complaint_id = '" + comp_id + "'");
			 while (r.next()) {  
				 emp_name = r.getString(1);
			 }
			 label_2.setText(emp_name);
			 r=st.executeQuery("select to_char(date_of_complaint,'MM-DD-YYYY') from complaint where complaint.complaint_id = '" + comp_id + "'");
			 while (r.next()) {  
				 date = r.getString(1);
			 }
			 label_1.setText(date);
			 r=st.executeQuery("select description from complaint where complaint.complaint_id = '" + comp_id + "'");
			 while (r.next()) {  
				 description = r.getString(1);
			 }
			 textArea.setText(description);*/
			String gender = "";
			ResultSet r = st.executeQuery("select department_name,landline,to_char(date_of_complaint,'DD-MM-YYYY'),employee_name,phone,gender,description from complaint,employee,department where employee.employee_id = complaint.employee_id and employee.department_id = complaint.department_id and employee.department_id = department.department_id and  department.department_id = complaint.department_id and complaint.complaint_id = '" + comp_id + "'");
			while(r.next()) {
				label_0.setText(r.getString(1));
				label_7.setText(r.getString(2));
				label_1.setText(r.getString(3));
				label_2.setText(r.getString(4));
				gender = r.getString(6);
				if(gender.equals('M')) gender = "Male";
				else gender = "Female";
				label_6.setText(gender);
				label_8.setText(r.getString(5));
				textArea.setText(r.getString(7));
			}
			conn.close();
			    } catch (Exception e) {  
			JOptionPane.showMessageDialog(null,"Failed to Connect to Database","Error Connection", JOptionPane.WARNING_MESSAGE);  
			System.exit(0);  
			}
	}
}
