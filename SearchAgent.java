import java.util.List;

public class SearchAgent {
    
    public List<Node> findSolution(Node node) {
        List<Moves> possibleMoves = node.getEnv().getPossibleMoves();
        
        for (Moves move : possibleMoves) {
            Environment newEnv = new Environment(node.getEnv()); // Deep copy constructor
            boolean reached = newEnv.action(move);
            Node child = new Node(node, newEnv, node.getGeneration() + 1);
            node.addChild(child);

            if (reached) {
                System.out.println("Solution found at generation " + child.getGeneration());
                return child.getPath(); // Return the path to the solution
            }
        }

        for (Node child : node.getChildren()) {
            List<Node> path = findSolution(child); // Recursively build the tree
            if (path != null) {
                return path; // Return the path if a solution is found
            }
        }

        return null; // Return null if no solution is found
    }


}