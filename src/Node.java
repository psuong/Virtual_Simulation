/**
 * Created by Porrith on 5/22/2015.
 * Node stores heuristic value using Manhattan distance for A* algorithm.
 */
public class Node {
    private int x, y;
    private int cost;
    private int heuristicValue;
    private int totalCost;
    private boolean isVisited;

    public Node(int column, int row)
    {
        x = column;
        y = row;
        cost = 10;
        heuristicValue = 0;
        totalCost = 0;
        isVisited = false;
    }

    public void setCoordinates(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void setCost(int cost)
    {
        this.cost = cost;
    }

    public void setHeuristicValue(int heuristicValue)
    {
        this.heuristicValue = heuristicValue;
    }

    public void setTotalCost(int total)
    {
        totalCost = total;
    }

    public int getCost()
    {
        return cost;
    }

    public int getHeuristicValue()
    {
        return heuristicValue;
    }

    public int getTotalCost()
    {
        return totalCost;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public boolean getVisited()
    {
        return isVisited;
    }
}
