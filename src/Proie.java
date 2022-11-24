/**
 * @author Vu Hoang Thuy Duong
 * No.etudiant : 21110221 - TME7
 * 
 * Theme : Natural Simulation
 * created : 20 Nov. 2022
 * 
 * Type : abstract class Predateur extends Animaux
 * Obj : stocker tous les variables, methods et constructeur necessaires pour l'heritage de Carnivore
 * 
 */

public class Proie extends Animaux {

    /* Variables definition */
    private static int cpt = 0;
    private String id = "";

    /* Constructeur */
    public Proie(int x, int y, double energie) {
        super(x, y, energie);
        id += cpt;
        cpt++;
    }
    public Proie(int x, int y) {
        super(x, y, (int)Math.floor(Math.random()*101));
        id += cpt;
        cpt++;
    }

    /* Methods */
    public static int getCpt() {
        return cpt;
    }
    public String toString() {
        return "Proie no."+cpt+" Position : ["+x+","+y+"], Energie : "+energie+", Dir : ["+dir[0]+","+dir[1]+"]";
    }
}
