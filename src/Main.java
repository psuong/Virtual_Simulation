import java.util.Random;

/**
 * Created by Porrith on 5/21/2015.
 */
public class Main {
    public static void main(String[] args) {

        World world = World.getInstance();
        Manager manager = new Manager(world);

        for (int i = 0; i < 1; i++) {
            Herbivore herbivore = new Herbivore(i, i, world, manager);
            manager.addOrganism(herbivore);
        }

        Plant plant = new Plant(0, 1, world, manager);
        manager.addOrganism(plant);

//        manager.setOrganism(herbivore);
        System.out.println("Initial Field");
        world.printField();

        //manager.createPath();

        //for (int i = 0; i < 10; i++)
        //{
         //   manager.moveObj();
          //  world.printField();
        //}

        manager.eat();
            //manager.createPath();
            //manager.moveObj();
            //world.printField();
        world.printField();
        world.accessElement(0, 0);
        //world.accessElement(0, 1);
    }
}