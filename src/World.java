/**
 * Created by Porrith on 5/21/2015.
 */
public class World {

    private World world = new World();
    private int dimension = 30;

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

    public World getInstance()
    {
        return world;
    }

}
