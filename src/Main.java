/**
 * Created by Porrith on 5/21/2015.
 */
public class Main {
    public static void main(String[] args) {

        FieldManager manager = FieldManager.getFieldManager();
        World world = World.getInstance();

        for (int i = 0; i < 5; i++)
        {
            Herbivore rabbit = new Herbivore(0, i, world);
            world.setObject(rabbit.getX(), rabbit.getY(), rabbit);
        }
        world.printField();
    }
}
