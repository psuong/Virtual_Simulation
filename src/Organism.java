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
    protected boolean isMoving;

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
        path = new LinkedList<Node>();
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

    }

    public void addPath(Node node)
    {
        path.add(node);
        //System.out.println("X: " + node.getX() + " Y: " + node.getY());
    }

    public Queue getPath()
    {
        return path;
    }

    public void popElement()
    {
        System.out.println(path.size());
        if (!path.isEmpty()) {
            Node node = path.remove();
            System.out.println(node.getHeuristicValue());
        }
    }
}
