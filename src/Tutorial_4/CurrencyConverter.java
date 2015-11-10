package Tutorial_4;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionAdapter;


public class CurrencyConverter {

	protected Shell shlCurrencyConverter;
	private Text amount;
	private Text result;
	public ArrayList<Currency> currencies = new ArrayList<Currency>();
	
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
		shlCurrencyConverter.open();
		shlCurrencyConverter.layout();
		while (!shlCurrencyConverter.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	public void currencyBuilder() {
		this.currencies.add(new Currency("GBP", 1.00));
		this.currencies.add(new Currency("EUR", 1.40));
		this.currencies.add(new Currency("USD", 1.52));
		this.currencies.add(new Currency("JPY", 185.48));
		this.currencies.add(new Currency("CNY", 9.67));
	}
	
	public String[] currencyCodes() {
		String[] currencyCodes = new String[this.currencies.size()];
		for (int i=0; i<this.currencies.size(); i++) {
			currencyCodes[i] = this.currencies.get(i).getCode();
		}
		return currencyCodes;
	}
	
	public Double convert(Double amount, String input, String output) {
		double inputRate = 1;
		double outputRate = 0;
		for (Currency c : currencies) {
			if (c.getCode().equals(input)) {
				inputRate = c.getRate();
			} else if (c.getCode().equals(output)) {
				outputRate = c.getRate();
			} 
		}
		double conversionRate = outputRate/inputRate;
		return amount*conversionRate;
	}
	
	public void writeToFile(String text) throws IOException {
		File cc = new File("./cc.txt");
		if (cc.exists()) { 
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cc.txt", true)));
			out.println(text);
			out.close();
		} else {
			PrintWriter out = new PrintWriter("cc.txt", "UTF-8");
			out.println(text);
			out.close();
		}
	}
	
	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		currencyBuilder();
		shlCurrencyConverter = new Shell();
		shlCurrencyConverter.setSize(213, 260);
		shlCurrencyConverter.setText("Currency Converter");
		shlCurrencyConverter.setLayout(new GridLayout(1, false));
		
		Label amountLabel = new Label(shlCurrencyConverter, SWT.NONE);
		amountLabel.setAlignment(SWT.CENTER);
		GridData gd_lblAmount = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblAmount.widthHint = 200;
		amountLabel.setLayoutData(gd_lblAmount);
		amountLabel.setText("Amount");
		
		amount = new Text(shlCurrencyConverter, SWT.BORDER);
		amount.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label inputLabel = new Label(shlCurrencyConverter, SWT.NONE);
		inputLabel.setAlignment(SWT.CENTER);
		GridData gd_lblInput = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblInput.widthHint = 200;
		inputLabel.setLayoutData(gd_lblInput);
		inputLabel.setText("Input Currency");
		
		final Combo inputCurrency = new Combo(shlCurrencyConverter, SWT.NONE);
		inputCurrency.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		inputCurrency.setItems(currencyCodes());
		
		Label outputLabel = new Label(shlCurrencyConverter, SWT.NONE);
		outputLabel.setAlignment(SWT.CENTER);
		GridData gd_lblOutput = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblOutput.widthHint = 200;
		outputLabel.setLayoutData(gd_lblOutput);
		outputLabel.setText("Input Currency");
		
		
		final Combo outputCurrency = new Combo(shlCurrencyConverter, SWT.NONE);
		outputCurrency.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		outputCurrency.setItems(currencyCodes());
		
		Label resulLabel = new Label(shlCurrencyConverter, SWT.NONE);
		resulLabel.setAlignment(SWT.CENTER);
		GridData gd_lblResult = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblResult.widthHint = 200;
		resulLabel.setLayoutData(gd_lblResult);
		resulLabel.setText("Result");
		
		result = new Text(shlCurrencyConverter, SWT.BORDER);
		result.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnConvert = new Button(shlCurrencyConverter, SWT.NONE);
		btnConvert.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent arg0) {
				String amt = amount.getText();
				Double amtD = Double.parseDouble(amt);
				Double res = convert(amtD, inputCurrency.getText(), outputCurrency.getText());
				String resStr = String.format("%.2f", res);
				result.setText(resStr);
				String writeStr = String.format("%.2f %.2f", amtD, res);
				try {
					writeToFile(writeStr);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		GridData gd_btnConvert = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnConvert.heightHint = 35;
		gd_btnConvert.widthHint = 200;
		btnConvert.setLayoutData(gd_btnConvert);
		btnConvert.setText("Convert");

	}
}
