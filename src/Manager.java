import java.util.Vector;

/**
 * Created by Porrith on 5/22/2015.
 */
public class Manager {
    //Setting references for the manager to handle each object
    private World reference;
    private Organism organism;
    private int dimension;

    private Node field[][];

    //openList used for sorting to get least costly path
    private Vector<Node> openList;

    //stores references of each organism on the field
    private Vector<Organism> meatEaters;
    private Vector<Organism> plantEaters;
    private Vector<Organism> plants;

    //constructor initializes every single vector and sets the references
    public Manager (World world)
    {
        reference = world;
        dimension = world.getDimension();
        field = new Node[dimension][dimension];

        openList = new Vector<>();
        meatEaters = new Vector<>();
        plantEaters = new Vector<>();
        plants = new Vector<>();

        //initializing a field for determining the path for every movable organism
        for (int row = 0; row < dimension; row++)
        {
            for (int column = 0; column < dimension; column++)
            {
                field[row][column] = new Node(column, row);
            }
        }
    }

    //sets the reference to an organism
    public void setOrganism (Organism organism)
    {
        this.organism = organism;
    }

    //adds the organism to a list
    public void addOrganism(Organism organism)
    {
        if (organism instanceof Herbivore)
            plantEaters.add(organism);
        else if (organism instanceof Carnivore)
            meatEaters.add(organism);
        else
            plants.add(organism);
    }

    //gets the Manhattan distance for A* algorithm
    private int heuristicCost(int x, int y, int x1, int y1)
    {
        int heuristics = (Math.abs(x1 - x)*10) + (Math.abs(y1 - y)*10);
        return heuristics;
    }

    //A* search
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

    //checks the Neighbors to determine the least costly neighbor to get to a destination
    private void checkNeighbors(int x, int y, int x1, int y1) {
        int minX = 0;
        int maxX = 0;
        int minY = 0;
        int maxY = 0;
        if (x == dimension - 1)
        {
            minX = x - 1;
            maxX = x;
        }
        else if (x == 0)
        {
            minX = x;
            maxX = x + 1;
        }
        else
        {
            minX = x - 1;
            maxX = x + 1;
        }
        if (y == dimension - 1)
        {
            minY = y - 1;
            maxY = y;
        }
        else if (y == 0)
        {
            minY = y;
            maxY = y + 1;
        }
        else
        {
            minY = y - 1;
            maxY = y + 1;
        }
        
        for (int row = minY; row <= maxY; row++)
        {
            for (int column = minX; column <= maxX; column++)
            {
                if (!field[row][column].getVisited())
                {
                    field[row][column].setHeuristicValue(heuristicCost(column, row, x1, y1));
                    field[row][column].setCost(field[x][y].getCost() + field[row][column].getCost());
                    field[row][column].setPrediction(field[row][column].getCost() + field[row][column].getHeuristicValue());
                    field[row][column].setVisited();
                    openList.add(field[row][column]);
                }
            }
        }
    }

    //uses insertion sort to get sort the openList from lowest to highest
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

    //adds the lowest cost neighbor to the path of the organism
    private void addToPath()
    {
        organism.addPath(openList.elementAt(0));
        for (int i = 0; i < openList.size(); i++) {
            openList.elementAt(i).setVisited();
        }
        openList.removeAllElements();
    }

    //resets the field of nodes for future calculations
    private void resetField()
    {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                field[i][j] = new Node(j, i);
            }
        }
    }

    //removes the object from the world, function currently doesn't work
    public void killObject()
    {
        for (int i = meatEaters.size() - 1; i >= 0; i--)
        {
            meatEaters.elementAt(i).die();
            meatEaters.remove(i);
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
    //utility functions to get size of each list
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

    //creates the path for each organism
    public void createPath()
    {
        for (int i = 0; i < plantEaters.size(); i++) {
            if (plantEaters.elementAt(i).isPathEmpty())
                plantEaters.elementAt(i).randDestination();
        }
        System.out.println(meatEaters.size());
        for (int i = 0; i < meatEaters.size(); i++)
        {
            System.out.println(meatEaters.elementAt(i).isPathEmpty());
            if (meatEaters.elementAt(i).isPathEmpty())
                meatEaters.elementAt(i).randDestination();
        }
    }

    //moves the organism according to A* algorithm path
    public void moveObj()
    {
        for(int i = 0; i < plantEaters.size(); i++)
        {
            if (!plantEaters.elementAt(i).isPathEmpty())
            {
                plantEaters.elementAt(i).move();
            }
        }
        for (int i = 0; i < meatEaters.size(); i++)
        {
            if (!meatEaters.elementAt(i).isPathEmpty())
            {
                meatEaters.elementAt(i).move();
            }
        }
    }

    //returns the closest position for food
    public void getFoodPos(int x, int y, Organism organism)
    {
        setOrganism(organism);
        int minX = 0;
        int maxX = 0;
        int minY = 0;
        int maxY = 0;
        if (x == dimension - 1)
        {
            minX = x - 1;
            maxX = x;
        }
        else if (x == 0)
        {
            minX = x;
            maxX = x + 1;
        }
        else
        {
            minX = x - 1;
            maxX = x + 1;
        }
        if (y == dimension - 1)
        {
            minY = y - 1;
            maxY = y;
        }
        else if (y == 0)
        {
            minY = y;
            maxY = y + 1;
        }
        else
        {
            minY = y - 1;
            maxY = y + 1;
        }

        int energy = 0;

        for (int row = minY; row <= maxY; row++)
        {
            for (int column = minX; column <= maxX; column++)
            {
                if (organism instanceof Herbivore)
                {
                    if (reference.getOrganism(row, column) instanceof Plant)
                    {
                        if (!organism.isPathEmpty())
                            organism.clearPath();
                        energy = (int)(reference.getOrganism(row, column).getEnergy() * 0.7);
                        organism.eat(energy);
                        plants.remove(reference.getOrganism(row, column));
                        reference.removeObj(organism.getX(), organism.getY());
                        reference.setObject(column, row, organism);
                        organism.setLocation(column, row);
                        organism.setCoordinates();
                        break;
                    }
                }
                else
                    if (reference.getOrganism(row, column) instanceof Herbivore)
                    {
                        if (!organism.isPathEmpty())
                            organism.clearPath();
                        energy = (int)(reference.getOrganism(row, column).getEnergy() * 0.7);
                        organism.eat(energy);
                        plantEaters.remove(reference.getOrganism(row, column));
                        reference.removeObj(organism.getX(), organism.getY());
                        reference.setObject(column, row, organism);
                        organism.setLocation(column, row);
                        organism.setCoordinates();
                        break;
                    }
            }
        }
    }

    //Allows each organism to eat and gain energy
    public void eat()
    {
        for (int i = 0; i < plantEaters.size(); i++) {
            plantEaters.elementAt(i).manager.getFoodPos(plantEaters.elementAt(i).getX(), plantEaters.elementAt(i).getY(), plantEaters.elementAt(i));
        }
        for (int i = 0; i < meatEaters.size(); i++) {
            meatEaters.elementAt(i).manager.getFoodPos(meatEaters.elementAt(i).getX(), meatEaters.elementAt(i).getY(), meatEaters.elementAt(i));
        }
    }

    //Each turn organisms lose 1 energy
    public void loseEnergy()
    {
        for (int i = 0; i < plantEaters.size(); i++) {
            plantEaters.elementAt(i).loseEnergy();
        }
        for (int i = 0; i < meatEaters.size(); i++)
        {
            meatEaters.elementAt(i).loseEnergy();
        }
        for (int i = 0; i < plants.size(); i++)
        {
            plants.elementAt(i).loseEnergy();
        }
    }

    //updates the field due to collision of objects
    public void updateField()
    {
        for (int i = 0; i < plants.size(); i++)
        {
            plants.elementAt(i).setCoordinates();
        }
        for (int i = 0; i < meatEaters.size(); i++)
        {
            meatEaters.elementAt(i).setCoordinates();
        }
        for (int i = 0; i < plantEaters.size(); i++)
        {
            plantEaters.elementAt(i).setCoordinates();
        }
    }
}
