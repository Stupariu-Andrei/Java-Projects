package gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
import javax.swing.text.StyledEditorKit.ForegroundAction;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import parsare.ReadMaterieXMLFile;
import parsare.ReadNotaXMLFile;
import parsare.ReadStudentXMLFile;
import parsare.WriteXMLFile;
import procesare.Catalog;
import procesare.Materie;
import procesare.Nota;
import procesare.Situatie;
import procesare.Student;

@SuppressWarnings("serial")
public class MainForm extends JFrame {
	private List<Student> studenti;
	private List<Materie> materii;
	private List<Nota> note;
	private Catalog catalog;
	private JTable tabelStudenti;
	private JTable tabelMaterii;
	private JTable tabelCatalog;
	
	  
	public MainForm() {
		super("Catalog Studenti");
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.CYAN);
		
		
		
		
		
		JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int i = fc.showOpenDialog(MainForm.this);
				if (i == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					String filepath = f.getPath();
					if(!filepath.endsWith("Student.xml")) {
						JOptionPane.showMessageDialog(MainForm.this, "Nu s-a ales fisierul studentilor");
					} else {
						studenti = (List<Student>)MainForm.this.parseXML(0, filepath);
						EditareDataModel model = new EditareDataModel();
						model.setStudenti(studenti);
						tabelStudenti.setModel(model);
					}
				}

			}
		});
		button.setText("Alege Student.xml");
		button.setBounds(10, 10, 150, 20);
		panel.add(button);
		add(panel);

		
		button = new JButton();
		button.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int i = fc.showOpenDialog(MainForm.this);
				if (i == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					String filepath = f.getPath();
					if(!filepath.endsWith("Materie.xml")) {
						JOptionPane.showMessageDialog(MainForm.this, "Nu s-a ales fisierul materiilor");
					} else {
						materii = (List<Materie>)MainForm.this.parseXML(1, filepath);
						EditareDataModel model = new EditareDataModel();
						model.setMaterii(materii);
						tabelMaterii.setModel(model);
					}
				}

			}
		});
		button.setText("Alege Materie.xml");
		button.setBounds(10, 200, 150, 20);
		panel.add(button);
		add(panel);
		
		button = new JButton();
		button.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int i = fc.showOpenDialog(MainForm.this);
				if (i == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					String filepath = f.getPath();
					if(!filepath.endsWith("Nota.xml")) {
						JOptionPane.showMessageDialog(MainForm.this, "Nu s-a ales fisierul notelor");
					} else {
						note = (List<Nota>)MainForm.this.parseXML(2, filepath);
						if(note != null && materii != null && studenti != null) {
							catalog = new Catalog();
							catalog.setMaterii(materii);
							for(Student stud : studenti) {
								Situatie situatie = new Situatie();
								situatie.setStudent(stud);
								for(Nota nota : note) {
									if(nota.getIdStudent() == stud.getId()) {
										situatie.addNota(nota);
									}
								}
								catalog.addSituatie(situatie);
								CatalogDataModel model = new CatalogDataModel(catalog);
								tabelCatalog.setModel(model);
							}
						}
					}
				}

			}
		});
		button.setText("Alege Nota.xml");
		button.setBounds(10, 400, 150, 20);
		panel.add(button);
		add(panel);
		
		tabelStudenti = new JTable();
//		tabel.setEditingColumn(5);
		
		JScrollPane sp = new JScrollPane(tabelStudenti);
		sp.setBounds(200, 10,500, 170);
		panel.add(sp);
		


		tabelMaterii = new JTable();
//		tabel.setEditingColumn(5);

		sp = new JScrollPane(tabelMaterii);
		sp.setBounds(200, 200, 500, 170);
		
		panel.add(sp);

		tabelCatalog = new JTable();

		sp = new JScrollPane(tabelCatalog);
		sp.setBounds(200, 400, 500, 200);
		panel.add(sp);

		JButton btnSave = new JButton();
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				WriteXMLFile domWriter = new WriteXMLFile();
				try {
					domWriter.createXMLFrom(catalog, "oCatalog.xml");
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnSave.setText("Save to XML");
		btnSave.setBounds(200, 620, 150, 20);
		panel.add(btnSave);

		setSize(850, 800);
		setLocationRelativeTo(null);

		setVisible(true);
	}
	

	
	public Object parseXML(int tip, String file)
	{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser;
		try {
			parser = factory.newSAXParser();
			if(tip == 0) {
				ReadStudentXMLFile saxReader = new ReadStudentXMLFile();
				parser.parse(file, saxReader);
				return saxReader.getStudenti();
			} else if(tip == 1) {
				ReadMaterieXMLFile saxReader = new ReadMaterieXMLFile();
				parser.parse(file, saxReader);
				return saxReader.getMaterii();
			} else if(tip == 2) {
				ReadNotaXMLFile saxReader = new ReadNotaXMLFile();
				parser.parse(file, saxReader);
				return saxReader.getNote();
			} 
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 	
	}
}
