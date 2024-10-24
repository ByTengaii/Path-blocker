import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Environment env = new Environment(8);
        SearchAgent agent = new SearchAgent();
        Node root = new Node(null, env, 0);
        
        agent.findSolution(root);
        //System.out.println("Root Child number : " + solutionTree.getChildren().size());
        //System.out.println("Root Map:");
        //solutionTree.getChildren(1).getEnv().printMap();
    }
}
