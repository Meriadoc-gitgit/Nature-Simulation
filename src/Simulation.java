/**
 * @author Vu Hoang Thuy Duong
 * No.etudiant : 21110221 - TME7
 * 
 * Theme : Natural Simulation
 * created : 20 Nov. 2022
 * 
 * Type : class Simulation
 * Obj : Simuler l'environnement
 * 
 */

public class Simulation {

    /* Final variables definition */
    public static final int temp_repousse_herbe = -15;
    public static final int temp_repousse_cepe = -5;
    
    /* Variables definition */
    private Terrain ter;
    private Ressource[] res;
    private AgentPred pred = new AgentPred();
    private AgentProie proie = new AgentProie();

    /* Tableau de Ressource */
    private String[] surround = {"Arbre", "Herbe", "Champignon", "Eau"};

    /* Constructeur */
    public Simulation(int m, int n) {
        /* 
         * Class Simulation va simuler un environnement avec :
         * Terrain ter de dimension NBLIGNESMAX * NBCOLONNESMAX
         * m ressources
         * n agents
         */
        this.ter = new Terrain();

        /* Initialisation de m ressources */
        this.res = new Ressource[m];
        for (int i=0;i<res.length;i++) {
            res[i] = new Ressource (
                surround[(int)Math.floor(Math.random()*surround.length)], 
                (int)Math.floor(Math.random()*100)   
            );

            ter.setCase(
                (int)Math.floor(Math.random()*ter.nbLignes),
                (int)Math.floor(Math.random()*ter.nbColonnes),
                res[i]
            );
        }

        /* Initialisation de n agents */
        /* Regle : 
         * Si n%2==0 alors nbPredateur > nbProie
         * Sinon alors nbPredateur == nbProie
         */
        int len = n;
        if (n%2==0) len/=2;
        else len = len/2 + 1;
        for(int i=0;i<(int)len;i++) {
            proie.add(new Proie(
                (int)Math.floor(Math.random()*ter.nbLignes),
                (int)Math.floor(Math.random()*ter.nbColonnes))
            );
            
            if (proie.size()+pred.size() == n) break;

            pred.add(new Predateur(
                (int)Math.floor(Math.random()*ter.nbLignes),
                (int)Math.floor(Math.random()*ter.nbColonnes))
            );
        }
    }

    /* Methods (pour les animaux) */
    public void rafraichir_proies() {

        /* Etape 1 : bouger les proies */
        proie.bouger(ter);

        /* Etape 2 : Parcourir */
        for (Proie p : proie) {
            if (!ter.caseEstVide(p.x,p.y) && ter.getCase(p.x,p.y).getQuantite()>0 && ter.getCase(p.x,p.y).type=="Herbe") {
                p.setEnergie(p.energie + ter.getCase(p.x,p.y).getQuantite());

                ter.getCase(p.x,p.y).setQuantite(Simulation.temp_repousse_herbe);
            }
            else if (ter.getCase(p.x,p.y)!=null) {
                if (ter.getCase(p.x,p.y).type=="Eau") {
                    p.setEnergie(p.energie+1);
                    ter.getCase(p.x,p.y).setQuantite(
                        ter.getCase(p.x,p.y).getQuantite()-1
                    );
                }
                else continue;
            }

            else p.setEnergie(p.energie-1);

            /* Au cas ou l'energie est negative */
            if (ter.getCase(p.x,p.y)!=null) {
                if (p.energie<0 || ter.getCase(p.x,p.y).type=="Champignon") {
                    ter.getCase(p.x,p.y).setQuantite(Simulation.temp_repousse_cepe);
                    proie.remove(p);
                    Proie.reduceAnimaux();
                    System.out.println(p.toString()+" est mort de champignon!");
                }
                else continue;
            }
        }

        /* Etape 3 : Reproduction */
        proie.reproduce();
    }
    public void rafraichir_predateur() {

        /* Etape 1 : bouger les predateurs */
        pred.bouger(ter);

        /* Etape 2 : Parcourir */
        for (Predateur p : pred) {
            if (proie.animal_en_XY(p.x, p.y)) {
                
                /* Energie de Proie */
                for (Proie tmp : proie) {
                    if (p.x==tmp.x && p.y==tmp.y) {
                        p.setEnergie(p.energie + tmp.energie);
                        proie.remove(tmp);
                        Proie.reduceAnimaux();
                        break;
                    }
                }
            }
            else if (ter.getCase(p.x,p.y)!=null) {
                if (ter.getCase(p.x,p.y).type=="Eau" && ter.getCase(p.x,p.y)!=null) {
                    p.setEnergie(p.energie+1);
                    ter.getCase(p.x,p.y).setQuantite(
                        ter.getCase(p.x,p.y).getQuantite()-1
                    );
                }
                else continue;
            }
            
            else p.setEnergie(p.energie-1);

            /* Au cas ou l'energie est negative */
            if (ter.getCase(p.x,p.y)!=null) {
                if (p.energie<0 || ter.getCase(p.x,p.y).type=="Champignon" && ter.getCase(p.x,p.y)!=null) {
                    ter.getCase(p.x,p.y).setQuantite(Simulation.temp_repousse_cepe);
                    pred.remove(p);
                    Predateur.reduceAnimaux();
                    System.out.println(p.toString()+" est mort de champignon!");
                }
                else continue;
            }
        }

        /* Etape 3 : reproduce les predateurs */
        pred.reproduce();
    }

    /* Methods (pour les ressources) */
    public void rafraichir_monde() {
        for (int i=0;i<ter.nbLignes;i++) {
            for (int j=0;i<ter.nbColonnes;i++) {
                if (ter.getCase(i,j)!=null) {
                    if (ter.getCase(i,j).type == "Herbe" || ter.getCase(i,j).type == "Champignon") {
                        ter.getCase(i,j).setQuantite(
                            ter.getCase(i,j).getQuantite()+1
                        );
                    }
                    else continue;
                }
                else continue;
            }
        }
    }

    /* Methods (pour la meteo) */
    public void rain() {
        for (int i=0;i<ter.nbLignes;i++) {
            for (int j=0;j<ter.nbColonnes;j++) {
                if (ter.getCase(i,j).type == "Herbe" || ter.getCase(i,j).type == "Champignon" || ter.getCase(i,j).type == "Eau")
                ter.getCase(i,j).setQuantite(
                    ter.getCase(i,j).getQuantite()*2
                );
            }
        }
    }
    public void secheresse() {
        for (int i=0;i<ter.nbLignes;i++) {
            for (int j=0;j<ter.nbColonnes;j++) {
                if (ter.getCase(i,j).type == "Herbe" || ter.getCase(i,j).type == "Champignon" || ter.getCase(i,j).type == "Eau")
                ter.getCase(i,j).setQuantite(
                    ter.getCase(i,j).getQuantite()/2
                );
            }
        }
    }
    /* AFFICHAGE DE SIMULATION */
    public String toString() {
        String s = "";
        
        /* First line */
        s += "|";
        for (int j=0;j<ter.nbColonnes;j++) 
            s += "---";
        s += "|\n";
        
        /* Content */
        for (int i=0;i<ter.nbLignes;i++) {
            s += "|";
            for (int j=0;j<ter.nbColonnes;j++) {

                /* Afficher les ressources */
                if (pred.animal_en_XY(i,j) || proie.animal_en_XY(i,j)) {
                    /* Afficher les agents */
                    if (pred.animal_en_XY(i,j) && proie.animal_en_XY(i,j))
                        s += " @ "; 
                    else if (pred.animal_en_XY(i,j) && !proie.animal_en_XY(i,j)) 
                        s += " O ";
                    else if (!pred.animal_en_XY(i,j) && proie.animal_en_XY(i,j)) 
                        s += " * ";
                    else s += "   ";
                }
                else {
                    /* Afficher les ressources */
                    if (ter.getCase(i,j) != null) {
                        if (ter.getCase(i,j).type == "Herbe") 
                            s += "###";
                        else if (ter.getCase(i,j).type == "Champignon") 
                            s += "@@@";
                        else if (ter.getCase(i,j).type == "Eau") 
                            s += "~~~";
                        else if (ter.getCase(i,j).type == "Arbre") 
                            s += "&&&";
                    }
                    else s += "   ";
                }
            }
            s += "|\n";
        }

        /* Last line */
        s += "|";
        for (int j=0;j<ter.nbColonnes;j++) 
            s += "---";
        s += "|\n\n";

        /* Compteur d'animaux */
        s += "Nombre de Proie : "+Proie.getCpt()+"\n";
        s += "Nombre de Predateur : "+Predateur.getCpt()+"\n\n";

        /* Notation pour les signes */
        s += "@ : Predateur + Proie\n";
        s += "* : Proie\n";
        s += "O : Predateur\n";
        s += "### : Herbe\n";
        s += "@@@ : Champignon (deadly)\n";
        s += "~~~ : Eau\n";
        s += "&&& : Arbre\n";

        return s;
    }
}




/* ============== FIN =============== */
