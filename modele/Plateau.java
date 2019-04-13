package modele;
import java.awt.Color;

/**
 * Classe Plateau représenté la grille de jeu.
 * @author Joachim Jaafar
 */
public class Plateau{
    /**
     * Cases du Plateau
     */
    private Case[][] cases;
    /**
     * Titre de la partie
     */
    private String titre;
    /**
     * Nombre de lignes dans la grille.
     */
    private int nbl;
    /**
     * Nombre de colonnes dans la grille.
     */
    private int nbc;
    /**
     * Nombre de pions à aligner pour finir la partie.
     */
    private int nbPions;
    /**
     * Premier joueur de la partie.
     */
    private Joueur j1;
    /**
     * Deuxième joueur de la partie.
     */
    private Joueur j2;
    /**
     * Indique si c'est le tour de j1.
     */
    private boolean tourDej1 = false;
    /**
     * Indique si la partie est en mode Suicide.
     */
    private boolean modesuicide;
    /**
     * Indique si la partie est en mode contre l'IA.
     */
    private boolean modeia;

    /**
     * Constructeur du Plateau
     * @param titre Titre de la partie.
     * @param nbl Nombre de lignes.
     * @param nbc Nombre de colonnes.
     * @param j1 Joueur 1.
     * @param j2 Joueur 2.
     * @param nbPions Nombre de pions pour finir.
     * @param modesuicide Si la partie est en mode Suicide.
     * @param modeia Si la partie est en mode contre l'IA.
     */
    public Plateau(String titre, int nbl, int nbc, Joueur j1, Joueur j2, int nbPions, boolean modesuicide, boolean modeia){
        this.cases = new Case[nbl][nbc];
        this.titre = titre;
        this.nbl = nbl;
        this.nbc = nbc;
        this.j1 = j1;
        this.j2 = j2;
        this.nbPions = nbPions;
        this.modesuicide = modesuicide;
        this.modeia = modeia;
        this.init();
    }

    /**
     * Initialise les cases du Plateau (les met en blanc).
     */
    public void init(){
        for(int i=0; i<this.nbl; i++){
            for(int j=0; j<this.nbc; j++){
                this.cases[i][j] = new Case(Color.WHITE);
            }
        }
    }

    /**
     * @return Le titre de la partie.
     */
    public String getTitre(){
        return this.titre;
    }

    /**
     * @return Le nombre de lignes de la grille.
     */
    public int getNbl(){
        return this.nbl;
    }

    /**
     * @return Le nombre de lignes de la grille.
     */
    public int getNbc(){
        return this.nbc;
    }

    /**
     * @return La case a la position (x,y).
     */
    public Case getCase(int x, int y){
        return this.cases[x][y];
    }

    /**
     * @return Le nombre de pions pour finir.
     */
    public int getNbPions(){
        return this.nbPions;
    }

    /**
     * @return Si la partie est en mode Suicide.
     */
    public boolean enModeSuicide(){
        return this.modesuicide;
    }

    /**
     * @return Si la partie est en mode contre l'IA.
     */
    public boolean enModeIA(){
        return this.modeia;
    }

    /**
     * @return Le Joueur 1.
     * @see Joueur
     */
    public Joueur getJ1(){
        return this.j1;
    }

    /**
     * @return Le Joueur 2.
     * @see Joueur
     */
    public Joueur getJ2(){
        return this.j2;
    }

    /**
     * @return Si c'est au tour de j1 de jouer.
     */
    public boolean estTourDeJ1(){
        return this.tourDej1;
    }

    /**
     * @return Si la case à la position (x,y) existe.
     */
    private boolean existe(int x, int y){
        return 0 <= x && x < this.nbl && 0 <= y && y < this.nbc;
    }

    /**
     * @return Si les cases sont occupées par le même joueur (donc de la même couleur).
     */
    private boolean memeCouleur(int a, int b, int x, int y){
        return this.existe(a,b) && this.existe(x, y) && !this.cases[a][b].getCouleur().equals(Color.WHITE) && this.cases[a][b].getCouleur().equals(this.cases[x][y].getCouleur());
    }

    /**
     * Place un pion dans la colonne c et retourne sa ligne.
     * @param c Colonne où l'on veut placer un pion.
     * @return Ligne du pion.
     */
    public int placerPion(int c){
        int l = this.nbl - 1;
        while(l >= 0){
            if(!cases[l][c].estOccupe()){
                break;
            }
            l--;
        }
        if(l < 0){
            return -1;
        }
        if(this.tourDej1) this.cases[l][c].devientOccupe(this.j1);
        else this.cases[l][c].devientOccupe(this.j2);
        this.tourDej1 = !this.tourDej1;
        return l;
    }

    /**
     * L'IA place un pion dans la colonne c et retourne sa ligne.
     * @param c Colonne où l'IA veut placer un pion.
     * @return Ligne du pion.
     */
    public int IAplacerPion(int c){
        int l = this.nbl - 1;
        while(l >= 0){
            if(!cases[l][c].estOccupe()){
                break;
            }
            l--;
        }
        if(this.tourDej1) this.cases[l][c].devientOccupe(this.j1);
        else this.cases[l][c].devientOccupe(this.j2);
        this.tourDej1 = !this.tourDej1;
        return l;
    }

    /**
     * @return Si toute la grille est remplie.
     */
    public boolean estRempli(){
        for(int c=0; c<this.nbc; c++){
            if(!cases[0][c].estOccupe()) return false; 
        }
        return true;
    }

    /**
     * @return Si la partie est finie (si un joueur a gagné ou si la grille est remplie).
     */
    public boolean partieFinie(){
        return this.aGagnant() || this.estRempli();
    }

    /**
     * @return Si un joueur a gagné.
     */
    public boolean aGagnant(){
        for(int i=0; i<this.nbl; i++){
            for(int j=0; j<this.nbc; j++){
                if(this.checkPion(i,j)) return true;
            }
        }
        return false;
    }

    /**
     * @return Si le pion placé en (l,c) fait gagner un joueur.
     */
    public boolean checkPion(int l, int c){
        return this.checkHorizontal(l,c) || this.checkVertical(l,c) || this.checkDiagonal(l,c);
    }

    /**
     * @return Si le pion placé en (l,c) fait gagner un joueur horizontalement.
     */
    private boolean checkHorizontal(int l, int c){
        int p = 0;
        for(int i = 0; i < this.nbc; i++){
            if(this.memeCouleur(l,i,l,c)){
                p++;
                if(p == this.nbPions){
                    return true;
                }
            }
            else p = 0;
        }
        return false;
    }

    /**
     * @return Si le pion placé en (l,c) fait gagner un joueur verticalement.
     */
    public boolean checkVertical(int l, int c){
        int p = 0;
        for(int i = 0; i < this.nbl; i++){
            if(this.memeCouleur(i,c,l,c)){
                p++;
                if(p == this.nbPions) return true;
            }
            else p = 0;
        }
        return false;
    }

    /**
     * @return Si le pion placé en (l,c) fait gagner un joueur en diagonale.
     */
    private boolean checkDiagonal(int l, int c){
        int p = 0;
        int xtmp = l, ytmp = c;
        while(xtmp != 0 && ytmp != 0){
            xtmp--;
            ytmp--;
        }
        while(xtmp != this.nbl && ytmp != this.nbc){
            if(this.memeCouleur(xtmp,ytmp,l,c)){
                p++;
                if(p == this.nbPions) return true;
            }
            else p = 0;
            xtmp++;
            ytmp++;
        }
        p = 0;
        xtmp = l;
        ytmp = c;
        while(xtmp != 0 && ytmp != this.nbc-1){
            xtmp--;
            ytmp++;
        }
        while(xtmp != this.nbl && ytmp >= 0){
            if(this.memeCouleur(xtmp,ytmp,l,c)){
                p++;
                if(p == this.nbPions) return true;
            }
            else p = 0;
            xtmp++;
            ytmp--;
        }
        return false;
    }
}