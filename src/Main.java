/**
 * Created by Porrith on 5/21/2015.
 */
public class Main {
    public static void main(String[] args) {

        World world = World.getInstance();
        Manager manager = new Manager(world);
        Herbivore herbivore = new Herbivore(0, 0, world);

        Herbivore herbivore1 = new Herbivore(1, 1, world);

        world.printField();
        manager.setOrganism(herbivore);
        herbivore.setCoordinates();
        manager.aStarSearch(2, 3);
        manager.setOrganism(herbivore1);
        herbivore1.setCoordinates();
        manager.aStarSearch(6, 7);
        world.printField();
        //herbivore.checkPath();
        for (int i = 0; i < 10; i++) {
            herbivore.move();
            herbivore.setCoordinates();
            herbivore1.move();
            herbivore1.setCoordinates();
            world.printField();
        }

    }
}
