import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by Porrith on 5/21/2015.
 */
public abstract class Organism {
    protected int energy;
    protected int age;
    protected int x, y;
    protected World reference;

    protected Queue<Node> path;

    public Organism(World world)
    {
        setWorldRef(world);
    }


    public Organism(int x, int y, World world)
    {
        setWorldRef(world);
        this.x = x;
        this.y = y;
        path = new LinkedList<>();
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
        reference.setObject(x, y, this);
    }

    public void setLocation(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void move()
    {
        if (!path.isEmpty()) {
            if (x == path.peek().getX() && y == path.peek().getY())
                path.remove();
            reference.removeObj(x, y);
            setLocation(path.peek().getX(), path.peek().getY());
            path.remove();
        }
    }

    public void addPath(Node node)
    {
        path.add(node);
    }

    //utility function for debugging
    public void checkPath()
    {
        while (!path.isEmpty())
        {
            System.out.println("Column: " + path.peek().getX() + " Row: " + path.peek().getY());
            path.remove();
        }
    }
}
