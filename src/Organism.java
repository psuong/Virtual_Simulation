/**
 * Created by Porrith on 5/21/2015.
 */
public abstract class Organism {
    protected int energy;
    protected int age;
    protected int x, y;

    public Organism()
    {

    }

    public Organism(int x, int y, int energy, int age)
    {
        this.x = x;
        this.y = y;
        this.energy = energy;
        this.age = age;
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
}
