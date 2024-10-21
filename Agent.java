public class Agent {
    private int x;
    private int y;
    private int score;
    
    Agent() {
        this.x = 0;
        this.y = 0;
        this.score = 0;
    }

    Agent(int y, int x) {
        this.y = y;
        this.x = x;
        this.score = 0;
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
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public int getScore() {
        return this.score;
    }


}
