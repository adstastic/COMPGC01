import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.*;
import java.nio.file.Paths;

public class XMLProcesser extends JFrame {
	
	private final static Logger L = Logger.getLogger(XMLProcesser.class.getName());
	private JTextArea errorLog;
	private Document xmlDoc;
	
	public static void main(String[] args) {
		L.setLevel(Level.INFO);
		XMLProcesser xmlp = new XMLProcesser();
	}
	
	public XMLProcesser() {
		L.info("Building GUI");
		setSize(500,500);
		setTitle("XML Processer");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		JPanel controls = new JPanel(new GridLayout(1,2));
		controls.setBorder(new EmptyBorder(10,10,10,10));
		JButton verify = new JButton("Verify");
		JButton save = new JButton("Save");
		
		JFileChooser fileChooser = new JFileChooser(Paths.get("").toAbsolutePath().toString());
//		fileChooser.setSize(100, 50);
		
		JTextArea preview = new JTextArea();
		JScrollPane scrollPreview = new JScrollPane(preview);
		errorLog = new JTextArea(5,2);
		
		JScrollPane scrollError = new JScrollPane(errorLog);
		
		Border border = BorderFactory.createLineBorder(Color.RED, 5);
		errorLog.setBorder(border);
		
		
//		add(fileChooser);
		controls.add(verify);
		controls.add(save);
		add(controls, BorderLayout.NORTH);
		add(scrollPreview, BorderLayout.CENTER);
		add(scrollError, BorderLayout.SOUTH);
				
		setVisible(true);
		
//		File f = new File("./Books.xml");
//		xmlDoc = verify(f);				
//		L.info("Printing XML Document to preview window");
//		String xmlStr = parse(xmlDoc);
////		preview.setText(xmlStr);
//		System.out.println(xmlStr);

		
		int status = fileChooser.showOpenDialog(this);
		if (status == JFileChooser.APPROVE_OPTION) {
			taLog("File selected. Click \'Verify\' to parse.");
			File f = fileChooser.getSelectedFile();
			L.info("File selected by user: "+f.getPath());			
			verify.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					L.info("Verify button clicked");
					xmlDoc = verify(f);				
					L.info("Printing XML Document to preview window");
					String xmlStr = parse(xmlDoc);
					preview.append(xmlStr);
					System.out.println(xmlStr);
				}
			});
		} else if (status == JFileChooser.CANCEL_OPTION) {
			L.info("File selection cancelled");
		}
		
		preview.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				String xmlEdit;
				try {
//					xmlEdit = e.getDocument().getText(e.getOffset(), e.getDocument().getLength());
					System.out.println(e.getOffset());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				String xmlEdit;
				try {
//					xmlEdit = e.getDocument().getText(e.getOffset(), e.getDocument().getLength());
					System.out.printf("%d %d\n", e.getOffset(), e.getDocument());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				String xmlEdit;
				try {
//					xmlEdit = e.getDocument().getText(e.getOffset(), e.getDocument().getLength());
					System.out.println(e.getOffset());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
	}
	
	
	public String parse(Document xmlDoc) {
		xmlDoc.getDocumentElement().normalize();
		StringBuilder sb = new StringBuilder();
		sb.append("Root Element: "+xmlDoc.getDocumentElement().getNodeName()).append('\n');
		sb.append("Books: \n");
		NodeList nodelist = xmlDoc.getElementsByTagName("book");
		for (int i=0; i<nodelist.getLength(); i++) {
			Node node = nodelist.item(i);
//			sb.append(node.getNodeName()+'\n');
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				NamedNodeMap attrs = element.getAttributes();
				sb.append(attrs.item(0).getNodeName()+" : "+attrs.item(0).getNodeValue()+'\n');
				NodeList childNodeList = node.getChildNodes();
				for (int j=0; j<childNodeList.getLength(); j++) {
					Node n = childNodeList.item(j);
					if (n.getNodeType() == Node.ELEMENT_NODE) {
						Element e = (Element) n;
						sb.append(e.getTagName()+" : "+n.getTextContent()+'\n');
					}
				}
			}
			sb.append('\n');
		}
		return sb.toString();
	}
	
	public Document verify(File xmlDoc) {
		L.info("Verifying input file");
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		try {
			docBuilder = docFactory.newDocumentBuilder();
			L.info("Parsing input file");
			Document doc = docBuilder.parse(xmlDoc);
			return doc;
		} catch (Exception e) {
			L.severe(e.getMessage());
			taLog(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	public void taLog(String s) {
		String existing = errorLog.getText();
		String updated = existing + '\n' + s;
		L.severe(s);
		errorLog.setText(updated);
	}
	
	public String xmlToString() {
		Transformer transformer;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			//initialize StreamResult with File object to save to file
			StreamResult result = new StreamResult(new StringWriter());
			DOMSource source = new DOMSource(xmlDoc);
			transformer.transform(source, result);
			String xmlString = result.getWriter().toString();
			return xmlString;
		} catch (Exception e1) {
			e1.printStackTrace();
			return null;
		}
	}

}
