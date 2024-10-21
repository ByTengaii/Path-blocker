import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.*;

public class Environment {

    private int width = 0;
    private int height = 0;
    private char[][] level;
    private Agent agent;
    private Goal goal;
    private int currentLevel;

    Environment() {
        // TODO: Implement constructor for level 1
        this.currentLevel = 31;
        updateWorld(this.currentLevel);
    }

    Environment(Agent agent) {
        // TODO: Implement constructor for level 1
        this.agent = agent;
        this.currentLevel = 31;
        updateWorld(this.currentLevel);
    }

    public void updateWorld(int mapLevel) {
        // TODO: Implement createWorld
        String filePath = "./levels/level" + mapLevel + ".txt"; // Path to your file
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            this.height = lines.size();
            this.width = lines.get(0).length();
            this.level = new char[height][width];

            for (int i = 0; i < lines.size(); i++) {
                char[] row = lines.get(i).toCharArray();
                this.level[i] = row;
            }
            System.out.println("The level has been updated:");
            printMap();
            initAgentAndGoal();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initAgentAndGoal() {
        boolean agentFound = false;
        boolean goalFound = false;
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (this.level[i][j] == 'x') {
                    this.agent = new Agent(i, j);
                    agentFound = true;
                } else if (this.level[i][j] == 'y') {
                    this.goal = new Goal(i, j);
                    goalFound = true;
                }
            }
        }
        if (agentFound && goalFound) {
            System.out
                    .println("Agent has been initialized at position: " + this.agent.getX() + ", " + this.agent.getY());
            System.out.println("Goal has been initialized at position: " + this.goal.getX() + ", " + this.goal.getY());
        } else if (!agentFound) {
            System.err.println("Agent not found in the level");
            printMap();
            System.exit(1);
        } else {
            System.err.println("Goal not found in the level");
            printMap();
            System.exit(1);
        }
    }

    public Moves action(Moves move) {
        if (this.level == null) {
            System.out.println("The level has not been initialized");
            return null;
        }
        int startRow = this.agent.getY();
        int startColumn = this.agent.getX();

        switch (move) {
            case UP:
                // System.out.println("Before loop, agent x: " + this.agent.getX() + " agent y:
                // " + this.agent.getY());
                for (int cursor = startRow - 1; cursor >= 0; cursor--) {
                    // System.out.println("Loop triggered");
                    if (this.level[cursor][startColumn] == '1') {
                        // System.out.println("agent found 1");
                        this.level[cursor][startColumn] = 'x';
                        this.level[this.agent.getY()][startColumn] = '0';
                        this.agent.setY(cursor);
                    } else if (this.level[cursor][startColumn] == 'y') {
                        System.out.println("Goal reached");
                        this.agent.setY(cursor);
                        break;
                    } else if (this.level[cursor][startColumn] == '0') {
                        System.out.println("Wall found");
                        // TODO: Implement wall found
                        break;
                    } else {
                        System.err.println("Invalid character in map");
                        printMap();
                        return null;
                    }
                }
            case DOWN:
            // System.out.println("Before loop, agent x: " + this.agent.getX() + " agent y:
                // " + this.agent.getY());
                for (int cursor = startRow + 1; cursor < this.height; cursor++) {
                    // System.out.println("Loop triggered");
                    if (this.level[cursor][startColumn] == '1') {
                        // System.out.println("agent found 1");
                        this.level[cursor][startColumn] = 'x';
                        this.level[this.agent.getY()][startColumn] = '0';
                        this.agent.setY(cursor);
                    } else if (this.level[cursor][startColumn] == 'y') {
                        System.out.println("Goal reached");
                        this.agent.setY(cursor);
                        break;
                    } else if (this.level[cursor][startColumn] == '0') {
                        System.out.println("Wall found");
                        // TODO: Implement wall found
                        break;
                    } else {
                        System.err.println("Invalid character in map");
                        printMap();
                        return null;
                    }
                }
                break;
            case LEFT:
                // System.out.println("Before loop, agent x: " + this.agent.getX() + " agent y:
                // " + this.agent.getY());
                for (int cursor = startColumn - 1; cursor >= 0; cursor--) {
                    // System.out.println("Loop triggered");
                    if (this.level[startRow][cursor] == '1') {
                        // System.out.println("agent found 1");
                        this.level[startRow][cursor] = 'x';
                        this.level[startRow][this.agent.getX()] = '0';
                        this.agent.setX(cursor);
                    } else if (this.level[startRow][cursor] == 'y') {
                        System.out.println("Goal reached");
                        this.agent.setX(cursor);
                        break;
                    } else if (this.level[startRow][cursor] == '0') {
                        System.out.println("Wall found");
                        // TODO: Implement wall found
                        break;
                    } else {
                        System.err.println("Invalid character in map");
                        printMap();
                        return null;
                    }
                }
                System.out.println("Agent has moved right :");
                break;
            case RIGHT:
                // System.out.println("Before loop, agent x: " + this.agent.getX() + " agent y:
                // " + this.agent.getY());
                for (int cursor = startColumn + 1; cursor < this.width; cursor++) {
                    // System.out.println("Loop triggered");
                    if (this.level[startRow][cursor] == '1') {
                        System.out.println("agent found 1");
                        this.level[startRow][cursor] = 'x';
                        this.level[startRow][this.agent.getX()] = '0';
                        this.agent.setX(cursor);
                    } else if (this.level[startRow][cursor] == 'y') {
                        System.out.println("Goal reached");
                        this.agent.setX(cursor);
                        break;
                    } else if (this.level[startRow][cursor] == '0') {
                        System.out.println("Wall found");
                        // TODO: Implement wall found
                        break;
                    } else {
                        System.err.println("Invalid character in map");
                        printMap();
                        return null;
                    }
                }
                System.out.println("Agent has moved right :");
                break;
        }
        return move;
    }

    private boolean isWall(int x, int y) {
        if (this.level[x][y] == '0') {
            return true;
        }
        return false;
    }

    public Agent getAgent() {
        return this.agent;
    }

    public int getGoalPositionX() {
        return 0;
    }

    public int getGoalPositionY() {
        return 0;
    }

    public boolean isGoalReached() {
        return false;
    }

    public void printMap() {
        System.out.println("Level: ");
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                System.out.print(this.level[i][j]);
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

}