import java.util.Random;

/**
 * Created by Porrith on 5/21/2015.
 */
public class Main {
    public static void main(String[] args) {

        World world = World.getInstance();
        Manager manager = new Manager(world);
        GUI gui = new GUI(world);

        Random rng = new Random();

        RunEnv runThis = new RunEnv(world, manager, rng, gui);
        runThis.initSpawn();
        gui.initGUI();
        runThis.run();
    }
}