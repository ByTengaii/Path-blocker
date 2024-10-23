import java.util.*;

public class Node {
    private Node parent;
    private Environment env;
    private int generation;
    private List<Node> children = new ArrayList<>();

    Node(Node parent,Environment env, int generation) {
        this.env = env;
        this.generation = generation;
        this.parent = parent;
    }

    public Environment getEnv() {
        return env;
    }

    public Node getParent() {return parent;}

    public int getGeneration() {
        return generation;
    }

    public void addChild(Node child) {
        this.children.add(child);
    }

    public List<Node> getChildren() {
        return children;
    }

    public int NumberOfChildren() {
        return children.size();
    }


}
