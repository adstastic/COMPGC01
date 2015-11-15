import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class WhackAMole extends JFrame {
	
	static JLabel Lbl_score;
	static JLabel Lbl_moles;
	static JLabel Lbl_time;
	static JLabel Lbl_level;
	static String score_text = "Score: ";
	static String moles_text = "Moles: ";
	static String time_text = "Time: ";
	static String level_text = "Level: ";
	private JPanel Pnl_board;
	private static int board_size = 16;
	private static Random random = new Random();
	private int score = 0;
	private int moles = 0;
	private int total = 12;
	private int difficulty = 2;
	private int round_length = 12000;
	private int rand_int = -1; // Starting value negative so first randomisation can be checked
	private JButton[] Arr_Btn_empty = new JButton[board_size];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WhackAMole game = new WhackAMole();
		while (true) {
			int i = 1;
			while (i<game.total) {
				Lbl_level.setText(level_text + i);
				JOptionPane.showMessageDialog(game, "Level "+i);
				game.play(i);
				if (result(game.moles, game.score, i, game.difficulty)) {
					i++;
					JOptionPane.showMessageDialog(game, "Success! Continue to level "+i+" :)");
				} else {
					JOptionPane.showMessageDialog(game, "Failure! Game over on level "+i+" :(");
					game.dispose();
					System.exit(0);
				}
			}			
			JOptionPane.showMessageDialog(game, "Game over! You win :)"); 
			game.dispose();
			System.exit(0);
		}
	}

	public WhackAMole() {
		setSize(500,500);
		// Adding JPanels to container in case want to do border stuff later
		JPanel Pnl_container = new JPanel();
		Pnl_container.setLayout(new BorderLayout()); 
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Whack A Mole!"); 
		
		JPanel Pnl_menu = new JPanel();
		Pnl_menu.setLayout(new GridLayout(1,4));
		Lbl_score = new JLabel(score_text+score);
		Lbl_time = new JLabel(time_text);
		Lbl_level = new JLabel(level_text);
		Lbl_moles = new JLabel(moles_text+moles);
		Pnl_menu.add(Lbl_level);
		Pnl_menu.add(Lbl_moles);
		Pnl_menu.add(Lbl_score);
		Pnl_menu.add(Lbl_time);
		Pnl_menu.setBorder(new EmptyBorder(20, 20, 20, 20));
		

		Pnl_board = new JPanel();
		Pnl_board.setLayout(new GridLayout(4,4,5,5));		
		for (int i=0; i<board_size; i++) {
			Arr_Btn_empty[i] = new JButton();
			Pnl_board.add(Arr_Btn_empty[i]);
		}
		Pnl_board.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		Pnl_container.add(Pnl_menu, BorderLayout.NORTH);
		Pnl_container.add(Pnl_board, BorderLayout.CENTER);
				
		add(Pnl_container);
		this.setVisible(true);
	}
	
	public JButton Mole() {
		JButton mole = new JButton();
		mole.setText("Mole");
		mole.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					mole.setEnabled(false);
					score += 1;
					Lbl_score.setText(score_text + score);
				};
			}
		});
		return mole;
	}
	
	public void randomise_board() {
		Pnl_board.removeAll();
		rand_int = random.nextInt(board_size-1);
		for (int i=0; i<board_size; i++) {
			if (i == rand_int) { 
				Arr_Btn_empty[i] = Mole();
				Pnl_board.add(Arr_Btn_empty[i]);
			} else {
				Arr_Btn_empty[i] = new JButton();
				Pnl_board.add(Arr_Btn_empty[i]);
			}
		}
		Lbl_moles.setText(moles_text+moles);
	}
	
	public void play(int level) {
		long start = System.currentTimeMillis();
//		Timer t = new Timer();
//		Thread time = new Thread() {
//			public void run() {
//				int dif = (int) (10-secs_elapsed(start));
//				Lbl_time.setText(time_text+dif);
//				Lbl_time.revalidate();
//				Lbl_time.repaint();
//				try {
//					Thread.sleep(1);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			};
//		};
//		Thread refresh = new Thread() {
//			public void run() {
//				int delay = level(level);
//				randomise_board();
//				Pnl_board.revalidate();
//				Pnl_board.repaint();
//				try {
//					Thread.currentThread().wait(delay);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			};
//		};
		int delay = level(level);
		int i = 1;
		while (elapsed(start) < round_length) {
			double dif = (round_length-elapsed(start))/1000;
			Lbl_time.setText(time_text+dif);
			Lbl_time.repaint();
//			try {
//				
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			if (System.currentTimeMillis()/100 == (start+delay*i)/100) {
				moles++;
				i++;
				randomise_board();
				Pnl_board.revalidate();
				Pnl_board.repaint();
			}
//			t.schedule(new TimerTask() {
//				@Override
//				public void run() {
//					// TODO Auto-generated method stub
//					randomise_board();
//					Pnl_board.revalidate();
//					Pnl_board.repaint();
//				}
//			}, delay);
//			time.run();
//			refresh.run();
		}	
//		t.cancel();
//		t.purge();
//		time.interrupt();
//		refresh.interrupt();
	}
	
	public double elapsed(long start) {
		return (System.currentTimeMillis()-start);
	}
	
	public int level(int i) {
		switch(i) {
		case 1: return 2000; 
		case 2: return 1500; 
		case 3: return 1300; 
		case 4: return 1000;
		case 5: return 900; 
		case 6: return 800; 
		case 7: return 700; 
		case 8: return 600; 
		case 9: return 500; 
		case 10: return 400; 
		case 11: return 6666;
		case 12: return 50;
		case 13: return 10;
		case 14: return 1;
		default: return 1; 
		}
	}
	
	public static boolean result(int moles, int score, int level, int difficulty) {
		double pass = (double) moles * Math.exp(-(double) difficulty / (double) level);
		System.out.println(pass);
		System.out.println((double) score);
		if ((double) score >= pass) {
			return true;
		} else {
			return false;
		}
	}

}
