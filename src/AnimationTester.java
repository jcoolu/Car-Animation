import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import javax.swing.Timer;

/**
This program implements an animation that moves
a car shape. User enters how many cars will appear on screen
 */
public class AnimationTester
{
    private static int width = 720;
    private static int height = 720;
    private static final int CAR_WIDTH = 100;
    private static BufferedImage image;
    private static int length;

    public static void main(String[] args)
    {
        JFrame frame = new JFrame(); // frame/window

        Scanner scan = new Scanner(System.in); // scanner used in reading input from user

        Random rand = new Random(255); // generates random number from 0 to 255

        ArrayList<CarShape> carList = new ArrayList<CarShape>(); // ArrayList of car shapes
        System.out.println("How many cars would you like shown?");
        int count = -1;
        try {
            count = Integer.parseInt(JOptionPane.showInputDialog(frame, "How many cars would you like shown?", null));
            if(count <0) {
                throw new NumberFormatException();
            }
        }
        catch (NumberFormatException e) {
            Component panel = null;
            JOptionPane.showMessageDialog(panel, "Error: Input was not valid integer", "Error", JOptionPane.ERROR_MESSAGE);
            count = -1;
        }

        if(count != -1) {
            Color color;//color of each car

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

            URL rs = AnimationTester.class.getResource("road.png");
            try {
                image = ImageIO.read(rs);
            }
            catch (IOException e) {
                e.printStackTrace();
            }


            ShapeIcon icon = new ShapeIcon(carList,
                    width, height);

            final JLabel label = new JLabel(icon);
            label.setPreferredSize(new Dimension(width, length));
// Now, you can add whatever you want to the container
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

            System.out.println(length);
            frame.setLayout(new FlowLayout());
            frame.setResizable(false);
            frame.add(scrPane);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
            //   MoveableShape test = new MoveableShape();
        }
    }

    public static int getLength() {
        return length;
    }
/*
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 720, 720, this);
    }
*/
}//end of class        
