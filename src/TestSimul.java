public class TestSimul {
    public static void main(String[] args) {
        Simulation s1 = new Simulation(15, 1001);
        
        System.out.println(s1.toString());

        s1.rafraichir_proies();
        s1.rafraichir_predateur();
        s1.rafraichir_monde();

        System.out.println(s1.toString());
    }
}
