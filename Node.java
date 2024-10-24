import java.util.*;

public class Node {
    private Node parent;
    private Environment env;
    private int generation;
    private List<Node> children = new ArrayList<>();


    Node(Node parent, Environment env, int generation) {
        this.env = new Environment(env);
        this.generation = generation;
        this.parent = parent;
    }

    Node(Node parent, Environment env, int generation, boolean isGoalReached) {
        this.env = new Environment(env);
        this.generation = generation;
        this.parent = parent;
    }

    public Environment getEnv() {
        return env;
    }

    public List<Node> getPath() {
        List<Node> path = new ArrayList<>();
        Node current = this;
        while (current != null) {
            path.add(0, current); // Add to the beginning of the list
            current = current.parent;
        }
        return path;
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

    public Node getChildren(int index) {
        return children.get(index);
    }

    public int NumberOfChildren() {
        return children.size();
    }

    public void printAllData(){
        System.out.println("Generation: " + generation);
        System.out.println("Parent: " + parent);
        System.out.println("Children: " + children);
    }

    public void traverse() {
        traverse(this, 0);
    }

    private void traverse(Node node, int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println("Generation: " + node.getGeneration() + ", Number of Children: " + node.NumberOfChildren());
        for (Node child : node.getChildren()) {
            traverse(child, depth + 1);
        }
    }
}
