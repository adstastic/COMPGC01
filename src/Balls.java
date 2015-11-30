import java.awt.*;
import java.util.Formatter;
import javax.swing.*;

// Extends JPanel, so as to override the paintComponent() for custom rendering codes. 
public class Balls extends JPanel {
   // Container box's width and height
   private static final int BOX_WIDTH = 700;
   private static final int BOX_HEIGHT = 700;
  
   // Ball's properties
   private float r = 50; // Ball's radius
   private float x1 = r + 20; // Ball's center (x, y)
   private float y1 = r + 20; 
   private float v1x = 3;   // Ball's speed for x and y
   private float v1y = 2;
   
   private float x2 = r + 50; // Ball's center (x, y)
   private float y2 = r + 50; 
   private float v2x = 3;   // Ball's speed for x and y
   private float v2y = 0;
   
   private float x3 = r + 100; // Ball's center (x, y)
   private float y3 = r + 100; 
   private float v3x = 0;   // Ball's speed for x and y
   private float v3y = 2;
  
   private static final int UPDATE_RATE = 30; // Number of refresh per second
  
   /** Constructor to create the UI components and init game objects. */
   public Balls() {
      this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
  
      // Start the ball bouncing (in its own thread)
      Thread gameThread = new Thread() {
         public void run() {
            while (true) { // Execute one update step
               // Calculate the ball's new position
               x1 += v1x;
               y1 += v1y;
               // Check if the ball moves over the bounds
               // If so, adjust the position and speed.
               if (x1 - r < 0) {
                  v1x = -v1x; // Reflect along normal
                  x1 = r; // Re-position the ball at the edge
               } else if (x1 + r > BOX_WIDTH) {
                  v1x = -v1x;
                  x1 = BOX_WIDTH - r;
               }
               // May cross both x and y bounds
               if (y1 - r < 0) {
                  v1y = -v1y;
                  y1 = r;
               } else if (y1 + r > BOX_HEIGHT) {
                  v1y = -v1y;
                  y1 = BOX_HEIGHT - r;
               }
               // Refresh the display
               repaint(); // Callback paintComponent()
               // Delay for timing control and give other threads a chance
               try {
                  Thread.sleep(1000 / UPDATE_RATE);  // milliseconds
               } catch (InterruptedException ex) { }
            }
         }
      };
         
         Thread gameThread2 = new Thread() {
             public void run() {
                while (true) { // Execute one update step
                   // Calculate the ball's new position
                   x2 += v2x;
                   y2 += v2y;
                   // Check if the ball moves over the bounds
                   // If so, adjust the position and speed.
                   if (x2 - r < 0) {
                      v2x = -v2x; // Reflect along normal
                      x2 = r; // Re-position the ball at the edge
                   } else if (x2 + r > BOX_WIDTH) {
                      v2x = -v2x;
                      x2 = BOX_WIDTH - r;
                   }
                   // May cross both x and y bounds
                   if (y2 - r < 0) {
                      v2y = -v2y;
                      y2 = r;
                   } else if (y2 + r > BOX_HEIGHT) {
                      v2y = -v2y;
                      y2 = BOX_HEIGHT - r;
                   }
                   // Refresh the display
                   repaint(); // Callback paintComponent()
                   // Delay for timing control and give other threads a chance
                   try {
                      Thread.sleep(1000 / UPDATE_RATE);  // milliseconds
                   } catch (InterruptedException ex) { }
                }
             }
         };
             
             Thread gameThread3 = new Thread() {
                 public void run() {
                    while (true) { // Execute one update step
                       // Calculate the ball's new position
                       x3 += v3x;
                       y3 += v3y;
                       // Check if the ball moves over the bounds
                       // If so, adjust the position and speed.
                       if (x3 - r < 0) {
                          v3x = -v3x; // Reflect along normal
                          x3 = r; // Re-position the ball at the edge
                       } else if (x3 + r > BOX_WIDTH) {
                          v3x = -v3x;
                          x3 = BOX_WIDTH - r;
                       }
                       // May cross both x and y bounds
                       if (y3 - r < 0) {
                          v3y = -v3y;
                          y3 = r;
                       } else if (y3 + r > BOX_HEIGHT) {
                          v3y = -v3y;
                          y3 = BOX_HEIGHT - r;
                       }
                       // Refresh the display
                       repaint(); // Callback paintComponent()
                       // Delay for timing control and give other threads a chance
                       try {
                          Thread.sleep(1000 / UPDATE_RATE);  // milliseconds
                       } catch (InterruptedException ex) { }
                    }
                 }
             };
             
             gameThread.start(); 
             gameThread2.start();
             gameThread3.start();// Callback run()
      }

  
   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);    // Paint background
  
      // Draw the box
      g.setColor(Color.BLACK);
      g.fillRect(0, 0, BOX_WIDTH, BOX_HEIGHT);
  
      // Draw the ball
      g.setColor(Color.RED);
      g.fillOval((int) (x1 - r), (int) (y1 - r),
            (int)(2 * r), (int)(2 * r));
      
      g.fillOval((int) (x2 - r), (int) (y2 - r),
              (int)(2 * r), (int)(2 * r));
      
      g.fillOval((int) (x3 - r), (int) (y3 - r),
              (int)(2 * r), (int)(2 * r));
  
      // Display the ball's information
      g.setColor(Color.WHITE);
      g.setFont(new Font("Courier New", Font.PLAIN, 12));
      StringBuilder sb = new StringBuilder();
      Formatter formatter = new Formatter(sb);
      formatter.format("Ball (%3.0f,%3.0f) Speed=(%2.0f,%2.0f)\n", x1, y1, v1x, v1y);
      formatter.format("Ball (%3.0f,%3.0f) Speed=(%2.0f,%2.0f)\n", x2, y2, v2x, v2y);
      formatter.format("Ball (%3.0f,%3.0f) Speed=(%2.0f,%2.0f)", x3, y3, v3x, v3y);
      g.drawString(sb.toString(), 20, 30);
   }
  
   public static void main(String[] args) {
      // Run GUI in the Event Dispatcher Thread (EDT) instead of main thread.
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            // Set up main window (using Swing's Jframe)
            JFrame frame = new JFrame("Bouncing balls");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new Balls());
            frame.pack();
            frame.setVisible(true);
         }
      });
   }
}