package modele;
import java.util.Scanner;
import java.awt.Color;

/**
 * Classe Joueur dans le jeu.
 * @author Joachim Jaafar
 */
public class Joueur{
    /**
     * Nom du Joueur.
     */
    private String nom;

    /**
     * Couleur des cases du Joueur.
     * @see Color
     */
    private Color couleur;

    /**
     * Constructeur du Joueur.
     * @param nom
     *              Nom du joueur.
     * @param couleur
     *              Couleur des cases du Joueur.
     */
    public Joueur(String nom, Color couleur){
        this.nom = nom;
        this.couleur = couleur;
    }

    /**
     * @return Le nom du Joueur.
     */
    public String getNom(){
        return this.nom;
    }

    /**
     * @return La couleur des cases du Joueur.
     */
    public Color getCouleur(){
        return this.couleur;
    }
}