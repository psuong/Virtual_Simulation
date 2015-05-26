import java.util.Random;

/**
 * Created by Porrith on 5/26/2015.
 */
public class RunEnv implements Runnable {
    private Thread thread;
    private World world;
    private Manager manager;
    private Random rng;
    private GUI gui;

    public RunEnv(World world, Manager manager, Random rng, GUI gui)
    {
        this.world = world;
        this.manager = manager;
        this.rng = rng;
        this.gui = gui;
        thread = new Thread();
    }

    @Override
    public void run()
    {
        try {
            for (int i = 0; i < 10; i++) {
                manager.createPath();
                manager.moveObj();
                manager.eat();
                manager.loseEnergy();
                manager.killObject();
                manager.updateField();
                gui.initGUI();
                thread.sleep(500);
                System.out.println("Generation Done " + i);
                //world.printField();
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("Thread " +  thread + " interrupted.");
        }
    }

    public void initSpawn()
    {

        for (int i = 0; i < 5; i++)
        {
            Herbivore herbivore = new Herbivore(rng.nextInt(world.getDimension()), rng.nextInt(world.getDimension()), world, manager);
            manager.addOrganism(herbivore);
        }

        for (int i = 0; i < 6; i++)
        {
            Plant plant = new Plant(rng.nextInt(world.getDimension()), rng.nextInt(world.getDimension()), world, manager);
            manager.addOrganism(plant);
        }

        for (int i = 0; i < 5; i++)
        {
            Carnivore carnivore = new Carnivore(rng.nextInt(world.getDimension()), rng.nextInt(world.getDimension()), world, manager);
            manager.addOrganism(carnivore);
        }

        /*
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
        */
    }
}
