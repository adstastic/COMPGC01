import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.*;

public class Circles extends JFrame {

    public ArrayList<Circle> circles = new ArrayList<Circle>();
    
    public static void main(String args[]) {
    	Circles c = new Circles();
    	c.add(new Circle(100,100,10));
    	c.add(new Circle(200,200,10));
    	c.add(new Circle(300,300,10));
    	
    	repaint();
    	
    	c.setTitle("Circles");
    	c.setSize(500,500);
    	c.setVisible(true);
    	c.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void add(Circle circle) {
        circles.add(circle);
    }

    public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g);
		for(Ball b:balls){
			g2d.setColor(b.getColor());
			g2d.fillOval((int)(b.getPosX()-b.getRadius()), (int)(b.getPosY()-b.getRadius()),(int) b.getRadius()*2, (int)b.getRadius()*2);
		}
		moveballs();
		collisionWithWall();
		coordinates();
	}
}