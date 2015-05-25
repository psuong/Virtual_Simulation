import java.util.Vector;

/**
 * Created by Porrith on 5/22/2015.
 */
public class Manager {
    private World reference;
    private Organism organism;
    private int dimension;

    private Node field[][];

    private Vector<Node> openList;

    private Vector<Organism> meatEaters;
    private Vector<Organism> plantEaters;
    private Vector<Organism> plants;

    public Manager (World world)
    {
        reference = world;
        dimension = world.getDimension();
        field = new Node[dimension][dimension];

        openList = new Vector<>();
        meatEaters = new Vector<>();
        plantEaters = new Vector<>();
        plants = new Vector<>();

        for (int row = 0; row < dimension; row++)
        {
            for (int column = 0; column < dimension; column++)
            {
                field[row][column] = new Node(column, row);
            }
        }
    }

    public void setOrganism (Organism organism)
    {
        this.organism = organism;
    }

    public void addOrganism(Organism organism)
    {
        if (organism instanceof Herbivore)
            plantEaters.add(organism);
        else
            plants.add(organism);
    }

    private int heuristicCost(int x, int y, int x1, int y1)
    {
        int heuristics = (Math.abs(x1 - x)*10) + (Math.abs(y1 - y)*10);
        return heuristics;
    }

    public void aStarSearch(int x1, int y1)
    {
        int row = organism.getY();
        int column = organism.getX();

        field[row][column].setCost(0);
        field[y1][x1].setEnd();
        //System.out.println("Is End: " + field[y1][x1].getEnd() + " Row: " + y1 + " Col: " + x1);
        field[row][column].setVisited();
        field[row][column].setHeuristicValue(heuristicCost(column, row, x1, y1));
        field[row][column].setPrediction(heuristicCost(column, row, x1, y1) + field[row][column].getCost());

        while (!field[row][column].getEnd())
        {
            checkNeighbors(column, row, x1, y1);
            sortList();
            row = openList.elementAt(0).getY();
            column = openList.elementAt(0).getX();
            addToPath();
        }
        resetField();
    }

    private void checkNeighbors(int x, int y, int x1, int y1) {
        int minX = 0;
        int maxX = 0;
        int minY = 0;
        int maxY = 0;
        if (x == dimension - 1)
        {
            minX = x - 1;
            maxX = x;
            //System.out.println("1: MinX: " + minX + " MinY: " + minY + " MaxX: " + maxX + " MaxY: "+ maxY);
        }
        else if (x == 0)
        {
            minX = x;
            maxX = x + 1;
            //System.out.println("2: MinX: " + minX + " MinY: " + minY + " MaxX: " + maxX + " MaxY: "+ maxY);
        }
        else //if (x != 0 && x != dimension - 1)
        {
            minX = x - 1;
            maxX = x + 1;
            //System.out.println("3: MinX: " + minX + " MinY: " + minY + " MaxX: " + maxX + " MaxY: "+ maxY);
        }
        if (y == dimension - 1)
        {
            minY = y - 1;
            maxY = y;
            //System.out.println("4: MinX: " + minX + " MinY: " + minY + " MaxX: " + maxX + " MaxY: "+ maxY);
        }
        else if (y == 0)
        {
            minY = y;
            maxY = y + 1;
            //System.out.println("5: MinX: " + minX + " MinY: " + minY + " MaxX: " + maxX + " MaxY: "+ maxY);
        }
        else //if (y != 0 && y != dimension - 1)
        {
            minY = y - 1;
            maxY = y + 1;
            //System.out.println("6: MinX: " + minX + " MinY: " + minY + " MaxX: " + maxX + " MaxY: "+ maxY);
        }

        //System.out.println("8: MinX: " + minX + " MinY: " + minY + " MaxX: " + maxX + " MaxY: "+ maxY);
        for (int row = minY; row <= maxY; row++)
        {
            for (int column = minX; column <= maxX; column++)
            {
                //System.out.println("Col: " + column + " Row: " + row);
                if (!field[row][column].getVisited() /*&& reference.checkLocation(column, row)*/)
                {
                    //System.out.println("Col: " + column + " Row: " + row);
                    field[row][column].setHeuristicValue(heuristicCost(column, row, x1, y1));
                    field[row][column].setCost(field[x][y].getCost() + field[row][column].getCost());
                    field[row][column].setPrediction(field[row][column].getCost() + field[row][column].getHeuristicValue());
                    field[row][column].setVisited();
                    openList.add(field[row][column]);
                }
            }
        }
    }

    private void sortList()
    {
        Node temp;
        int index;
        for (int i = 1; i < openList.size(); i++)
        {
            index = i;
            while (index > 0 && (openList.elementAt(index - 1).getPredictionVal() > openList.elementAt(index).getPredictionVal()))
            {
                temp = openList.elementAt(index - 1);
                openList.set(index - 1, openList.elementAt(index));
                openList.set(index, temp);
                index--;
            }
        }
    }

    private void addToPath()
    {
        organism.addPath(openList.elementAt(0));
        for (int i = 0; i < openList.size(); i++) {
            openList.elementAt(i).setVisited();
        }
        openList.removeAllElements();
    }

    private void resetField()
    {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                field[i][j] = new Node(j, i);
            }
        }
    }

    public void die()
    {
        for (int i = meatEaters.size() - 1; i >= 0; i--)
        {
            meatEaters.elementAt(i).die();
        }
        for (int i = plantEaters.size() - 1; i >= 0; i--)
        {
            plantEaters.elementAt(i).die();
            plantEaters.remove(i);
        }
        for (int i = plants.size() - 1; i >= 0; i--)
        {
            plants.elementAt(i).die();
        }
    }

    public int getMeatEater()
    {
        return meatEaters.size();
    }

    public int getPlantEater()
    {
        return plantEaters.size();
    }

    public int getPlant()
    {
        return plantEaters.size();
    }

    public Organism accessPlantEater(int i)
    {
        return plantEaters.elementAt(i);
    }

    public void createPath()
    {
        //System.out.println("# of Animals: " + plantEaters.size());
        for (int i = 0; i < plantEaters.size(); i++) {
            //System.out.println("Path Empty: " + plantEaters.elementAt(i).isPathEmpty());
            if (plantEaters.elementAt(i).isPathEmpty())
                plantEaters.elementAt(i).randDestination();
        }
    }

    public void moveObj()
    {
        for(int i = 0; i < plantEaters.size(); i++)
        {
            //System.out.println("Path Empty: " + !plantEaters.elementAt(i).isPathEmpty());
            if (!plantEaters.elementAt(i).isPathEmpty())
            {
                plantEaters.elementAt(i).move();
            }
        }
    }

}
