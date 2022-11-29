import java.util.ArrayList;

/**
 * @author Vu Hoang Thuy Duong
 * No.etudiant : 21110221 - TME7
 * 
 * Theme : Natural Simulation
 * created : 20 Nov. 2022
 * 
 * Type : public class AgentPred extends ArrayList<Predateur> implements Zoo
 * Obj : Classe definie juste pour simplifier l'initialisation d'un ArrayList AgentProie de Proie
 * 
 */

public class AgentProie extends ArrayList<Proie> implements Zoo {
    
    /* Methods */
    public AgentProie clone() {
        AgentProie tmp = new AgentProie();
        for (Proie p : this) 
            tmp.add(p.clone());
        return tmp;
    }

    @Override
    public void bouger(Terrain t) {
        for (Proie proie : this) {
            proie.seDeplacer(
                (int)Math.floor(Math.random()*t.nbLignes), 
                (int)Math.floor(Math.random()*t.nbColonnes));
        }
    }
    public void reproduce() {
        AgentProie tmp = this.clone();
        for (Proie proie : tmp) {
            if (Math.floor(Math.random()*35000+32000)/Animaux.RAND_MAX < Animaux.p_reproduce_predateur) {
                
                this.add(new Proie(
                    proie.getX(), 
                    proie.getY()));

                proie.setEnergie(proie.getEnergie()/2);
            }
        }
    }
    public boolean animal_en_XY(int x, int y) {
        for (Proie proie : this) {
            if (proie.getX()==x && proie.getY()==y) 
                return true;
        }
        return false;
    }
    public String toString() {
        String s = "";
        for (Proie proie : this) {
            s += proie.toString()+"\n";
        }
        return s;
    }
}
