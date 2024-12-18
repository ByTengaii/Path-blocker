import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import javax.imageio.ImageIO;


public class Environment {

    private int width = 0;
    private int height = 0;
    private char[][] level;
    private Agent agent;
    private Goal goal;

    Environment(int level) {
        updateWorld(level);
    }


    Environment(Environment env){
        this.width = env.width;
        this.height = env.height;
        this.level = copyWorld(env.level);
        this.agent = new Agent(env.agent.getY(), env.agent.getX());
        this.goal = new Goal(env.goal.getY(), env.goal.getX());
    }

    public boolean action(Moves move) {
        if (this.level == null) {
            System.out.println("The level has not been initialized");
            return false;
        }
        int startRow = this.agent.getY();
        int startColumn = this.agent.getX();
        boolean goalReached = false;
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
                        this.level[cursor][startColumn] = 'x';
                        this.level[this.agent.getY()][startColumn] = '0';
                        this.agent.setY(cursor);
                        goalReached = true;
                        break;
                    } else if (this.level[cursor][startColumn] == '0') {
                        //System.out.println("Wall found");
                        break;
                    } else {
                        System.err.println("Invalid character in map");
                        printMap();
                        return false;
                    }
                }
                break;
            case DOWN:;
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
                        this.level[cursor][startColumn] = 'x';
                        this.level[this.agent.getY()][startColumn] = '0';
                        this.agent.setY(cursor);
                        goalReached = true;
                        break;
                    } else if (this.level[cursor][startColumn] == '0') {
                        //System.out.println("Wall found");
                        break;
                    } else {
                        System.err.println("Invalid character in map");
                        printMap();
                        return false;
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
                        this.level[startRow][cursor] = 'x';
                        this.level[startRow][this.agent.getX()] = '0';
                        this.agent.setX(cursor);
                        goalReached = true;
                        break;
                    } else if (this.level[startRow][cursor] == '0') {
                        //System.out.println("Wall found");
                        break;
                    } else {
                        System.err.println("Invalid character in map");
                        printMap();
                        return false;
                    }
                }
                break;
            case RIGHT:
                // System.out.println("Before loop, agent x: " + this.agent.getX() + " agent y:
                // " + this.agent.getY());
                for (int cursor = startColumn + 1; cursor < this.width; cursor++) {
                    // System.out.println("Loop triggered");
                    if (this.level[startRow][cursor] == '1') {
                        // System.out.println("agent found 1");
                        this.level[startRow][cursor] = 'x';
                        this.level[startRow][this.agent.getX()] = '0';
                        this.agent.setX(cursor);
                    } else if (this.level[startRow][cursor] == 'y') {
                        this.level[startRow][cursor] = 'x';
                        this.level[startRow][this.agent.getX()] = '0';
                        this.agent.setX(cursor);
                        goalReached = true;
                        break;
                    } else if (this.level[startRow][cursor] == '0') {
                        //System.out.println("Wall found");
                        // TODO: Implement wall found
                        break;
                    } else {
                        System.err.println("Invalid character in map");
                        printMap();
                        return false;
                    }
                }
                break;
        }
        //System.out.println(move + " action has been taken:");
        //printMap();
        if (goalReached) {
            System.out.println("Goal reached");
            return true;
        }
        return false;

    }

    public Agent getAgent() {
        return this.agent;
    }

    public void printMap() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                System.out.print(this.level[i][j]);
            }
            System.out.println();
        }
    }

    public char[][] getLevel() {
        return this.level;
    };

    //! Can be more optimize
    public List<Moves> getPossibleMoves() {
        List<Moves> moveList = new ArrayList<>();
        Moves move;
        if (agent.getY() + 1 < this.height) {
            if (this.level[this.agent.getY() + 1][this.agent.getX()] != '0') {
                move = Moves.DOWN;
                moveList.add(move);
            }
        }
        if (agent.getY() - 1 >= 0) {
            if (this.level[this.agent.getY() - 1][this.agent.getX()] != '0') {
                move = Moves.UP;
                moveList.add(move);
            }
        }
        if (agent.getX() + 1 < this.width) {
            if (this.level[this.agent.getY()][this.agent.getX() + 1] != '0') {
                move = Moves.RIGHT;
                moveList.add(move);
            }
        }
        if (agent.getX() - 1 >= 0) {
            if (this.level[this.agent.getY()][this.agent.getX() - 1] != '0') {
                move = Moves.LEFT;
                moveList.add(move);
            }
        }

        return moveList;
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
            //System.out.println("The level has been updated:");
            //printMap();
            initAgentAndGoal(this.level);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private char[][] copyWorld(char[][] level) {
        // TODO: Implement createWorld
        char [][] copy = new char[level.length][level[0].length];
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[0].length; j++) {
                copy[i][j] = level[i][j];
            }
        }
        return copy;
    }
    private void initAgentAndGoal(char[][] level) {
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
            //System.out.println("Agent has been initialized at position: " + this.agent.getX() + ", " + this.agent.getY());
            //System.out.println("Goal has been initialized at position: " + this.goal.getX() + ", " + this.goal.getY());
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

    public void saveImage(String folderPath, int stepNumber) {
        int cellSize = 10; // because you said 10 during the class
        BufferedImage image = new BufferedImage(width * cellSize, height * cellSize, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (level[i][j] == '0') { // black for wall
                    g.setColor(Color.BLACK);
                } else if (level[i][j] == '1') { // white for path
                    g.setColor(Color.WHITE);
                } else if (level[i][j] == 'x') { // blue for player
                    g.setColor(Color.BLUE);
                } else if (level[i][j] == 'y') { // green for goal
                    g.setColor(Color.GREEN);
                }
                g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                g.setColor(Color.GRAY);
                g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
            }
        }

        g.dispose();
        try {
            File outputFile = new File(folderPath + String.format("%03d", stepNumber) + ".png");
            ImageIO.write(image, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
