/**
 * Created by Porrith on 5/21/2015.
 */
public class Herbivore extends Organism {

    public Herbivore(int x, int y, World world, Manager manager)
    {
        super(x, y, world);
        setInitAge();
        initEnergy();
        setManagerRef(manager);
    }
}
