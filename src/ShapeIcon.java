import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
An icon that contains a moveable shape.
 */
public class ShapeIcon implements Icon
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

    public void paintIcon(Component c, Graphics g, int x, int y)
    {

        Graphics2D g2 = (Graphics2D) g;
        for(int i =0; i < cars.size(); i++) 
            cars.get(i).draw(g2); 

        // has draw method since it implements MoveableShape
    }

    public void setIconWidth(int wid) {
        width = wid;
    }

    public void setIconHeight(int hig) {
        height = hig;
    }
}

