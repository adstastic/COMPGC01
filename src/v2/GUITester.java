package v2;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUITester {

	public static void main(String[] args) {
		String filepath = "./data/patient_test.json";
		UI ui = new UI();
		PatientDataHandler pdh = new PatientDataHandler(filepath);
//		testPatientScreen(pdh, ui);
		ui.start();
	}
	
	protected static void testPatientScreen(PatientDataHandler pdh, UI ui) {
		JFrame f = new JFrame();
		f.setSize(570,770);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		Patient p = pdh.getAtIndex(0);
		JPanel patient = ui.patientPanel(p);
		
		f.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {}
			
			@Override
			public void componentResized(ComponentEvent e) {
//				System.out.println(f.getWidth()+" "+f.getHeight());
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {}
			
			@Override
			public void componentHidden(ComponentEvent e) {}
		});

		f.add(patient);
		f.setVisible(true);
	}
}
