import javax.swing.*;
import java.awt.*;

/**
 * Created by Porrith on 5/25/2015.
 */
public class GUI extends JFrame {
    private World reference;
    private int rows;
    private int column;

    public GUI (World world)
    {
        reference = world;
        rows = reference.getDimension();
        column = reference.getDimension();
    }

    public void initGUI()
    {
        JFrame frame = new JFrame("Simulation");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(new Grid());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public class Grid extends JPanel
    {
        GridBagConstraints gridBag;
        public Grid()
        {
            setLayout(new GridBagLayout());
            gridBag = new GridBagConstraints();

            for (int i = 0; i < rows; i++)
            {
                for (int j = 0; j < column; j++)
                {
                    Color color;
                    if (reference.getOrganism(i, j) instanceof Plant)
                        color = Color.GREEN;
                    else if (reference.getOrganism(i, j) instanceof Herbivore)
                        color = Color.BLUE;
                    else if (reference.getOrganism(i, j) instanceof Carnivore)
                        color = Color.RED;
                    else
                        color = Color.WHITE;

                    gridBag.gridx = j;
                    gridBag.gridy = i;
                    add(new Cell(color), gridBag);
                }
            }
        }
    }

    public class Cell extends JButton
    {
        public Cell(Color bg)
        {
            setContentAreaFilled(false);
            setBorderPainted(false);
            setBackground(bg);
            setOpaque(true);
        }

        @Override
        public Dimension getPreferredSize()
        {
            return new Dimension(25, 25);
        }
    }

}
