import java.util.Stack;

/**
 * Created by Porrith on 5/22/2015.
 */
public class Manager {
    private World reference;
    private int dimension;
    private int diagCost = 14;

    private Node field[][] = new Node[dimension][dimension];
    private Stack<Node> path;

    public Manager (World world)
    {
        reference = world;
        dimension = world.getDimension();
        for (int i = 0; i < dimension; i++)
        {
            for (int j = 0; j < dimension; j++)
            {
                field[i][j] = new Node(j, i);
            }
        }
    }

    private int heuristicCost(int x, int y, int x1, int y1)
    {
        int heuristics = (Math.abs(x1 - x)*10) + (Math.abs(y1 - y)*10);
        return heuristics;
    }

    public void aStarSearch(int x, int y, int x1, int y1)
    {
        int row = y;
        int column = x;
        field[row][column].setCost(0);

        int heuristic = heuristicCost(x, y, x1, y1);
        int total = field[y][x].getCost() + heuristic;
        field[y1][x1].setEnd();
        field[row][column].setVisited();
        field[row][column].setHeuristicValue(heuristic);
        field[row][column].setTotalCost(total);

        while (isFinished(column, row, x1, y1))
        {

        }
    }

    private boolean isFinished (int x, int y, int x1, int y1)
    {
        if (x != x1 && y != y1)
            return false;
        else
            return true;
    }

    private void checkNeighbors(int x, int y)
    {
        if (x == 0 && y == 0)
        {
            if (x != x + 1 && y != y + 1)
                field[y + 1][x + 1].setCost(diagCost);
        }
        else if (x == dimension - 1 && y == dimension - 1)
        {
            field[y - 1][x - 1].setCost(diagCost);
        }
        else if ((x != 0 && x != dimension - 1) && (y != 0 && y != dimension - 1))
        {
            field[y + 1][x + 1].setCost(diagCost);
            field[y + 1][x - 1].setCost(diagCost);
            field[y - 1][x + 1].setCost(diagCost);
            field[y - 1][x - 1].setCost(diagCost);
        }
    }
}
