package graphique;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import Produit.Produit;
import connection.DaoProduit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class Gestion extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JButton btnDel;
	private JButton btnEdit;
	private JButton btnAdd;
	private JScrollPane scrollPane;
	private Authentification authentification;
	private JButton btnDeco;
	private DefaultTableModel model;
	private DefaultComboBoxModel<Object> model1;
	private JTextField textNom;
	private JTextField textQuantite;
	private JTextField textPrix;
	private JTextArea textDescription;
	private JScrollPane scrollPane_1;
	private String[] element;
	private JComboBox<Object> comboBox;
	public int combo;
	private JLabel lblTotal;
	
	public Gestion(Authentification authentification, String prenom, String nom) {
		this.authentification = authentification;
		
		setSize(new Dimension(1350, 892));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Gestion");
		setResizable(false);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(10, 11, 1311, 60);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Liste des produits");
		lblNewLabel.setFont(new Font("Cambria", Font.BOLD, 31));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 1291, 38);
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(10, 148, 841, 694);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 821, 672);
		panel_2.add(scrollPane);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBounds(1151, 796, 170, 46);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		btnDeco = new JButton("D\u00E9connection");
		btnDeco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onclick();
			}
		});
		btnDeco.setForeground(Color.BLACK);
		btnDeco.setFont(new Font("Arial", Font.PLAIN, 17));
		btnDeco.setBounds(10, 11, 150, 24);
		panel_3.add(btnDeco);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_5.setLayout(null);
		panel_5.setBounds(861, 82, 459, 501);
		panel.add(panel_5);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1_1.setBounds(11, 11, 437, 58);
		panel_5.add(panel_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("D\u00E9tails sur le produit");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 31));
		lblNewLabel_1.setBounds(10, 11, 417, 36);
		panel_1_1.add(lblNewLabel_1);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2_1.setBounds(11, 80, 437, 410);
		panel_5.add(panel_2_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nom : ");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(28, 32, 102, 29);
		panel_2_1.add(lblNewLabel_1_1);
		
		textNom = new JTextField();
		textNom.setFont(new Font("Arial", Font.PLAIN, 15));
		textNom.setColumns(10);
		textNom.setBounds(140, 32, 229, 29);
		panel_2_1.add(textNom);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Description : ");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(28, 77, 102, 29);
		panel_2_1.add(lblNewLabel_1_1_1);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(140, 79, 229, 134);
		panel_2_1.add(scrollPane_1);
		
		textDescription = new JTextArea();
		textDescription.setFont(new Font("Arial", Font.PLAIN, 15));
		scrollPane_1.setViewportView(textDescription);
		textDescription.setLineWrap(true);
		textDescription.setWrapStyleWord(true);
		
		
		JLabel lblNewLabel_1_2 = new JLabel("Cat\u00E9gorie : ");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(28, 234, 102, 29);
		panel_2_1.add(lblNewLabel_1_2);
		
		comboBox = new JComboBox<Object>();
		element = new String[] {"Lait de corps", "Parfums", "Gel de bain", "Produit de cheveux", "Soin de visage", "Accessoires de cheveux", "Produit manicure", "Autres"};
		model1 = new DefaultComboBoxModel<Object>(element);
		comboBox.setModel(model1);
		comboBox.setSelectedIndex(-1);
		comboBox.setFont(new Font("Arial", Font.PLAIN, 15));
		comboBox.setBounds(140, 235, 229, 29);
		panel_2_1.add(comboBox);
		
		JLabel lblNewLabel_1_3 = new JLabel("Quantit\u00E9 : ");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_3.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_1_3.setBounds(28, 293, 102, 29);
		panel_2_1.add(lblNewLabel_1_3);
		
		textQuantite = new JTextField();
		textQuantite.setFont(new Font("Arial", Font.PLAIN, 15));
		textQuantite.setColumns(10);
		textQuantite.setBounds(140, 293, 229, 29);
		panel_2_1.add(textQuantite);
		
		JLabel lblNewLabel_1_4 = new JLabel("Prix : ");
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_1_4.setBounds(28, 349, 102, 29);
		panel_2_1.add(lblNewLabel_1_4);
		
		textPrix = new JTextField();
		textPrix.setFont(new Font("Arial", Font.PLAIN, 15));
		textPrix.setColumns(10);
		textPrix.setBounds(140, 349, 229, 29);
		panel_2_1.add(textPrix);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(861, 594, 460, 141);
		panel.add(panel_4);
		panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_4.setLayout(null);
		
		table = new JTable();
		table.setFont(new Font("Arial", Font.PLAIN, 13));
		model = new DefaultTableModel();
		Object[] column = {"Quantité", "Nom", "Description", "Prix", "Catégorie"};
		model.setColumnIdentifiers(column);
		table.setModel(model);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int i=table.getSelectedRow();
				textNom.setText(model.getValueAt(i, 1).toString());
				textDescription.setText(model.getValueAt(i, 2).toString());
				textPrix.setText(model.getValueAt(i, 3).toString());
				textQuantite.setText(model.getValueAt(i, 0).toString());
				comboBox.setSelectedItem(model.getValueAt(i, 4));
			}
		});
		scrollPane.setViewportView(table);
		btnAdd = new JButton("Ajouter");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textNom.getText().isEmpty() || textDescription.getText().isEmpty() ||
						comboBox.getSelectedIndex() < 0 || textQuantite.getText().isEmpty() ||
						textPrix.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Veuillez remplir toutes les cases !");
				}
				else {
					String nom = textNom.getText();
					String description = textDescription.getText();
					String categorie = (String)comboBox.getSelectedItem();
					int quantite = (int)Double.parseDouble(textQuantite.getText());
					int prix = (int)Double.parseDouble(textPrix.getText());
					Produit produit = new Produit(nom, description, prix, quantite, categorie);
					try {
						if(DaoProduit.check(DaoProduit.getDBId(produit)) == true) {
							JOptionPane.showMessageDialog(null, "Ce produit existe déjà !");
						}
						else if(prix < 0) {
							JOptionPane.showMessageDialog(null, "Le prix doit être positif !");
						}
						else if(quantite < 0) {
							JOptionPane.showMessageDialog(null, "La quantité doit être positive !");
						}
						else {
							DaoProduit.create(produit);
							clear();
							Object[] row = new Object[5];
							row[0] = produit.getQuantite();
							row[1] = produit.getNom();
							row[2] = produit.getDescription();
							row[3] = produit.getPrix();
							row[4] = produit.getCategorie();
							model.addRow(row);
							valeurBoutique();
							JOptionPane.showMessageDialog(null, "Produit ajouté !");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnAdd.setFont(new Font("Arial", Font.BOLD, 15));
		btnAdd.setBounds(122, 11, 242, 32);
		panel_4.add(btnAdd);
		
		btnEdit = new JButton("Modifier");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=table.getSelectedRow();
				if(i < 0)
					JOptionPane.showMessageDialog(null, "Aucun produit selectionné !");
				else {	
					if(textNom.getText().isEmpty() || textDescription.getText().isEmpty() ||
							comboBox.getSelectedIndex() < 0 || textQuantite.getText().isEmpty() ||
							textPrix.getText().isEmpty()){
						JOptionPane.showMessageDialog(null, "Veuillez remplir toutes les cases !");
					}
					else {
						String nom1 = (String) model.getValueAt(i, 1);
						String description1 = (String) model.getValueAt(i, 2);
						int quantite1 = (int) model.getValueAt(i, 0);
						String categorie1 = (String) model.getValueAt(i, 4);
						int prix1 = (int) model.getValueAt(i, 3);
						Produit produit1 = new Produit(nom1, description1, prix1, quantite1, categorie1);
						
						String nom = textNom.getText();
						String description = textDescription.getText();
						String categorie = (String)comboBox.getSelectedItem();
						int quantite = (int)Double.parseDouble(textQuantite.getText());
						int prix = (int)Double.parseDouble(textPrix.getText());
						Produit produit = new Produit(nom, description, prix, quantite, categorie);
						try {
							int i1 = DaoProduit.getDBId(produit);
							if(DaoProduit.check(i1) == true) {
								JOptionPane.showMessageDialog(null, "Ce produit existe déjà !");
								System.out.println(i1);
							}
							else if(prix < 0) {
								JOptionPane.showMessageDialog(null, "Le prix doit être positif !");
							}
							else if(quantite < 0) {
								JOptionPane.showMessageDialog(null, "La quantité doit être positive !");
							}
							else {
								DaoProduit.update(produit, DaoProduit.getDBId(produit1));
								clear();
								model.setValueAt(quantite, i, 0);
								model.setValueAt(nom, i, 1);
								model.setValueAt(description, i, 2);
								model.setValueAt(prix, i, 3);
								model.setValueAt(categorie, i, 4);
								valeurBoutique();
								JOptionPane.showMessageDialog(null, "Produit modifié !");
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnEdit.setFont(new Font("Arial", Font.BOLD, 15));
		btnEdit.setBounds(122, 54, 242, 32);
		panel_4.add(btnEdit);
		
		btnDel = new JButton("Supprimer");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				if(i >=0) {
					int reponse = JOptionPane.showConfirmDialog(null, "Sure ?");
					String nom = textNom.getText();
					String description = textDescription.getText();
					String categorie = (String)comboBox.getSelectedItem();
					int quantite = (int)Double.parseDouble(textQuantite.getText());
					int prix = (int)Double.parseDouble(textPrix.getText());
					Produit produit = new Produit(nom, description, prix, quantite, categorie);
					
					if(reponse == JOptionPane.YES_OPTION) {
						try {
							DaoProduit.delete(DaoProduit.getDBId(produit));
							model.removeRow(i);
							valeurBoutique();
							JOptionPane.showMessageDialog(null, "Supprimé avec succès !");
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Aucun produit selectionné !");
				}
			}
		});
		btnDel.setForeground(Color.RED);
		btnDel.setFont(new Font("Arial", Font.BOLD, 15));
		btnDel.setBounds(122, 97, 242, 32);
		panel_4.add(btnDel);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_6.setBounds(10, 82, 841, 60);
		panel.add(panel_6);
		panel_6.setLayout(null);
		
		JLabel lblBonjour = new JLabel("Bonjour, " + prenom + " " + nom);
		lblBonjour.setFont(new Font("Arial", Font.PLAIN, 17));
		lblBonjour.setBounds(10, 11, 821, 38);
		panel_6.add(lblBonjour);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_7.setBounds(861, 746, 150, 96);
		panel.add(panel_7);
		panel_7.setLayout(null);
		
		lblTotal = new JLabel();
		lblTotal.setFont(new Font("Arial", Font.BOLD, 21));
		lblTotal.setForeground(Color.DARK_GRAY);
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setBounds(10, 11, 130, 74);
		panel_7.add(lblTotal);
		
		try {
			lister();
			valeurBoutique();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	protected void onclick() {
		int rep = JOptionPane.showConfirmDialog(null, "Sure ?");
		if(rep == JOptionPane.YES_OPTION) {
			dispose();
			authentification.run();
		}
		
	}

	public void run() {
		setVisible(true);
	}
	public void close() {
		setVisible(false);
	}
	public void clear() {
		textNom.setText(null);
		textDescription.setText(null);
		comboBox.setSelectedIndex(-1);
		textQuantite.setText(null);
		textPrix.setText(null);
	}
	protected void lister() throws Exception {
		final Object[] row = new Object[5];
		List<Produit> produits = DaoProduit.list();
		for(Produit produit:produits) {
			row[0] = produit.getQuantite();
			row[1] = produit.getNom();
			row[2] = produit.getDescription();
			row[3] = produit.getPrix();
			row[4] = produit.getCategorie();
			model.addRow(row);
		}
	}
	protected void valeurBoutique() throws Exception {
		List<Produit> produits = DaoProduit.list();
		int total = 0;
		for(Produit produit:produits) {
			total+=produit.prixTotal();
		}
		lblTotal.setText(String.valueOf(total));

	}
	protected void viderTable() {
		for(int i = model.getRowCount(); i > 0; --i)
			model.removeRow(i-1);
	}
}
