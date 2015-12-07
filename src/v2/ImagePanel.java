package v2;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	private BufferedImage img;
	private String path;
	double scale;
	
	public ImagePanel(String path) {
		this.img = PatientDataHandler.getImage(path);
		this.path = path;
		scale = (double) img.getHeight() / (double) img.getWidth();
		addListener();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		int newHeight = (int) (this.getWidth()*this.scale);
		g.drawImage(img, 0, 0, this.getWidth(), newHeight, null);
	}
	
	protected Image getImage() {
		return this.img;
	}
	
	protected String getPath() {
		return this.path;
	}
	
	
	protected void setImage(String path) {
		this.img = PatientDataHandler.getImage(path);
		this.path = path;
		this.repaint();
	}
	
	protected void clear() {
		this.img = null;
		this.repaint();
	}
	
	private void addListener() {
		this.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {				
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				repaint();
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
			}
		});
	}
	
}
