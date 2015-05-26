import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Created by Porrith on 5/21/2015.
 */
public abstract class Organism {
    protected int energy;
    protected int age;
    protected int x, y;
    protected World reference;
    protected Manager manager;
    protected Random rng;

    protected Queue<Node> path;

    public Organism(int x, int y, World world)
    {
        setWorldRef(world);
        rng = new Random();
        path = new LinkedList<>();this.x = x;
        this.y = y;
        setCoordinates();
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
        age = rng.nextInt(10);
    }
    protected void initEnergy()
    {
        energy = rng.nextInt(10);
    }

    public void setWorldRef(World world)
    {
        reference = world;
    }

    public void setManagerRef(Manager manager)
    {
        this.manager = manager;
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
            setCoordinates();
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
            //System.out.println("Column: " + path.peek().getX() + " Row: " + path.peek().getY());
            path.remove();
        }
    }

    public void randDestination()
    {
        int endX = rng.nextInt(reference.getDimension() - 1);
        int endY = rng.nextInt(reference.getDimension() - 1);
        manager.setOrganism(this);
        //System.out.println("Object: " + this + " Random Destination: " + "X: " + endX + " Y: " + endY + " Current: " + " X: " + x + " Y: " + y);
        manager.aStarSearch(endX, endY);
    }

    public boolean isPathEmpty()
    {
        if (path.isEmpty())
            return true;
        else
            return false;
    }

    public void clearPath()
    {
        path.clear();
    }

    public int getPathSize()
    {
        return path.size();
    }

    public void setZero()
    {
        energy = 0;
    }

    public void eat(int energy)
    {
        this.energy += (energy * 0.7);
    }

    public void loseEnergy()
    {
        energy -= 1;
    }
}
