/**
 * @author Vu Hoang Thuy Duong
 * No.etudiant : 21110221 - TME7
 * 
 * Theme : Natural Simulation
 * created : 20 Nov. 2022
 * 
 * Type : class Proie extends Animaux
 * Obj : Stocker tous les variables, methods et constructeur necessaires pour les Herbivore
 * 
 */

public class Proie extends Animaux {
    
    /* Variables definition */
    private static int cpt = 0;
    public final int id;

    /* Constructeur */
    public Proie(int x, int y, double e) {
        super(x, y, e);
        cpt++;
        id = cpt;
    }
    public Proie(int x, int y) {
        super(x, y, Math.floor(Math.random()*100));
        cpt++;
        id = cpt;
    }

    /* Methods */
    public String toString() {
        return "Herbivore no."+cpt+" Position : ["+x+","+y+"] Energie : "+energie;
    }
    public Proie clone() {
        return new Proie(this.x, this.y, this.energie);
    }

    /* Ascenseur */
    public static int getCpt() {
        return cpt;
    }
}
