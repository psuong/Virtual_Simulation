/**
 * Created by Porrith on 5/21/2015.
 */
public class Main {
    public static void main(String[] args) {
        World world = World.getInstance();

        Herbivore rabbit = new Herbivore(0, 0, world);
        world.setObject(0, 0, rabbit);
        world.printField();
    }
}
