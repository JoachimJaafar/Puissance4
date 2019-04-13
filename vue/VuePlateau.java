package vue;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * Partie visuelle du Plateau.
 * @see JFrame
 * @see Plateau
 */
public class VuePlateau extends JFrame{
    /**
     * Panel de VuePlateau.
     * @see JPanel
     */
    JPanel lePanel = new JPanel();
    /**
     * Nombre de lignes du VuePlateau.
     */
    private int nbl;
    /**
     * Nombre de colonnes du VuePlateau.
     */
    private int nbc;
    /**
     * Grille du VuePlateau.
     */
    private VueCase[][] cases;
    /**
     * Boutons du VuePlateau.
     */
    private JButton[] boutons;

    /**
     * Constructeur du VuePlateau
     * @param titre Titre du jeu
     * @param nbl Nombre de lignes du jeu.
     * @param nbc Nombre de colonnes du jeu.
     */
    public VuePlateau(String titre, int nbl, int nbc){
        super(titre);
        this.nbl = nbl;
        this.nbc = nbc;
        this.setPreferredSize(new Dimension(700,800));
        lePanel.setBackground(Color.BLUE);
        lePanel.setLayout(new GridLayout(this.nbl + 1,this.nbc));

        this.cases = new VueCase[this.nbl][this.nbc];
        this.boutons = new JButton[this.nbc];
        
        //dessine les cercles sur la grille
	    for (int ligne = 0; ligne < this.nbl; ligne++) {
	        for (int col = 0; col < this.nbc; col++) {
	    		VueCase circle = new VueCase(Color.white);
                this.cases[ligne][col] = circle;
	    		lePanel.add(this.cases[ligne][col]);
	    	}
	    }
        
        //dessine les boutons
        for (int c = 0; c < this.nbc; c++){
        	JButton bouton=new JButton(c+"");
            this.boutons[c] = bouton;
    		lePanel.add(bouton);
        }
 
        setContentPane(lePanel);
        pack(); //permet de mettre une bonne dimension a la fenetre
        setVisible(true);
    }
    
    /**
     * Change la couleur de la VueCase à la position (x,y) de la grille par color.
     */
    public void changerCouleur(int x, int y, Color color){
    	cases[x][y].changeCouleur(color);
    	actualiser();
    }

    /**
     * @return La liste des boutons du VuePlateau.
     */
    public JButton[] getBoutons(){
        return this.boutons;
    }

    /**
     * @return La grille du VuePlateau.
     */
    public VueCase[][] getCases(){
        return this.cases;
    }

    /**
     * Repaint le panel.
     */
    public void actualiser(){
	    lePanel.repaint();
    }
    
    /**
     * Affiche un pop-up annoncant la fin de la partie.
     */
    public void partieFinie(String message){
    	JOptionPane.showMessageDialog(null, message, "La partie est finie.", JOptionPane.INFORMATION_MESSAGE);
    	setVisible(false); 
        dispose();
    }
}