package graphique;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.FlowLayout;
import java.awt.Dimension;
import connection.DaoUser;
import javax.swing.border.EtchedBorder;
import Utilisateur.User;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Authentification extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextField loginField;
	private JPasswordField passwordField;
	private AbstractButton btnOK;
	private JButton btnCANCEL;
	private JLabel lblAvertissement;
		
	public Authentification() {
		
		setSize(new Dimension(460, 300));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setTitle("Authentification");
		getContentPane().setLayout(new GridLayout(3, 1, 0, 0));
		setResizable(false);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		FlowLayout flowLayout_3 = (FlowLayout) panel.getLayout();
		flowLayout_3.setVgap(25);
		getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Sign In");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Sitka Text", Font.BOLD, 25));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Login : ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(97, 5, 77, 21);
		lblNewLabel_1.setFont(new Font("Segoe UI Variable", Font.PLAIN, 15));
		panel_3.add(lblNewLabel_1);
		
		loginField = new JTextField();
		loginField.setBounds(180, 8, 166, 20);
		panel_3.add(loginField);
		loginField.setColumns(20);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(null);
		panel_1.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Password : ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(80, 0, 95, 21);
		lblNewLabel_2.setFont(new Font("Segoe UI Variable", Font.PLAIN, 15));
		panel_4.add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(181, 3, 166, 20);
		passwordField.setColumns(20);
		panel_4.add(passwordField);
		
		lblAvertissement = new JLabel("");
		lblAvertissement.setForeground(Color.RED);
		lblAvertissement.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblAvertissement.setBounds(181, 27, 166, 14);
		panel_4.add(lblAvertissement);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setVgap(25);
		getContentPane().add(panel_2);
		
		btnOK = new JButton("OK");
		btnOK.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							onOkClick();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
			}
		});
		btnOK.setPreferredSize(new Dimension(100, 30));
		panel_2.add(btnOK);
		
		btnCANCEL = new JButton("CANCEL");
		btnCANCEL.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						onCancelClick();
			}
		});
		btnCANCEL.setPreferredSize(new Dimension(100, 30));
		panel_2.add(btnCANCEL);
	}
	
	protected void onOkClick() throws Exception {
		String login = loginField.getText();
		String password = String.valueOf(passwordField.getPassword());
		clear();
		User usr = new User(login, password);
		int id = DaoUser.getDBId(usr);
		if(login.equals("admin") && password.equals("admin")) {
			Administration admin = new Administration(this);
			close();
			admin.run();
		}
		else if(DaoUser.check(id) == true) {
			User u = DaoUser.read(id);
			Gestion ui = new Gestion(this, u.getPrenom(), u.getNom());
			close();
			ui.run();
		}
		else {
			JOptionPane.showMessageDialog(null, "Login ou Password erroné !");
		}
	}
	
	protected void onCancelClick() {
		clear();
		int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter ?");
		
		if (reponse == JOptionPane.YES_OPTION) {
			dispose();
			System.exit(0);
		}
	}
	
	protected void clear () {
		loginField.setText(null);
		passwordField.setText(null);
	}
	
	public void run () {
		setVisible(true);
	}
	
	public void close () {
		setVisible(false);
	}
}
