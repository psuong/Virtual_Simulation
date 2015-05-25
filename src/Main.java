import java.util.Random;

/**
 * Created by Porrith on 5/21/2015.
 */
public class Main {
    public static void main(String[] args) {

        World world = World.getInstance();
        Manager manager = new Manager(world);

        for (int i = 0; i < 3; i++) {
            Herbivore herbivore = new Herbivore(i, i, world, manager);
            manager.addOrganism(herbivore);
        }

//        manager.setOrganism(herbivore);
        System.out.println("Initial Field");
        world.printField();

        //manager.createPath();

        //for (int i = 0; i < 10; i++)
        //{
         //   manager.moveObj();
          //  world.printField();
        //}

        for (int i = 0; i < 10; i++)
        {
            manager.createPath();
            manager.moveObj();
            world.printField();
        }
    }
}
