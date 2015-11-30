import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

public class Circle extends JPanel {
	int x;
	int y;
	int r;

	public Circle() {
		this.x = 200;
		this.y = 200;
		this.r = 50;
	}
	
	public Circle(int x, int y, int r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		Ellipse2D circle = new Ellipse2D.Double();
		circle.setFrameFromCenter(this.x, this.y, this.x+this.r, this.y+this.r);
		
		g2d.draw(circle);
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getR() {
		return this.r;
	}

}
