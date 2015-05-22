/**
 * Created by Porrith on 5/21/2015.
 */
public abstract class Organism {
    protected int energy;
    protected int age;
    protected int x, y;
    protected World reference;

    public Organism(World world)
    {
        setWorldRef(world);
    }


    public Organism(int x, int y, World world)
    {
        setWorldRef(world);
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getAge()
    {
        return age;
    }

    public int getEnergy()
    {
        return energy;
    }

    protected void setInitAge()
    {

    }
    protected void setEnergy()
    {

    }

    public void setWorldRef(World world)
    {
        reference = world;
    }

    public void die()
    {
        if (energy == 0)
        {
            reference.removeObj(x, y);
        }
    }

    public void setCoordinates()
    {
        //reference.setObject(x, y, Organism);
    }
}
