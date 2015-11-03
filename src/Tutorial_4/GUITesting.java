package Tutorial_4;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class GUITesting extends JFrame implements ActionListener {
	
	JPanel p;
	JButton b;
	JLabel l;
	
	public GUITesting() {
		p = new JPanel();
		b = new JButton("press me!");
		l = new JLabel("Hello World");
		
		b.addActionListener(this);
		
		p.add(b);
		p.add(l);
		
		add(p);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GUITesting gt = new GUITesting();
		
		gt.setSize(200, 200);
		gt.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Button clicked");
	}
}
