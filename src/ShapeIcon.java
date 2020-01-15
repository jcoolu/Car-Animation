import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
An icon that contains a moveable shape.
 */
public class ShapeIcon extends JPanel implements Icon
{
    private int width;
    private int height;
    private ArrayList <CarShape> cars = new ArrayList<CarShape>();
    
    public ShapeIcon(ArrayList <CarShape> shapes,
    int width, int height)
    {
        cars = shapes;
        this.width = width;
        this.height = height;
    }
    /**
     * Returns ShapeIcon width
     * @return width
     */
    public int getIconWidth()
    {
        return width;
    }

    /**
     * Returns ShapeIcon height
     * @return height
     */
    public int getIconHeight()
    {
        return height;
    }

    /**
     * Paints icons (cars) and road.
     * @param c
     * @param g
     * @param x
     * @param y
     */
    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        // draws image background
        URL resource = getClass().getResource("road.png");
        BufferedImage image = null;
        try {
            image = ImageIO.read(resource);

        } catch (IOException e) {
            e.printStackTrace();
        }
        super.paintComponent(g);
        int displacement = 0;
        for(int j = 0; j < AnimationTester.getLength()/720 + 1; j++) {
            g.drawImage(image, 0, displacement * 720, 720, 720, this);
        displacement++;
    }
        Graphics2D g2 = (Graphics2D) g;
        for(int i =0; i < cars.size(); i++) 
            cars.get(i).draw(g2); 

        // has draw method since it implements MoveableShape
    }

    /**
     * Sets Icon width
     * @param wid - width
     */
    public void setIconWidth(int wid) {
        width = wid;
    }

    /**
     * Sets the icon height
     * @param hig - height
     */
    public void setIconHeight(int hig) {
        height = hig;
    }
}

