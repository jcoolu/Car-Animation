import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.Color;
import javax.swing.Timer;

/**
This program implements an animation that moves
a car shape.
 */
public class AnimationTester
{
    private static final int ICON_WIDTH = 720;
    private static final int ICON_HEIGHT = 1280;
    private static final int CAR_WIDTH = 100;

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        Scanner scan = new Scanner(System.in);
        Random rand = new Random(255);

        ArrayList <CarShape> carList = new ArrayList<CarShape>();
        System.out.println("How many cars would you like shown?");
        int count = scan.nextInt();

        Color color;//color of each car

        for(int i = 0; i < count; i++) {
            color = new Color(rand.nextFloat(),rand.nextFloat(),
                rand.nextFloat());
            carList.add(new CarShape(i*(CAR_WIDTH/2) + 10,
                i*(CAR_WIDTH/2) + 10, CAR_WIDTH, color));
        }   
        
        ShapeIcon icon = new ShapeIcon(carList,
                ICON_WIDTH, ICON_HEIGHT); 
              
        final JLabel label = new JLabel(icon);
        frame.setLayout(new FlowLayout());
        frame.add(label);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        final int DELAY = 5;
        // Milliseconds between timer ticks
        Timer t = new Timer(DELAY, new
                ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {

                        for(int i = 0; i < carList.size(); i++){
                            //if i is even, it translate by 2
                            if(i % 2 == 0 && i != 0) {
                                carList.get(i).translate(2,0); }
                            //else it translates by 1
                            else {
                                carList.get(i).translate(1,0); 
                            }
                            
                            //if car's x is equal to icon width, reset back to beginning of screen
                            if(carList.get(i).getX() == ICON_WIDTH) {
                                carList.get(i).setX(-CAR_WIDTH*(i+1));
                            }

                            label.repaint();

                        }
                    }

                });
        t.start();

     //   MoveableShape test = new MoveableShape();
    }
}//end of class        
