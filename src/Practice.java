import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import java.security.cert.X509CRLEntry;

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
	public JLabel coords;
	public JPanel Pnl_dragDrop;
	private int count = 0;
	
	public static void main(String[] args) {
		Practice p = new Practice();
	}

	public Practice() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(550,500);
		
		JPanel Pnl_container = new JPanel();
		Pnl_container.setLayout(new BorderLayout()); 
		
		JPanel Pnl_elements = new JPanel();
		Pnl_elements.setLayout(new GridLayout(6,1)); 
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
		
		coords = new JLabel();
		Pnl_dragDrop = new JPanel();
		Pnl_dragDrop.setPreferredSize(new Dimension(380,280));
		Border border = BorderFactory.createLineBorder(Color.BLUE, 5);
		Pnl_dragDrop.setBorder(border);
		
		Pnl_elements.add(focusTarget);
		Pnl_elements.add(display);
		Pnl_elements.add(numChars);
		Pnl_elements.add(input);
		Pnl_elements.add(jcb);
		Pnl_elements.add(coords);
		
		JLabel test = new JLabel();
		test.setText("Drag & Drop");
		test.setBorder(border);
		test.setPreferredSize(new Dimension(100, 50));
		test.setLocation(0, 0);
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
		numChars.setText("Number of characters in box below: "+Integer.toString(count++));
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
		JOptionPane.showMessageDialog(this, "Selected item: "+jcb.getSelectedItem());
	}
	
	private String FOCUS_PREFIX = "The above button is ";
	
	@Override
	public void focusGained(FocusEvent e) {
		display.setText(FOCUS_PREFIX+"IN FOCUS");
	}

	@Override
	public void focusLost(FocusEvent e) {
		display.setText(FOCUS_PREFIX+"OUT OF FOCUS");
	}

	private int x;
	private int y;
	
	@Override
	public void mouseDragged(MouseEvent e) {
//		coords.setText(x+" "+y);
		JLabel s = (JLabel) e.getSource();
		s.setLocation(e.getPoint());
		s.repaint();
		System.out.println("DRAG "+e.getPoint().toString());
//		coords.setText("Name: "+s.getClass()+" Coordinates: "+s.getLocation().toString());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		JLabel s = (JLabel) e.getSource();
		coords.setText("Name: "+s.getClass()+" Coordinates: "+s.getLocation().toString());
		s.setLocation(e.getPoint());
		System.out.println("RELEASED "+e.getPoint());
		s.repaint();
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("PRESSED "+e.getPoint());
	}


	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
