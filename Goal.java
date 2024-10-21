public class Goal {
    private int x;
    private int y;
    
    Goal() {
        this.x = 0;
        this.y = 0;
    }

    Goal(int y, int x) {
        this.y = x;
        this.x = y;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }


}
