package graphique;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.HeadlessException;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import Utilisateur.User;
import connection.DaoUser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Color;

public class Administration extends JFrame {
	
	private JScrollPane scrollPane;
	private JButton btnFinish;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnAdd;
	private JButton btnClear;
	private JTable table;
	private JTextField tfprenom;
	private JTextField tfnom;
	private JTextField tflogin;
	private JTextField tfpw;
	private static final long serialVersionUID = 1L;
	DefaultTableModel model;
	private Authentification authentification;
	private JButton btnTruncate;
	
	public Administration(Authentification authentification){
		
		this.authentification = authentification;
		
		setSize(new Dimension(840, 600));
		setTitle("Gestion des utilisateurs");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 822, 557);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(10, 11, 802, 46);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Gestion des utilisateurs");
		lblNewLabel.setBounds(10, 11, 782, 24);
		panel_1.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 17));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 68, 822, 489);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBounds(10, 204, 286, 106);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				if(i >=0) {
					int reponse = JOptionPane.showConfirmDialog(null, "Sure ?");
					if(reponse == JOptionPane.YES_OPTION) {
						try {
							User user = new User(model.getValueAt(i, 1).toString(), model.getValueAt(i, 2).toString(), model.getValueAt(i, 3).toString(), model.getValueAt(i, 4).toString());
							DaoUser.delete(DaoUser.getDBId(user));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						model.removeRow(i);
						JOptionPane.showMessageDialog(null, "Deleted Succesfully !");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Please select a row first !");
				}
				clear();
			}
		});
		btnDelete.setFont(new Font("Arial", Font.PLAIN, 13));
		btnDelete.setBounds(10, 58, 128, 36);
		panel_3.add(btnDelete);
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnClear.setFont(new Font("Arial", Font.PLAIN, 13));
		btnClear.setBounds(148, 58, 128, 36);
		panel_3.add(btnClear);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_4.setBounds(306, 11, 506, 467);
		panel_2.add(panel_4);
		panel_4.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 486, 445);
		panel_4.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		model = new DefaultTableModel();
		Object[] column = {"ID", "Prénom", "Nom", "Login", "Password"};
		final Object[] row = new Object[5];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int i=table.getSelectedRow();
				tfprenom.setText(model.getValueAt(i, 1).toString());
				tfnom.setText(model.getValueAt(i, 2).toString());
				tflogin.setText(model.getValueAt(i, 3).toString());
				tfpw.setText(model.getValueAt(i, 4).toString());
			}
		});
		scrollPane.setViewportView(table);
		
		try {
			lister();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfprenom.getText().isEmpty() || tfnom.getText().isEmpty() || tflogin.getText().isEmpty() || tfpw.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill complete information !");
				}
				else {
					int i=table.getSelectedRow();
					String row11 = (String) model.getValueAt(i, 1);
					String row22 = (String) model.getValueAt(i, 2);
					String row33 = (String) model.getValueAt(i, 3);
					String row44 = (String) model.getValueAt(i, 4);
					User usr1 = new User(row11, row22, row33, row44);
					
					String row1 = tfprenom.getText();
					String row2 = tfnom.getText();
					String row3 = tflogin.getText();
					String row4 = tfpw.getText();
					
					User usr = new User(row1, row2, row3, row4);
					try {
						if(usr1.getPrenom().equals(usr.getPrenom()) && usr1.getNom().equals(usr.getNom())
								&& usr1.getLogin().equals(usr.getLogin()) && usr1.getPassword().equals(usr.getPassword())) {
							JOptionPane.showMessageDialog(null, "User already exist !");
						}
						else {
							try {
								DaoUser.update(usr,DaoUser.getDBId(usr1));
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							try {
								model.setValueAt(row1, i, 1);
								model.setValueAt(row2, i, 2);
								model.setValueAt(row3, i, 3);
								model.setValueAt(row4, i, 4);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							clear();
							JOptionPane.showMessageDialog(null, "Updated succesfully !");
						}
					}  catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnUpdate.setFont(new Font("Arial", Font.PLAIN, 13));
		btnUpdate.setBounds(148, 11, 128, 36);
		panel_3.add(btnUpdate);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfprenom.getText().isEmpty() || tfnom.getText().isEmpty() || tflogin.getText().isEmpty() || tfpw.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill complete information !");
				}
				else {
					String row1 = tfprenom.getText();
					String row2 = tfnom.getText();
					String row3 = tflogin.getText();
					String row4 = tfpw.getText();
					User usr = new User(row1, row2, row3, row4);
					try {
						if(DaoUser.check(DaoUser.getDBId(usr)) == true) {
							JOptionPane.showMessageDialog(null, "User already exist !");
						}
						else {
							try {
								DaoUser.create(usr);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							try {
								row[0] = DaoUser.getDBId(usr);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							row[1] = usr.getPrenom();
							row[2] = usr.getNom();
							row[3] = usr.getLogin();
							row[4] = usr.getPassword();
							model.addRow(row);
							clear();
							JOptionPane.showMessageDialog(null, "Added succesfully !");
						}
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		
		btnAdd.setFont(new Font("Arial", Font.PLAIN, 13));
		btnAdd.setBounds(10, 11, 128, 36);
		panel_3.add(btnAdd);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Information sur l'utilisateur", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(10, 39, 286, 154);
		panel_2.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Pr\u00E9nom : ");
		lblNewLabel_1.setBounds(23, 27, 253, 20);
		panel_5.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 13));
		
		tfprenom = new JTextField();
		tfprenom.setBounds(94, 27, 182, 20);
		panel_5.add(tfprenom);
		tfprenom.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nom : ");
		lblNewLabel_1_1.setBounds(39, 58, 237, 20);
		panel_5.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 13));
		
		tfnom = new JTextField();
		tfnom.setBounds(94, 58, 182, 20);
		panel_5.add(tfnom);
		tfnom.setColumns(10);
		
		JLabel lblNewLabel_1_2 = new JLabel(" Login : ");
		lblNewLabel_1_2.setBounds(30, 89, 246, 20);
		panel_5.add(lblNewLabel_1_2);
		lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 13));
		
		tflogin = new JTextField();
		tflogin.setBounds(94, 89, 182, 20);
		panel_5.add(tflogin);
		tflogin.setColumns(10);
		
		JLabel lblNewLabel_1_3 = new JLabel("Password : ");
		lblNewLabel_1_3.setBounds(10, 120, 266, 20);
		panel_5.add(lblNewLabel_1_3);
		lblNewLabel_1_3.setFont(new Font("Arial", Font.PLAIN, 13));
		
		tfpw = new JTextField();
		tfpw.setBounds(94, 120, 182, 20);
		panel_5.add(tfpw);
		tfpw.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_6.setBounds(10, 429, 286, 49);
		panel_2.add(panel_6);
		panel_6.setLayout(null);
		
		btnFinish = new JButton("Finish");
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClick();
			}
		});
		btnFinish.setBounds(75, 11, 130, 27);
		btnFinish.setFont(new Font("Arial", Font.PLAIN, 13));
		panel_6.add(btnFinish);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(10, 340, 286, 49);
		panel_2.add(panel_7);
		panel_7.setLayout(null);
		
		btnTruncate = new JButton("TRUNCATE");
		btnTruncate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rep =JOptionPane.showConfirmDialog(null, "Really ?");
				if(rep == JOptionPane.YES_OPTION) {
					try {
					DaoUser.truncate();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					viderTable();
				}
			}
		});
		btnTruncate.setForeground(Color.RED);
		btnTruncate.setFont(new Font("Arial", Font.BOLD, 13));
		btnTruncate.setBounds(73, 11, 133, 27);
		panel_7.add(btnTruncate);
	}
	protected void onClick() {
		dispose();
		authentification.run();
		
	}
	public void run() {
		setVisible(true);
	}
	public void close() {
		setVisible(false);
	}
	protected void clear () {
		tfprenom.setText(null);
		tfnom.setText(null);
		tflogin.setText(null);
		tfpw.setText(null);
	}
	
	protected void lister() throws Exception {
		final Object[] row = new Object[5];
		List<User> users = DaoUser.list();
		for(User user:users) {
			try {
				row[0] = DaoUser.getDBId(user);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			row[1] = user.getPrenom();
			row[2] = user.getNom();
			row[3] = user.getLogin();
			row[4] = user.getPassword();
			model.addRow(row);
		}
	}
	
	protected void viderTable() {
		for(int i = model.getRowCount(); i > 0; --i)
			model.removeRow(i-1);
	}
}