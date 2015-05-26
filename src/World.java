/**
 * Created by Porrith on 5/21/2015.
 */
public class World {

    private static World world = new World();
    private int dimension = 10;

    private Organism field[][] = new Organism[dimension][dimension];

    private World()
    {
        for (int i = 0; i < dimension; i++)
        {
            for (int j = 0; j < dimension; j++) {
                field[i][j] = null;
            }
        }
    }

    public static World getInstance()
    {
        return world;
    }

    public void removeObj(int x, int y)
    {
        field[y][x] = null;
    }

    public void setObject(int x, int y, Organism organism)
    {
        field[y][x] = organism;
    }

    public boolean checkLocation(int x, int y)
    {
        if (field[y][x] == null)
            return true;
        else
            return false;
    }

    //the print function below is just a utility
    public void printField()
    {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print("[" + field[i][j] + "]");
            }
            System.out.println("\n");
        }
        System.out.println("\n");
    }

    public int getDimension()
    {
        return dimension;
    }

    public Organism getOrganism(int row, int column)
    {
        System.out.println(field[row][column]);
        return field[row][column];
    }

    public void accessElement(int x, int y)
    {
        System.out.println(field[y][x] + " Energy: " + field[y][x].getEnergy());
    }
}