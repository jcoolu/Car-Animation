import java.awt.*;
import java.awt.geom.*;

/**
A car that can be moved around.
 */
public class CarShape 
{
    private int x;
    private int y;
    private int width;
    private final Color color; //color of CarShape (for body of Car)
    /**
    Constructs a car item.
    @param x the left of the bounding rectangle
    @param y the top of the bounding rectangle
    @param width the width of the bounding rectangle
     */

    public CarShape(int x, int y, int width, Color col)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        color = col;
    }
    
    /**
     * Translates x and y coordinate of
     * CarShape by values given by user.
     * @param dx x position
     * @param dy y positon
     */
    public void translate(int dx, int dy)
    {
        x += dx;
        y += dy;
    }
    
    /**
     * Returns x coordinate of CarShape
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the value of x position
     * @param xint x position of CarShape
     */
    public void setX(int xint) {
        x = xint;
    }

    public void draw(Graphics2D g2)
    {
        Rectangle2D.Double body
        = new Rectangle2D.Double(x, y + width / 6, 
                width - 1, width / 6);
        Ellipse2D.Double frontTire
        = new Ellipse2D.Double(x + width / 6, y + width / 3, 
                width / 6, width / 6);
        Ellipse2D.Double rearTire
        = new Ellipse2D.Double(x + width * 2 / 3, y + width / 3,
                width / 6, width / 6);

        // The bottom of the front windshield
        Point2D.Double r1
        = new Point2D.Double(x + width / 6, y + width / 6);
        // The front of the roof
        Point2D.Double r2
        = new Point2D.Double(x + width / 3, y);
        // The rear of the roof
        Point2D.Double r3
        = new Point2D.Double(x + width * 2 / 3, y);
        // The bottom of the rear windshield
        Point2D.Double r4
        = new Point2D.Double(x + width * 5 / 6, y + width / 6);
        Line2D.Double frontWindshield
        = new Line2D.Double(r1, r2);
        Line2D.Double roofTop
        = new Line2D.Double(r2, r3);
        Line2D.Double rearWindshield
        = new Line2D.Double(r3, r4);
        
        g2.setColor(color);//sets color to be color entered when Constructor is called
        g2.fill(body);
        g2.draw(body);
        g2.setColor(Color.BLACK);
        g2.fill(frontTire);
        g2.draw(frontTire);
        g2.fill(rearTire);
        g2.draw(rearTire);
        g2.setColor(color);
        g2.fill(frontWindshield);
        g2.draw(frontWindshield);
        g2.fill(roofTop);
        g2.draw(roofTop);
        g2.draw(rearWindshield);
    }
}   
