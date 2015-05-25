/**
 * Created by Porrith on 5/25/2015.
 */
public class Plant extends Organism {
    public Plant(int x, int y, World world)
    {
        super(x, y, world);
        setInitAge();
        setEnergy();
    }
}
