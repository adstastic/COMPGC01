package Tutorial_4;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WhackAMole extends JFrame implements ActionListener {

	public static void main(String[] args) {
		WhackAMole game = new WhackAMole();
		
		JFrame frame = new JFrame("Whack a mole!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(500,500, 500, 500);
        frame.setVisible(true);
	}
	
	public WhackAMole() {
		JPanel board = new JPanel(); 
		board.setLayout(new GridLayout(4,4,5,5)); 
		JButton[] moles = new JButton[16]; 
		for(int i=0; i<moles.length; i++) {
			moles[i] = new JButton();
			moles[i].addActionListener(this); 
			board.add(moles[i]);
		}
		setContentPane(board);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}


}
