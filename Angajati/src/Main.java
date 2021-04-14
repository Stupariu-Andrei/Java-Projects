import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Main extends JFrame{

	private AngajatiTableModel angajati;
	private JTable jt;
	EditCellRender editCell;
	
	public Main() {
		super("Parsare si prelucrare fisier XML");
	    JPanel panel = new JPanel();  
	    panel.setLayout(new FlowLayout());  
	    JLabel label = new JLabel("Alege Fisierul XML");  
	    JButton button = new JButton();  
	    button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				  JFileChooser fc= new JFileChooser();    
				  int i = fc.showOpenDialog(Main.this);    
				  if(i == JFileChooser.APPROVE_OPTION){    
				        File f = fc.getSelectedFile();    
				        String filepath = f.getPath();    
				        Main.this.parseXML(filepath);
				  }    
				
			}
		});
	    button.setText("Select");  
	    panel.add(label);  
	    panel.add(button);  
	    add(panel);  
	    
	    jt = new JTable();    
	    jt.setEditingColumn(5);
	    jt.setBounds(30,40,200,300);          
	    JScrollPane sp = new JScrollPane(jt);    
	    
	    panel.add(sp);          
	    AngajatCellRender render = new AngajatCellRender();
	    jt.setDefaultRenderer(String.class, render);
	    jt.setDefaultRenderer(Integer.class, render);
	    
	    editCell = new EditCellRender();
	    jt.setDefaultRenderer(JButton.class, editCell);
	    jt.setDefaultEditor(JButton.class, editCell);
	    editCell.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.this.editRow(jt.getEditingRow());
			}
		});
	    
	    jt.setCellSelectionEnabled(true);
	    JButton btnSave = new JButton();  
	    btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				WriteDomXML domWriter = new WriteDomXML();
				try {
					domWriter.createXMLFrom(angajati.getAngajati(), "oAngajati.xml");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 				
			}
		});
	    btnSave.setText("Save to XML");  
	    panel.add(btnSave);  
	    setSize(300, 300);  
	    setLocationRelativeTo(null);  
 
	    setVisible(true);  
	}
	
	public void editRow(int index) {
		Angajat obj = angajati.getAngajati().get(index);
		EditareAngajatForm eaf = new EditareAngajatForm(obj);
		eaf.setVisible(true);
		eaf.pack();
	}
	
	public void parseXML(String file)
	{
		ReadSaxXML saxReader = new ReadSaxXML();
		
		// TODO Auto-generated method stub
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser;
		try {
			parser = factory.newSAXParser();
			parser.parse(file, saxReader);
			angajati = new AngajatiTableModel(saxReader.getAngajati());
			jt.setModel(angajati);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
	}
	
	AngajatiTableModel getModel()
	{
		return angajati;
	}
	
	public static void main(String[] args) {
		
		Main fereastra = new Main();
		
		
		
	}

}
