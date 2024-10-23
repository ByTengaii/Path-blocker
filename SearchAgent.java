import java.util.List;

public class SearchAgent {
    
    public Node findSolution (Node node) {
        List<Moves> possibleMoves = node.getEnv().getPosssibleMoves();

        for (Moves move : possibleMoves) {
            Environment newEnv = new Environment(node.getEnv());//copy the environment
            boolean reached = newEnv.action(move);
            if (reached) {
                System.out.println("Solution found");
                //TODO: print the solution
                return node;
            }
            Node child = new Node(node,newEnv, node.getGeneration() + 1);
            node.addChild(child);
        }
        System.out.println("Number of childrens:"+ node.NumberOfChildren());
        for (Node child : node.getChildren()) {
            Node solution = findSolution(child);
            if (solution != null) {
                return solution;
            }

        }

        System.out.println("Return null");
        return null;
    }
}
