/**
 * @author Vu Hoang Thuy Duong
 * No.etudiant : 21110221 - TME7
 * 
 * Theme : Natural Simulation
 * created : 20 Nov. 2022
 * 
 * Type : abstract class Animaux
 * Obj : Stocker tous les variables, methods et constructeur necessaires pour l'heritage - Predateur et Proie
 * 
 */

public class Animaux implements Action {
    
    /* Final variables definition */
    public final static int RAND_MAX = 32767;
    public final static double p_reproduce_predateur = .5;
    public final static double p_reproduce_proie = .4;

    /* Protected variables definition
     * Seulement les classes filles peuvent y acceder
     */
    protected int x, y; /* Position de l'animal */
    protected double energie; /* Energie de l'animal */

    /* Constructeur (a utiliser pour les classes filles) */
    public Animaux(int x, int y, double energie) {
        this.x = x;
        this.y = y;
        this.energie = energie;
    }

    /* Methodes utiles pour la simulation des animaux */
    @Override
    public void seDeplacer(int xnew, int ynew) {
        x = xnew;
        y = ynew;
    }
    public double distance(int x, int y) {
        return Math.sqrt((this.x - x)*(this.x - x) + (this.y - y)*(this.y - y));
    }
    public double distance(Animaux a) {
        return this.distance(a.getX(), a.getY());
    }
    
    /* Ascenseur */
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public double getEnergie() {
        return energie;
    }
    public void setEnergie(double e) {
        energie = e;
    }
}
