public class Node {
    private int x;
    private int y;
    private int score;
    private Node parent;
    
    Node() {
        this.x = 0;
        this.y = 0;
        this.score = 0;
        this.parent = null;
    }

    Node(int y, int x) {
        this.y = y;
        this.x = x;
        this.score = 0;
        this.parent = null;
    }

    Node(int y, int x, Node parent) {
        this.y = y;
        this.x = x;
        this.score = 0;
        this.parent = parent;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public void setParent(Node parent) {
        this.parent = parent;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public int getScore() {
        return this.score;
    }
    public Node getParent() {
        return this.parent;
    }
}
