/**
 * @author Vu Hoang Thuy Duong
 * No.etudiant : 21110221 - TME7
 * 
 * Theme : Natural Simulation
 * created : 20 Nov. 2022
 * 
 * Type : class Simulation 
 * Obj : Classe definir avec plusieurs nouvelles methodes pour la simulation de l'environnement
 * 
 */

public class Simulation {
    
    /* Variables definition */
    private Terrain ter;
    private Ressource[] res;
    private AgentPred pred;
    private AgentProie proie;

    /* Tableau de Ressources */
    private String[] tabRes = {"Arbre", "Herbe", "Champignon", "Etang"};

    /* Constructeur */
    public Simulation(int m, int n) {
        /* 
         * class Simulation va simuler un environnement avec :
         * Terrain ter de dimension NBLIGNESMAX * NBCOLONNESMAX
         * m ressources
         * n agents
         */
        this.ter = new Terrain();

        /* Initialisation de m ressources */
        this.res = new Ressource[m];
        for (int i=0;i<res.length;i++) {
            res[i] = new Ressource(tabRes[(int)Math.floor(Math.random()%tabRes.length)], (int)Math.floor(Math.random()%100));

            ter.setCase((int)Math.floor(Math.random()%ter.nbLignes), (int)Math.floor(Math.random()%ter.nbColonnes), res[i]);
        }

        /* Initialisation de n agents */
        /* Regle : 
         * Si n%2==0 alors nbPredateur > nbProie
         * Sinon alors nbPredateur == nbProie
         */
        pred = new AgentPred();
        proie = new AgentProie();
        if (n%2 == 0) {
            for (int i=0;i<n/2;i++) 
                pred.add(new Predateur((int)Math.floor(Math.random()%ter.nbLignes), (int)Math.floor(Math.random()%ter.nbColonnes)));
            for (int i=0;i<n-n/2;i++) 
                proie.add(new Proie((int)Math.floor(Math.random()%ter.nbLignes), (int)Math.floor(Math.random()%ter.nbColonnes)));
        } 
        else {
            for (int i=0;i<n/2;i++) {
                pred.add(new Predateur((int)Math.floor(Math.random()%ter.nbLignes), (int)Math.floor(Math.random()%ter.nbColonnes)));
                proie.add(new Proie((int)Math.floor(Math.random()%ter.nbLignes), (int)Math.floor(Math.random()%ter.nbColonnes)));
            }
        }
    }

    /* Methodes */
    public void rafraichir_proies() {
        proie.bouger(ter);

        for (int i=0;i<ter.nbLignes;i++) {
            for (int j=0;j<ter.nbColonnes;j++) {
                if (!ter.caseEstVide(i,j) && ter.getCase(i,j).getQuantite()>0) {
                    proie.get(i).setEnergie(proie.get(i).getEnergie() + ter.getCase(i,j).getQuantite());
                    ter.getCase(i,j).setQuantite(Animaux.temp_repousse_herbe);
                }
                else proie.get(i).setEnergie(proie.get(i).getEnergie()-1);;

                if (proie.get(i).getEnergie()<0) 
                    proie.remove(proie.get(i));
            }
        }
        proie.reproduce(Animaux.p_reproduce_proie);
    }
    public void rafraichir_predateur() {
        pred.bouger(ter);
        for (int i=0;i<pred.size();i++) {
            if (proie.animal_en_XY(pred.get(i).getX(), pred.get(i).getY())!=null) {
                for (int j=0;j<proie.size();j++) {
                    if (proie.get(j).getX()==pred.get(i).getX() && proie.get(j).getY()==pred.get(i).getY()) {
                        pred.get(i).setEnergie(proie.get(j).getEnergie()+pred.get(i).getEnergie());
                        proie.remove(proie.get(j));
                    }
                }
            }
            pred.get(i).setEnergie(pred.get(i).getEnergie()-1);
        }
        pred.reproduce(Animaux.p_reproduce_predateur);
    }
}
