/**
 * Created by Porrith on 5/21/2015.
 */
public class Main {
    public static void main(String[] args) {

        World world = World.getInstance();
        Manager manager = new Manager(world);
        Herbivore herbivore = new Herbivore(0, 0, world);

        //world.printField();
        manager.setOrganism(herbivore);
        herbivore.setCoordinates();
        manager.aStarSearch(2, 3);
        //world.printField();
        herbivore.checkPath();

    }
}
