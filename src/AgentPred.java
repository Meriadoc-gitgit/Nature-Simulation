import java.util.ArrayList;

/**
 * @author Vu Hoang Thuy Duong
 * No.etudiant : 21110221 - TME7
 * 
 * Theme : Natural Simulation
 * created : 20 Nov. 2022
 * 
 * Type : public class AgentPred extends ArrayList<Predateur> implements Zoo
 * Obj : Classe definie juste pour simplifier l'initialisation d'un ArrayList AgentPred de Predateur
 * 
 */

public class AgentPred extends ArrayList<Predateur> implements Zoo {
    
    /* Methods */
    public AgentPred clone() {
        AgentPred tmp = new AgentPred();
        for (Predateur p : this) 
            tmp.add(p.clone());
        return tmp;
    }

    @Override
    public void bouger(Terrain t) {
        for (Predateur pred : this) {
            pred.seDeplacer(
                (int)Math.floor(Math.random()*t.nbLignes), 
                (int)Math.floor(Math.random()*t.nbColonnes));
        }
    }
    public void reproduce() {
        AgentPred tmp = this.clone();
        for (Predateur pred : tmp) {
            if (Math.floor(Math.random()*35000+32000)/Animaux.RAND_MAX < Animaux.p_reproduce_predateur) {
                
                this.add(new Predateur(
                    pred.getX(), 
                    pred.getY()));

                pred.setEnergie(pred.getEnergie()/2);
            }
        }
    }
    public boolean animal_en_XY(int x, int y) {
        for (Predateur pred : this) {
            if (pred.getX()==x && pred.getY()==y) 
                return true;
        }
        return false;
    }
    public String toString() {
        String s = "";
        for (Predateur pred : this) {
            s += pred.toString()+"\n";
        }
        return s;
    }
}
