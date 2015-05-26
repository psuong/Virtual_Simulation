import java.util.Random;

/**
 * Created by Porrith on 5/21/2015.
 */
public class Main {
    public static void main(String[] args) {

        World world = World.getInstance();
        Manager manager = new Manager(world);

        Random rng = new Random();

        for (int i = 0; i < 7; i++) {
            Herbivore herbivore = new Herbivore(rng.nextInt(world.getDimension()), rng.nextInt(world.getDimension()), world, manager);
            manager.addOrganism(herbivore);
        }

        for (int i = 0; i < 5; i++)
        {
            Carnivore carnivore = new Carnivore(rng.nextInt(world.getDimension()), rng.nextInt(world.getDimension()), world, manager);
            manager.addOrganism(carnivore);
        }

        for (int i = 0; i < 7; i++)
        {
            Plant plant = new Plant(rng.nextInt(world.getDimension()), rng.nextInt(world.getDimension()), world, manager);
            manager.addOrganism(plant);
        }

        for (int i = 0; i < 10; i++)
        {
            manager.createPath();
            manager.moveObj();
            manager.killObject();
            manager.eat();
            manager.loseEnergy();
            world.printField();
        }
    }
}