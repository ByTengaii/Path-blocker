import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Environment env = new Environment();
        Moves move = Moves.DOWN;
        env.action(move);
        env.printMap();
    }
}
