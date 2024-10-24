import java.util.*;

public class SearchAgent {
    private Set<Node> visitedNodes = new HashSet<>();

    public Node findSolution(Node node) {
        if (visitedNodes.contains(node)) {
            return null; // Node already visited, avoid infinite loop
        }

        visitedNodes.add(node);

        List<Moves> possibleMoves = node.getEnv().getPossibleMoves();
        //System.out.println(possibleMoves);
        for (Moves move : possibleMoves) {
            Environment newEnv = new Environment(node.getEnv()); // Deep copy constructor
            boolean reached = newEnv.action(move);
            Node child = new Node(node, newEnv, node.getGeneration() + 1);
            node.addChild(child);
            if(reached){
                System.out.println("Solution found at generation " + child.getGeneration());
            }
        }

        if (!node.getChildren().isEmpty()) {
            for (Node child : node.getChildren()) {
                findSolution(child); // Recursively build the tree
            }
        }

        return node; // Return the root node after building the tree
    }
}