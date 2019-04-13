package controleur;

import java.util.Random;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import modele.Plateau;
import modele.Joueur;
import vue.VuePlateau;
import vue.VueCase;

/**
 * Controleur du jeu. Contient le Modele et la Vue du jeu.
 * @see modele
 * @see vue
 */
public class Controleur{
    private Plateau modele;
    private VuePlateau vue;
    private int nbl;
    private int nbc;
    private Joueur j1;
    private Joueur j2;

    /**
     * Constructeur du Controleur : crée la Vue à partir du Modele donné en paramètre.
     * @param modele Modele du jeu.
     */
    public Controleur(Plateau modele){
        this.modele = modele;
        this.nbl = this.modele.getNbl();
        this.nbc = this.modele.getNbc();
        this.vue = new VuePlateau(this.modele.getTitre(), this.nbl, this.nbc);
        this.j1 = this.modele.getJ1();
        this.j2 = this.modele.getJ2();
        this.init();
    }

    /**
     * Active les boutons sur la Vue et les relie au Modele.
     */
    private void init(){
        JButton[] boutons = vue.getBoutons();
        for (int button = 0; button < this.nbc; button++){
            final int index = button;
            boutons[button].addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        int l = modele.placerPion(index);
                        //si la colonne index est deja remplie, rien ne se passe (on ne rentre pas dans le if)
                        if(l != -1){
                            vue.changerCouleur(l, index, (modele.estTourDeJ1() ? j1.getCouleur() : j2.getCouleur()));
                            //si la partie est finie (victoire d'un joueur ou grille remplie)
                            if(modele.partieFinie()){
                                if(modele.aGagnant()) vue.partieFinie("Victoire de " + (modele.estTourDeJ1() ? (modele.enModeSuicide() ? j2.getNom() : j1.getNom()) : (modele.enModeSuicide() ? j1.getNom() : j2.getNom())) + " !");
                                else vue.partieFinie("Egalité !");
                            }
                            //si la partie n'est pas finie
                            else{
                                //si la partie est en mode IA, on laisse l'IA jouer après le joueur
                                if(modele.enModeIA()){
                                    int c;
                                    do{
                                        c = new Random().nextInt(nbc);
                                    }while(modele.getCase(0,c).estOccupe());
                                    l = modele.IAplacerPion(c);
                                    vue.changerCouleur(l, c, (modele.estTourDeJ1() ? j1.getCouleur() : j2.getCouleur()));
                                    if(modele.partieFinie()){
                                        if(modele.aGagnant()) vue.partieFinie("Victoire de " + (modele.estTourDeJ1() ? (modele.enModeSuicide() ? j2.getNom() : j1.getNom()) : (modele.enModeSuicide() ? j1.getNom() : j2.getNom())) + " !");
                                        else vue.partieFinie("Egalité !");
                                    }
                                }
                            }
                        }
                    }
                }
            );
        }
    }
}