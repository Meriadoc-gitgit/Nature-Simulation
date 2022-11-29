/**
 * @author Vu Hoang Thuy Duong
 * No.etudiant : 21110221 - TME7
 * 
 * Theme : Natural Simulation
 * created : 20 Nov. 2022
 * 
 * Type : abstract class Stock
 * Obj : Stocker en creant un terrain, des ressources et des animaux
 * 
 */

public abstract class Stock {

    /* Final variables definition */
    public static final int temp_repousse_herbe = -15;
    public static final int temp_repousse_cepe = -5;
    
    /* Variables definition */
    protected Terrain ter;
    protected Ressource[] res;
    protected AgentPred pred = new AgentPred();
    protected AgentProie proie = new AgentProie();

    /* Tableau de Ressource */
    private String[] surround = {"Arbre", "Herbe", "Champignon", "Eau"};

    
    /* Constructor */
    public Stock(int m, int n) {
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

    /* Methods */
    public abstract void rafraichir_proies();
    public abstract void rafraichir_predateur();
    public abstract void rafraichir_monde();
    public abstract void rain();
    public abstract void secheresse();
    public abstract String toString();
}
