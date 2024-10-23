import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Environment env = new Environment(2);
        SearchAgent agent = new SearchAgent();
        //for (int i = 1 ; i <= 10 ; i++  ){
            System.out.println("ENV Possible moves: " + env.getPosssibleMoves());
            Node root = new Node(null,env, 0);
            agent.findSolution(root);
            System.out.println("Root Child number : " + root.NumberOfChildren());
            System.out.println("Root Possible moves: " + root.getEnv().getPosssibleMoves());

        //}
    }
}
