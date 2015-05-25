import java.util.Stack;
import java.util.Vector;

/**
 * Created by Porrith on 5/22/2015.
 */
public class Manager {
    private World reference;
    private Organism organism;
    private int dimension;

    private Node field[][];

    private Stack<Node> closedList;
    private Vector<Node> openList;

    public Manager (World world)
    {
        reference = world;
        dimension = world.getDimension();
        field = new Node[dimension][dimension];

        openList = new Vector<>();
        closedList = new Stack<>();

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

        int heuristic = heuristicCost(column, row, x1, y1);
        int total = field[row][column].getCost() + heuristic;
        field[y1][x1].setEnd();
        field[row][column].setVisited();
        field[row][column].setHeuristicValue(heuristic);
        field[row][column].setPrediction(total);

        while (isFinished(column, row, x1, y1))
        {
            checkNeighbors(column, row, x1, y1);
            sortList();
            column = openList.elementAt(0).getX();
            row = openList.elementAt(0).getY();
            System.out.println("Row: " + row + " Column: " + column);
            addToPath();
            if (column == x1 && row == y1)
            {
                System.out.println("Row: " + row + " Column: " + column);
            }
        }
    }

    private boolean isFinished (int x, int y, int x1, int y1)
    {
        if (x != x1 && y != y1)
            return true;
        else
            return false;
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
        }
        else if (x == 0)
        {
            minX = x;
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
            minX = x - 1;
            minY = y - 1;
            maxX = x + 1;
            maxY = y + 1;
        }

        for (int row = minY; row <= maxY; row++)
        {
            for (int column = minX; column <= maxX; column++)
            {
                if (/*!field[row][column].getVisited() ||*/ !closedList.contains(field[row][column]))
                {
                    //System.out.println(heuristic + " " + row + " " + column + " " + x1 + " " + y1);
                    field[row][column].setHeuristicValue(heuristicCost(column, row, x1, y1));
                    field[row][column].setCost(field[x][y].getCost() + field[row][column].getCost());
                    field[row][column].setPrediction(field[row][column].getCost() + field[row][column].getHeuristicValue());
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
            closedList.push(openList.elementAt(i));
        }
        openList.removeAllElements();
    }

    public void clearClosed()
    {
        closedList.removeAllElements();
    }
}
