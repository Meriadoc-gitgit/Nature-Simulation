public class TestSimul {
    public static void main(String[] args) {

        Simulation s1 = new Simulation(100, 100);
        AgentPred pred1 = new AgentPred();
        
        for (int i=0;i<30;i++) {
            pred1.add(new Predateur(i, i));
        }
        System.out.println(pred1.toString());
    }
}
