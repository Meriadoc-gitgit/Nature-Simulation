/**
 * @author Vu Hoang Thuy Duong
 * No.etudiant : 21110221 - TME7
 * 
 * Theme : Natural Simulation
 * created : 20 Nov. 2022
 * 
 * Type : abstract class Animaux
 * Obj : stocker tous les variables, methods et constructeur necessaires pour l'heritage
 * 
 */

public class Animaux implements Action{
    
    /* Final variables definition */
    public final static double RAND_MAX = 32767;
    public final static double p_reproduce_proie = 0.4;
    public final static double p_reproduce_predateur = 0.5;
    public final static int temp_repousse_herbe = -15;

    /* Protected variables definition */
    protected int x, y;
    protected double energie;
    protected int[] dir = new int[2];

    /* Constructeur to use for inherited class */
    public Animaux(int x, int y, double energie) {
        this.x = x;
        this.y = y;
        this.energie = energie;
        dir[0] = (int)Math.floor(Math.random()*3-1);
        dir[1] = (int)Math.floor(Math.random()*3-1);
    }

    /* Methodes pour tous les 2 types d'Animaux */
    @Override
    public void seDeplacer(int xnew, int ynew) {
        x = xnew;
        y = ynew;
    }
    public double distance(int x, int y) {
        return Math.sqrt((this.x + x)*(this.x + x) + (this.y + y)*(this.y + y));
    }
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
    public String toString() {
        return "Animal : Position : ["+x+","+y+"], Energie : "+energie+", Dir : ["+dir[0]+","+dir[1]+"]";
    }




    public static void main(String[] args) {

        System.out.println(Animaux.temp_repousse_herbe);
    }
}
