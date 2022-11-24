/**
 * @author Vu Hoang Thuy Duong
 * No.etudiant : 21110221 - TME7
 * 
 * Theme : Natural Simulation
 * created : 20 Nov. 2022
 * 
 * Type : interface Action 
 * Obj : stocker toutes les actions pour la simulation definie prochainement
 * 
 */

public interface Action {
    
    /* Pour les 2 types d'Animaux definis prochainement */
    public void seDeplacer(int xnew, int ynew);
    public double distance(int x, int y);
    
    /* Ascenseur */
    public int getX();
    public int getY();
    public double getEnergie();
    public void setEnergie(double e);
    public String toString();
}
