/**
 * @author Vu Hoang Thuy Duong
 * No.etudiant : 21110221 - TME7
 * 
 * Theme : Natural Simulation
 * created : 20 Nov. 2022
 * 
 * Type : class Agent, extends Animaux 
 * Obj : Classe definie juste pour simplifier l'initialisation d'un ArrayList AgentPred de Predateur
 * 
 */
import java.util.ArrayList;

public class AgentPred extends ArrayList<Predateur> { 
    /* Constructeur sans argument */

    /* Methods */
    public void bouger(Terrain t) {
        for (int i=0;i<this.size();i++) {
            this.get(i).seDeplacer((int)Math.floor(Math.random()%t.nbLignes), (int)Math.floor(Math.random()%t.nbColonnes));
        }
    }
    public void reproduce(double p_reproduce) {
        for (int i=0;i<this.size();i++) {
            if (Math.floor(Math.random())/Animaux.RAND_MAX < p_reproduce) {
                this.add(new Predateur(this.get(i).getX(), this.get(i).getY(), this.get(i).getEnergie()/2));
                this.get(i).setEnergie(this.get(i).getX()/2);
            }
            else continue;
        }
    }
    public Predateur animal_en_XY(int x, int y) {
        for (int i=0;i<this.size();i++) {
            if (this.get(i).getX()==x && this.get(i).getY()==y)
                return this.get(i);
        }
        return null;
    }
    public String toString() {
        String s = "";
        for (Predateur p : this) 
            s+=p.toString()+"\n";
        return s;
    }
}
