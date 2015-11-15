import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class Practice extends JFrame implements KeyListener, ActionListener, FocusListener, MouseListener, MouseMotionListener {

	private JLabel numChars;
	private JLabel display;
	private int count = 0;
	
	public static void main(String[] args) {
		Practice p = new Practice();
	}

	public Practice() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,500);
		
		JPanel Pnl_container = new JPanel();
		Pnl_container.setLayout(new BorderLayout()); 
		
		JPanel Pnl_elements = new JPanel();
		Pnl_elements.setLayout(new GridLayout(5,1)); 
		Pnl_elements.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		JTextField input = new JTextField();
		input.addKeyListener(this);
		
		numChars = new JLabel();
		
		JComboBox<String> jcb = new JComboBox<String>();
		jcb.addItem("asd");
		jcb.addItem("fgh");
		jcb.addItem("jkl");
		jcb.addActionListener(this);
		
		JButton focusTarget = new JButton("Focus Here");
		focusTarget.addFocusListener(this);
		display = new JLabel();
		
		JPanel Pnl_dragDrop = new JPanel();
		
		Pnl_elements.add(focusTarget);
		Pnl_elements.add(display);
		Pnl_elements.add(numChars);
		Pnl_elements.add(input);
		Pnl_elements.add(jcb);
		
		JLabel test = new JLabel();
		test.setText("drag & drop");
		Border border = BorderFactory.createLineBorder(Color.BLUE, 5);
		test.setBorder(border);
		test.setPreferredSize(new Dimension(100, 100));
		test.addMouseListener(this);
		test.addMouseMotionListener(this);
		
		Pnl_dragDrop.add(test);
		
		Pnl_container.add(Pnl_elements, BorderLayout.NORTH);
		Pnl_container.add(Pnl_dragDrop, BorderLayout.CENTER);
		
		add(Pnl_container);
		
		setVisible(true);
		
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		displayInfo(e, "KEY TYPED: ");
		numChars.setText(Integer.toString(count++));
	}

	@Override
	public void keyPressed(KeyEvent e) {
//		displayInfo(e, "KEY TYPED: ");
	}

	@Override
	public void keyReleased(KeyEvent e) {
//		displayInfo(e, "KEY TYPED: ");
	}

	private void displayInfo(KeyEvent e, String keyStatus){
        
        //You should only rely on the key char if the event
        //is a key typed event.
        int id = e.getID();
        String keyString;
        if (id == KeyEvent.KEY_TYPED) {
            char c = e.getKeyChar();
            keyString = "key character = '" + c + "'";
        } else {
            int keyCode = e.getKeyCode();
            keyString = "key code = " + keyCode
                    + " ("
                    + KeyEvent.getKeyText(keyCode)
                    + ")";
        }
        System.out.println(keyString);
	}
        
	public void actionPerformed(ActionEvent e) {
		JComboBox<String> jcb = (JComboBox<String>) e.getSource();
		System.out.println(jcb.getSelectedItem());
		JOptionPane.showMessageDialog(this, jcb.getSelectedItem());
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		display.setText("IN FOCUS");
		System.out.println(e.getSource());
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		display.setText("OUT OF FOCUS");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel s = (JLabel) e.getSource();
		System.out.println(e.getPoint());
		s.setLocation(e.getPoint());
		s.repaint();
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
