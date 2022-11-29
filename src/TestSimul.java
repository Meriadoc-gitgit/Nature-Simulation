/**
 * @author Vu Hoang Thuy Duong
 * No.etudiant : 21110221 - TME7
 * 
 * Theme : Natural Simulation
 * created : 20 Nov. 2022
 * 
 * Type : class TestSimul
 * Obj : Tester la simulation
 * 
 */

public class TestSimul {
    public static void main(String[] args) {

        /* Simuler */
        Simulation s1 = new Simulation(15, 400);
        System.out.println(s1.toString());

        /* boucle de rafraichissement */
        for (int i=0;i<200;i++) {
            System.out.print("Tour no."+(i+1)+"\n");
            s1.rafraichir_proies();
            s1.rafraichir_predateur();
            s1.rafraichir_monde();

            System.out.println(s1.toString());
            s1.rain();
            s1.secheresse();
        }
    } 
}
