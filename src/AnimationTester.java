import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.Timer;

/**
This program implements an animation that moves
a car shape. User enters how many cars will appear on screen.
 */
public class AnimationTester
{
    private static final int CAR_WIDTH = 100; // car's width
    private static int length; // length of screen (how much space all cars will take to adjust screen accordingly)

    /**
     * Main method - starts program. Asks user how many cars should be shown.
     * Window is scrollable and all cars are shown.
     *
     * @param args
     */
    public static void main(String[] args)
    {
        int height = 720; // height of window
        int width = 720; // width of window

        JFrame frame = new JFrame(); // frame/window

        Scanner scan = new Scanner(System.in); // scanner used in reading input from user

        Random rand = new Random(255); // generates random number from 0 to 255

        ArrayList<CarShape> carList = new ArrayList<CarShape>(); // ArrayList of car shapes

        // Asks how many cars will be shown on screen (valid:  all positive numbers (#'s>=0)
        int count = -1; // to check if input from user is valid/invalid
        try {
            count = Integer.parseInt(JOptionPane.showInputDialog(frame, "How many cars would you like shown?", null));
            // if number is negative throw exception
            if(count < 0) {
                throw new NumberFormatException();
            }
        }
        catch (NumberFormatException e) {
            Component panel = null;
            JOptionPane.showMessageDialog(panel, "Error: Input was not valid integer", "Error", JOptionPane.ERROR_MESSAGE);
            count = -1;
        }

        if(count != -1) {
            Color color; // color of each car

            // assign a color to each car and creates a car (restricted to how many cars entered by user)
            int num = 0;
            for (int i = 0; i < count; i++) {
                color = new Color(rand.nextFloat(), rand.nextFloat(),
                        rand.nextFloat());
                if (i % 10 != 0) {
                    carList.add(new CarShape(num * (CAR_WIDTH / 2) + 10,
                            i * (CAR_WIDTH / 2) + 10, CAR_WIDTH, color));
                    num++;
                } else {
                    num = 0;
                }
                length = i * (CAR_WIDTH / 2) + 10;
            }

            ShapeIcon icon = new ShapeIcon(carList,
                    width, height);

            final JLabel label = new JLabel(icon);
            label.setPreferredSize(new Dimension(width, length));

            JScrollPane scrPane = new JScrollPane(label);
            scrPane.setPreferredSize(new Dimension(width, height));
            final int DELAY = 5;
            // Milliseconds between timer ticks
            Timer t = new Timer(DELAY, new
                    ActionListener() {
                        public void actionPerformed(ActionEvent event) {

                            for (int i = 0; i < carList.size(); i++) {
                                //if i is even, it translate by 2
                                if (i % 2 == 0 && i != 0) {
                                    carList.get(i).translate(2, 0);
                                }
                                //else it translates by 1
                                else {
                                    carList.get(i).translate(1, 0);
                                }

                                //if car's x is equal to icon width, reset back to beginning of screen
                                if (carList.get(i).getX() == width) {
                                    carList.get(i).setX(-CAR_WIDTH * (i + 1));
                                }

                                label.repaint();

                            }
                        }

                    });
            t.start();

            frame.setLayout(new FlowLayout());
            frame.setResizable(false);
            frame.add(scrPane);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
            //   MoveableShape test = new MoveableShape();
        }
    }

    /**
     * Returns length of screen.
     * @return length - Integer
     */
    public static int getLength() {
        return length;
    }

}//end of class        
