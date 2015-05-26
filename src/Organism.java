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

    //getters
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

    //setters
    //sets initial age between 1 to 10
    protected void setInitAge()
    {
        age = rng.nextInt(10);
    }

    //sets the initial energy between 1 to 50
    protected void initEnergy()
    {
        energy = rng.nextInt(50);
    }

    //sets the reference of the world
    public void setWorldRef(World world)
    {
        reference = world;
    }

    //sets the manager's reference
    public void setManagerRef(Manager manager)
    {
        this.manager = manager;
    }

    //tells the object to remove itself from the world if energy is 0
    public void die()
    {
        if (energy == 0)
        {
            reference.removeObj(x, y);
        }
    }

    //places the object onto the world, utility function to allow movement
    public void setCoordinates()
    {
        reference.setObject(x, y, this);
    }

    //stores the value of the x and y coordinate into the object
    public void setLocation(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    //allows the organism to move without leaving copies behind
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

    //adds the node to the organism's path
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

    //allows random wandering
    public void randDestination()
    {
        int endX = rng.nextInt(reference.getDimension() - 1);
        int endY = rng.nextInt(reference.getDimension() - 1);
        manager.setOrganism(this);
        //System.out.println("Object: " + this + " Random Destination: " + "X: " + endX + " Y: " + endY + " Current: " + " X: " + x + " Y: " + y);
        manager.aStarSearch(endX, endY);
    }

    //checks if path is empty
    public boolean isPathEmpty()
    {
        if (path.isEmpty())
            return true;
        else
            return false;
    }

    //clears the path
    public void clearPath()
    {
        path.clear();
    }

    //utility function to set size of path
    public int getPathSize()
    {
        return path.size();
    }

    //allows organism to eat and get energy
    public void eat(int energy)
    {
        this.energy += (energy * 0.7);
    }

    //loses 1 eneryg per point
    public void loseEnergy()
    {
        energy -= 1;
    }
}
