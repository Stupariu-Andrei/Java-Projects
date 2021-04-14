package gui;


import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.text.StyledEditorKit.ForegroundAction;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.helpers.DefaultHandler;

import procesare.Profesor;
import parsare.ReadProfesori;
import parsare.WriteXML;

import javax.swing.table.*;
@SuppressWarnings("serial")
public class MainForm extends JFrame {
	private List<Profesor> profesori;
	private List<Profesor> profesoriS;
	private List<Profesor> profesoriFacultate;
	private Profesor profesor;

	private JTable tabelProfesori;
	private JTable tabelSalarii;
	private JTable tabelProfesoriACE;
	
	private JButton button = new JButton();
	private JPanel panel = new JPanel();
	  
	public MainForm() {
		super("Tabel profesori");
		
		
		panel.setLayout(null);
		panel.setBackground(Color.CYAN);
		
		
		
		
		
		button.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int i = fc.showOpenDialog(MainForm.this);
				if (i == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					String filepath = f.getPath();
					if(!filepath.endsWith("Profesori.xml")) {
						JOptionPane.showMessageDialog(MainForm.this, "Nu s-a ales fisierul studentilor");
					} else {
						profesori = (List<Profesor>)MainForm.this.parseXML(filepath);
						EditareProfesori model = new EditareProfesori();
						model.setProfesori(profesori);
						tabelProfesori.setModel(model);

						
					}
				}

			}
		});
		button.setText("Alege Profesori.xml");
		button.setBounds(100, 10, 300, 20);
		tabelProfesori = new JTable();
		panel.add(button);
		add(panel);
		
		
		
		
		
//		tabel.setEditingColumn(5);
		JScrollPane sp = new JScrollPane(tabelProfesori);
		sp.setBounds(200, 70,800, 170);
		panel.add(sp);

		
		
		setLocationRelativeTo(null);

		
		
		button = new JButton();
		button.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				profesoriS= new ArrayList();
				for (int i = 0; i<profesori.size();i++){
					int profSalariiMici = Integer.parseInt(profesori.get(i).getSalariu());
					
					if(profSalariiMici>=2000){
						profesoriS.add(profesori.get(i));
					}	
				}
				EditareProfesori model = new EditareProfesori();
				model.setProfesori(profesoriS);
				tabelSalarii.setModel(model);
			}
		});
		button.setText("Arata profesori cu salarii mai mici de 2000");
		button.setBounds(100, 300, 300, 20);
		
		
		tabelSalarii = new JTable();
		panel.add(button);
		add(panel);
		
		sp = new JScrollPane(tabelSalarii);
		sp.setBounds(200, 350, 800, 170);
		panel.add(sp);
		
		
		button = new JButton();
		button.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				profesoriFacultate = new ArrayList();
				for (int i = 0; i<profesori.size();i++){
					if(profesori.get(i).getFacultate().equals("ACE"))
						profesoriFacultate.add(profesori.get(i));
				}
				EditareProfesori model = new EditareProfesori();
				model.setProfesori(profesoriFacultate);
				tabelProfesoriACE.setModel(model);
			}
		});
		button.setText("Arata profesori de la ACE");
		button.setBounds(100, 580, 300, 20);
		
		
		tabelProfesoriACE = new JTable();
		panel.add(button);
		add(panel);
		
		sp = new JScrollPane(tabelProfesoriACE);
		sp.setBounds(200, 640, 800, 170);
		panel.add(sp);
		
		
		JButton btnSave = new JButton();
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				WriteXML domWriter = new WriteXML();
				try {
					domWriter.createXMLFrom(profesori, "oProfesori.xml");
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnSave.setText("Save to XML");
		btnSave.setBounds(800, 620, 150, 20);
		panel.add(btnSave);

		setSize(850, 800);
		setLocationRelativeTo(null);

		setVisible(true);
		
		
		
		
		setVisible(true);
		setSize(1300, 800);
		
		
		
	}
	
	public Object parseXML(String file)
	{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser;
		try {
			parser = factory.newSAXParser();
			ReadProfesori saxReader = new ReadProfesori();
			parser.parse(file, saxReader);
			return saxReader.getProfesori();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 	
	}	
		

		
	
}

