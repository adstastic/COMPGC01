package Tutorial_4;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;

public class CurrencyConverter {

	protected Shell shell;
	private Text text;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CurrencyConverter window = new CurrencyConverter();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(210, 450);
		shell.setText("SWT Application");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(45, 40, 117, 19);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(45, 15, 115, 19);
		lblNewLabel.setText("Amount");
		
		Combo combo = new Combo(shell, SWT.NONE);
		combo.setBounds(45, 114, 135, 22);
		
		Label lblCurrency = new Label(shell, SWT.NONE);
		lblCurrency.setAlignment(SWT.CENTER);
		lblCurrency.setText("Currency");
		lblCurrency.setBounds(43, 89, 117, 19);

	}
}
