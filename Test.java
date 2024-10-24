import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        SearchAgent agent = new SearchAgent();

        for (int i = 1; i <= 10; i++) {
            Environment env = new Environment(i);
            Node root = new Node(null, env, 0);
            
            List<Node> solutionPath = agent.findSolution(root);
    
            
            if (solutionPath != null) {
                System.out.println("Solution path of level: $" + i);
                for (Node node : solutionPath) {
                    System.out.println("Generation: " + node.getGeneration());
                    node.getEnv().printMap();
                }
            } else {
                System.out.println("No solution found.");
            }
        }
    }
}
