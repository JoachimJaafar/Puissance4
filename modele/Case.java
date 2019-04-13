package modele;
import java.awt.Color;

/**
 * Classe Case dans le plateau du jeu.
 * @author Joachim Jaafar
 */
public class Case{
    /**
     * Couleur de la case.
     * @see Color
     */
    private Color couleur;

    /**
     * Joueur auquel la Case appartient.
     * @see Joueur
     */
    private Joueur j = null;

    /**
     * Constructeur de la Case.
     * @param couleur
     *              Couleur de base de la Case.
     */
    public Case(Color couleur){
        this.couleur = couleur;
    }

    /**
     * @return La couleur de la Case.
     */
    public Color getCouleur(){
        return this.couleur;
    }

    /**
     * @return Si la case est occupée par un joueur.
     */
    public boolean estOccupe(){
        return this.j != null;
    }

    /**
     * Assigne la case à un joueur.
     * @param j
     *              Joueur à qui la case va appartenuir.
     */
    public void devientOccupe(Joueur j){
        if(!this.estOccupe()){
            this.j = j;
            this.couleur = j.getCouleur();
        }
        else System.out.println("Cette case est déjà occupée !\n");
    }
}