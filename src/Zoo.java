/**
 * @author Vu Hoang Thuy Duong
 * No.etudiant : 21110221 - TME7
 * 
 * Theme : Natural Simulation
 * created : 20 Nov. 2022
 * 
 * Type : interface Zoo 
 * Obj : Stocker toutes les actions d'un ArrayList d'Animaux pour la simulation prochainement
 * 
 */

public interface Zoo {
    
    /* Methods */
    public void bouger(Terrain t);
    public void reproduce();
    public String toString();
    public boolean animal_en_XY(int x, int y);
    /* Methods animal_en_XY et clone va etre defini dans chanque fichier independant */
}
