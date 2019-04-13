package vue;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Partie visuelle de la Case.
 * @see JPanel
 * @see Case
 */
public class VueCase extends JPanel {
	/**
     * Couleur de la Case.
	 * @see Color
     */
	private Color couleur;
	
	/**
     * Constructeur de VueCase.
	 * @param couleur Couleur de la Case.
     */
	public VueCase(Color couleur){
		this.couleur = couleur;
	}
	
	/**
     * Change la couleur de la VueCase.
	 * @param couleur Nouvelle couleur de la VueCase
     */
	public void changeCouleur(Color couleur){
		this.couleur = couleur;
	}

	/**
     * Repaint la VueCase.
	 * @param graph Nouveau graphique de la VueCase
	 * @see Graphics
     */
	@Override
	protected void paintComponent(Graphics graph) {
		graph.setColor(this.couleur);
		graph.fillOval(0, 0, graph.getClipBounds().width, graph.getClipBounds().height);
	}
}
