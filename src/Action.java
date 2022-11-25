/**
 * @author Vu Hoang Thuy Duong
 * No.etudiant : 21110221 - TME7
 * 
 * Theme : Natural Simulation
 * created : 20 Nov. 2022
 * 
 * Type : interface Action 
 * Obj : Stocker toutes les actions des Animaux pour la simulation definie prochainement
 * 
 */

public interface Action {

    /* Methodes necessaires pour les 2 types d'Animaux definis prochainement */
    public void seDeplacer(int xnew, int ynew);
    public double distance(int x, int y);
    public double distance(Animaux a); /* Surcharge, pour simplifier les calculs */
    public String toString();

    /* Ascenseur */
    public int getX();
    public int getY();
    public double getEnergie();
    public void setEnergie(double e);
}
