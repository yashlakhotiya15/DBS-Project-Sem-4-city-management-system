import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.SwingConstants;

import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Area extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Area frame = new Area();
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
	public Area() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 530);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 196, 222));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblArea = new JLabel("AREA");
		lblArea.setHorizontalAlignment(SwingConstants.CENTER);
		lblArea.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblArea.setBounds(211, 58, 107, 41);
		contentPane.add(lblArea);

		JLabel lblAreaName = new JLabel("Area Name");
		lblAreaName.setHorizontalAlignment(SwingConstants.CENTER);
		lblAreaName.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblAreaName.setBounds(66, 171, 153, 38);
		contentPane.add(lblAreaName);

		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(new Color(192, 192, 192));
		comboBox.setToolTipText("Select your Area");
		comboBox.setBounds(291, 181, 143, 28);
		contentPane.add(comboBox);

		JLabel lblAreaId = new JLabel("Area ID");
		lblAreaId.setHorizontalAlignment(SwingConstants.CENTER);
		lblAreaId.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblAreaId.setBounds(66, 238, 107, 34);
		contentPane.add(lblAreaId);

		lblNewLabel = new JLabel("Select Area");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(291, 241, 98, 34);
		contentPane.add(lblNewLabel);

		JButton btnDisplay = new JButton("DISPLAY");
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s = comboBox.getSelectedItem().toString();
				displayId(s);
			}
		});
		btnDisplay.setBackground(Color.LIGHT_GRAY);
		btnDisplay.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnDisplay.setBounds(180, 321, 153, 46);
		contentPane.add(btnDisplay);

		try {  
			Class.forName("oracle.jdbc.driver.OracleDriver");  

			Connection conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","B2170905466");  
			Statement st = conn.createStatement();

			ResultSet r=st.executeQuery("select area_name from area");

			while (r.next()) {  

				comboBox.addItem(r.getString("area_name"));  
			}


			conn.close();
		} catch (Exception e) {  
			JOptionPane.showMessageDialog(null,"Failed to Connect to Database","Error Connection", JOptionPane.WARNING_MESSAGE);  
			System.exit(0);  
		}
	}
	public void displayId(String s) {
		try {  
			Class.forName("oracle.jdbc.driver.OracleDriver");  

			Connection conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","B2170905466");  
			Statement st = conn.createStatement();

			ResultSet r=st.executeQuery("select area_id from area where area_name = '" + s + "'");

			while (r.next()) {  
				lblNewLabel.setText(r.getString(1));
			}


			conn.close();
		} catch (Exception e) {  
			JOptionPane.showMessageDialog(null,"Failed to Connect to Database","Error Connection", JOptionPane.WARNING_MESSAGE);  
			System.exit(0);  
		}
	}
}
