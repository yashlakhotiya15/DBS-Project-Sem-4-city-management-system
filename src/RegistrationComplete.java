import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class RegistrationComplete extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public JLabel lblUserid;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			RegistrationComplete dialog = new RegistrationComplete();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public RegistrationComplete(String id) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 432, 20);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 218, 432, 35);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Login login = new Login();
						login.setVisible(true);
						//JDialog#dispose();
						//super.dispose();
					}
				});
				okButton.addActionListener(e -> this.dispose());
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
		JLabel lblYourUniqueUser = new JLabel("Your Unique User ID is:");
		lblYourUniqueUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourUniqueUser.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblYourUniqueUser.setBounds(29, 109, 213, 33);
		getContentPane().add(lblYourUniqueUser);
		
		JLabel lblRegistrationSuccessful = new JLabel("REGISTRATION SUCCESSFUL");
		lblRegistrationSuccessful.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrationSuccessful.setFont(new Font("Dialog", Font.BOLD, 25));
		lblRegistrationSuccessful.setBounds(8, 37, 420, 33);
		getContentPane().add(lblRegistrationSuccessful);
		
		JLabel lblUserid = new JLabel(id);
		lblUserid.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUserid.setBounds(263, 115, 122, 20);
		getContentPane().add(lblUserid);
		
	}
}
