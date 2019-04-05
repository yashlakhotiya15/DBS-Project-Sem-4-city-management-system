import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.ActionEvent;

public class LoginAs extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginAs frame = new LoginAs();
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
	public LoginAs() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 530);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLoginAs = new JLabel("LOGIN AS");
		lblLoginAs.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginAs.setFont(new Font("Yu Gothic", Font.BOLD, 50));
		lblLoginAs.setBounds(114, 42, 319, 69);
		contentPane.add(lblLoginAs);
		
		JButton btnCitizen = new JButton("CITIZEN");
		btnCitizen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
			}
		});
		btnCitizen.addActionListener(e -> this.dispose());
		btnCitizen.setFont(new Font("Tahoma", Font.BOLD, 29));
		btnCitizen.setBounds(189, 189, 176, 43);
		contentPane.add(btnCitizen);
		
		JButton btnEmployee = new JButton("EMPLOYEE");
		btnEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					EmployeeLogin empLogin = new EmployeeLogin();
					empLogin.setVisible(true);
			}
		});
		btnEmployee.addActionListener(e -> this.dispose());
		btnEmployee.setFont(new Font("Tahoma", Font.BOLD, 29));
		btnEmployee.setBounds(178, 298, 201, 43);
		contentPane.add(btnEmployee);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\lakho\\Desktop\\DBSProject\\dust-blue-particles_-1x0xewgb__F0000.png"));
		lblNewLabel.setBounds(0, 0, 562, 483);
		contentPane.add(lblNewLabel);
	}
}
