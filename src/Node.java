import java.util.ArrayList;

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
    private boolean isEnd;

    public Node(int column, int row)
    {
        x = column;
        y = row;
        cost = 0;
        heuristicValue = 0;
        totalCost = 0;
        isVisited = false;
        isEnd = false;
    }

    public void setCost(int cost)
    {
        this.cost = cost;
    }

    public void setEnd()
    {
        isEnd = true;
    }

    public void setVisited()
    {
        isVisited = true;
    }

    public void setHeuristicValue(int heuristicValue)
    {
        this.heuristicValue = heuristicValue;
    }

    public void setPrediction(int total)
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

    public int getPredictionVal()
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

    public boolean getEnd()
    {
        return isEnd;
    }
}
