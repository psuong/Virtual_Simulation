/**
 * Created by Porrith on 5/22/2015.
 */
public class Manager {
    private World reference;
    private int dimension;

    private Node field[][] = new Node[dimension][dimension];

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
        int currentX, currentY;
        field[y][x].setCost(0);

        int heuristic = heuristicCost(x, y, x1, y1);
        int total = field[y][x].getCost() + heuristic;
        field[y1][x1].setEnd();
        field[y][x].setVisited();
        field[y][x].setHeuristicValue(heuristic);
        field[y][x].setTotalCost(total);

    }
}
