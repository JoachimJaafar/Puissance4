import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.Color;

import controleur.Controleur;
import modele.Plateau;
import modele.Joueur;

/**
 * Classe principale, ouvre un menu pour modifier les paramètres des parties.
 */
public class Main extends JFrame{
	
	//Partie options
	private JComboBox<Integer> cbxnbl;
	private int nbl;
	private JComboBox<Integer> cbxnbc;
	private int nbc;
	private JComboBox<Integer> cbxpions;
	private int nbpions;


	//Partie joueurs
	private JTextField txtnomj1;
	private JComboBox<String> cbxj1;

	private JTextField txtnomj2;
    private JComboBox<String> cbxj2;
    
    private String[] couleurs = {"Gris", "Cyan", "Jaune", "Noir", "Orange", "Rose", "Rouge", "Vert"};

	//Label qui nous envoie les potentielles erreurs de paramètres (nbl et nbc plus petits que nbPions, noms ou couleurs des joueurs identiques).
    private JLabel lblerreur;
    
    public static void main(String[] args){
        Main m = new Main();
    }

	/**
	 * Constructeur du Main, qui est un menu de configuration.
	 */
	public Main() {
		//Cree la fenetre
		super("Puissance 4");
		setSize(2200,700);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setLayout(new BorderLayout());
		ajouteElt();
		pack();
		setVisible(true);
	}

	/**
	 * @return Si les paramètres des parties sont correctes : nbl et nbc plus grands que nbPions, noms et couleurs des joueurs différents.
	 */
	public boolean optionsCorrectes(){
		String coulj1 = String.valueOf(this.cbxj1.getSelectedItem());
		String coulj2 = String.valueOf(this.cbxj2.getSelectedItem());
		this.nbl = (Integer)this.cbxnbl.getSelectedItem(); 
		this.nbc = (Integer)this.cbxnbc.getSelectedItem(); 
		this.nbpions = (Integer)this.cbxpions.getSelectedItem();
		boolean nbpionscorrect = (this.nbl > this.nbc ? this.nbpions <= this.nbc : this.nbpions <= this.nbl);
		return !this.txtnomj1.getText().equals(this.txtnomj2.getText()) && !coulj1.equals(coulj2) && nbpionscorrect;
	}

	/**
	 * Modifie le label nous indiquants des erreurs.
	 * @param str Nouveau message dans le label d'erreur.
	 */
	public void setErreur(String str){
		this.lblerreur.setText(str);
	}
	
	/**
	 * Ajoute tous les elements (boutons, checkbox, textfield ...) au menu.
	 */
	public void ajouteElt(){
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Color.BLACK);

		//Joueurs

		JPanel panelJoueurs = new JPanel();
		panelJoueurs.setBackground(Color.YELLOW);
		panelJoueurs.setPreferredSize(new Dimension(200, 220));
		JLabel lblJoueurs = new JLabel("JOUEURS");
		lblJoueurs.setFont(new Font("Arial",Font.BOLD, 30));
		panelJoueurs.add(lblJoueurs);

		JLabel lblj1 = new JLabel("Joueur 1 : ");
		lblj1.setFont(new Font("Arial",Font.BOLD, 20));
		this.txtnomj1 = new JTextField("J1");
		this.txtnomj1.setPreferredSize(new Dimension(100, 20));
		this.cbxj1 = new JComboBox<String>(this.couleurs);
		this.cbxj1.setSelectedIndex(2);
		panelJoueurs.add(lblj1);
		panelJoueurs.add(this.txtnomj1);
		panelJoueurs.add(this.cbxj1);
		JLabel lblj2 = new JLabel("Joueur 2 : ");
		lblj2.setFont(new Font("Arial",Font.BOLD, 20));
		txtnomj2 = new JTextField("J2");
		this.txtnomj2.setPreferredSize(new Dimension(100, 20));
		this.cbxj2 = new JComboBox<String>(this.couleurs);
		this.cbxj2.setSelectedIndex(6);
		panelJoueurs.add(lblj2);
		panelJoueurs.add(this.txtnomj2);
		panelJoueurs.add(this.cbxj2);

		//Options

		JPanel panelOptions = new JPanel();
		panelOptions.setBackground(Color.BLUE);
		panelOptions.setPreferredSize(new Dimension(200, 220));
		JLabel lblOptions = new JLabel("OPTIONS");
		lblOptions.setFont(new Font("Arial",Font.BOLD, 30));
		panelOptions.add(lblOptions);
		Integer[] tailles = new Integer[16];
		for(int i=0, inc=3;i<16;i++,inc++){
			tailles[i]= inc;
		}
		JLabel lblnbl = new JLabel("Nombre de lignes : ");
		lblnbl.setFont(new Font("Arial",Font.BOLD, 10));
		this.cbxnbl = new JComboBox<Integer>(tailles);
		this.cbxnbl.setSelectedIndex(4);
		panelOptions.add(lblnbl);
		panelOptions.add(this.cbxnbl);
		JLabel lblnbc = new JLabel("Nombre de colonnes : ");
		lblnbc.setFont(new Font("Arial",Font.BOLD, 10));
		this.cbxnbc = new JComboBox<Integer>(tailles);
		this.cbxnbc.setSelectedIndex(3);
		panelOptions.add(lblnbc);
		panelOptions.add(this.cbxnbc);
		JLabel lblpions = new JLabel("Nb de pions pour gagner : ");
		lblpions.setFont(new Font("Arial",Font.BOLD, 10));
		this.cbxpions = new JComboBox<Integer>(tailles);
		this.cbxpions.setSelectedIndex(1);
		panelOptions.add(lblpions);
		panelOptions.add(this.cbxpions);
		JCheckBox modesuicide = new JCheckBox("Mode Suicide");
		modesuicide.setFont(new Font("Arial",Font.BOLD, 18));
		modesuicide.setBackground(Color.BLUE);
		panelOptions.add(modesuicide);
		JCheckBox modeia = new JCheckBox("Mode Contre IA");
		modeia.setFont(new Font("Arial",Font.BOLD, 18));
		modeia.setBackground(Color.BLUE);
		panelOptions.add(modeia);

		//Boutons

		JPanel panelBoutons = new JPanel();
		panelBoutons.setBackground(Color.RED);
		panelBoutons.setPreferredSize(new Dimension(200, 220));
		JButton quitter = new JButton("Quitter");
		quitter.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					System.exit(0);
				}
			}
		);
		JButton lancer = new JButton("Lancer");
		lancer.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//si les paramètres de jeu sont correctes
					if(optionsCorrectes()){
						setErreur("");
						//choix des couleurs pour les joueurs
                        Color couleurj1 = Color.WHITE, couleurj2 = Color.WHITE;
                        switch(String.valueOf(cbxj1.getSelectedItem())){
                            case "Gris":    couleurj1 = Color.GRAY;
                                            break;
                            case "Cyan":    couleurj1 = Color.CYAN;
                                            break;
                            case "Jaune":    couleurj1 = Color.YELLOW;
                                            break;
                            case "Noir":    couleurj1 = Color.BLACK;
                                            break;
                            case "Orange":  couleurj1 = Color.ORANGE;
                                            break;
                            case "Rose":    couleurj1 = Color.PINK;
                                            break;
                            case "Rouge":    couleurj1 = Color.RED;
                                            break;
                            case "Vert":    couleurj1 = Color.GREEN;
                                            break;
                        }
                        switch(String.valueOf(cbxj2.getSelectedItem())){
                            case "Gris":    couleurj2 = Color.GRAY;
                                            break;
                            case "Cyan":    couleurj2 = Color.CYAN;
                                            break;
                            case "Jaune":    couleurj2 = Color.YELLOW;
                                            break;
                            case "Noir":    couleurj2 = Color.BLACK;
                                            break;
                            case "Orange":  couleurj2 = Color.ORANGE;
                                            break;
                            case "Rose":    couleurj2 = Color.PINK;
                                            break;
                            case "Rouge":    couleurj2 = Color.RED;
                                            break;
                            case "Vert":    couleurj2 = Color.GREEN;
                                            break;
						}
						//titre de la fenetre de la partie. Il depend des parametres du menu. 
						String titre = (modesuicide.isSelected() ? "Mode Suicide - " : "") + nbl + "x" + nbc + " - " + nbpions + " pions - " + txtnomj1.getText() + " (" + String.valueOf(cbxj1.getSelectedItem()) + ") vs " + (modeia.isSelected() ? "[IA] " : "") + txtnomj2.getText() + " (" + String.valueOf(cbxj2.getSelectedItem()) + ")";
						//cree un plateau avec les parametres du menu.
						Plateau p = new Plateau(titre, nbl, nbc, new Joueur(txtnomj1.getText(), couleurj1), new Joueur((modeia.isSelected() ? "[IA] " : "") + txtnomj2.getText(), couleurj2), nbpions, modesuicide.isSelected(), modeia.isSelected());
						//cree un controleur basé sur ce plateau.
						Controleur c = new Controleur(p);
				    }
					else setErreur("Erreur dans les paramétrages !");
				}
			}
		);
		//initialise le label d'erreur.
		this.lblerreur = new JLabel("");
		this.lblerreur.setFont(new Font("Arial",Font.BOLD, 10));
		//ajoute les boutons "Quitter" et "Lancer" et le label d'erreur.
		panelBoutons.add(quitter);
		panelBoutons.add(lancer);
		panelBoutons.add(this.lblerreur);

		//ajoute les panel a la fenetre
		panelPrincipal.add(panelOptions);
		panelPrincipal.add(panelJoueurs);
		panelPrincipal.add(panelBoutons, BorderLayout.SOUTH);
		this.add(panelPrincipal);
	}
}