import java.io.File;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        SearchAgent agent = new SearchAgent();

        for (int i = 1; i <= 10; i++) {
            Environment env = new Environment(i);
            Node root = new Node(null, env, 0);
            
            List<Node> solutionPath = agent.findSolution(root);
            
            if (solutionPath != null) {
                String folderPath = String.format("./level%02d/", i);
                File levelFolder = new File(folderPath);
                if (!levelFolder.exists()) {
                    levelFolder.mkdirs();
                }

                System.out.println("Solution path of level: " + i);
                int stepNumber = 1;
                for (Node node : solutionPath) {
                    System.out.println("Generation: " + node.getGeneration());
                    node.getEnv().printMap();
                    
                    node.getEnv().saveImage(folderPath, stepNumber);
                    stepNumber++;
                }
            } else {
                System.out.println("No solution found for level: " + i);
            }
        }
    }
}
