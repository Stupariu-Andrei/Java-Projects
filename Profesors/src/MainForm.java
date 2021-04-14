


import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.util.ArrayList;
import java.util.List;


import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;





import javax.swing.table.*;
@SuppressWarnings("serial")
public class MainForm extends JFrame {
	

	private JTable tabelProfesori;
	private JButton button = new JButton();
	private JPanel panel = new JPanel();
	private int id=0;
	
	public MainForm(List<Profesor> profesori) {
		super("Tabel profesori");
		ConnectionDB db = new ConnectionDB();
		EditareProfesori model = new EditareProfesori();
		Object[] emptRow = new Object[7];
		panel.setLayout(null);
		panel.setBackground(Color.white);
		

		button.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			
			public void actionPerformed(ActionEvent e) {	
				
				db.getData(profesori);
				model.setProfesori(profesori);
				tabelProfesori.setModel(model);
				
				
				
			}
		});
		
		
		button.setText("Alege profesori din DB");
		button.setBounds(100, 10, 300, 20);
		tabelProfesori = new JTable();
		panel.add(button);
		add(panel);
		
		JButton btnDelete = new JButton();
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				try{
				id=profesori.get(tabelProfesori.getSelectedRow()).getId();
				db.delete(id);
				model.removeRow(tabelProfesori.getSelectedRow());
				model.fireTableChanged(null);
				}catch(Exception ex){
					System.out.println(ex);
				}
			}
		});
		btnDelete.setText("Delete from DB");
		btnDelete.setBounds(800, 15, 150, 20);
		panel.add(btnDelete);
		
		JButton btnParse = new JButton();
		btnParse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				try{
				db.parseData();
				}catch(Exception ex){
					System.out.println(ex);
				}
			}
		});
		btnParse.setText("Parse");
		btnParse.setBounds(970, 15, 150, 20);
		panel.add(btnParse);
		
//		tabel.setEditingColumn(5);
		JScrollPane sp = new JScrollPane(tabelProfesori);
		sp.setBounds(200, 70,800, 170);
		panel.add(sp);

		JButton btnEdit = new JButton();
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				try{
				id=profesori.get(tabelProfesori.getSelectedRow()).getId();
				db.delete(id);
				db.update(id, profesori.get(tabelProfesori.getSelectedRow()));
				model.fireTableChanged(null);
				}catch(Exception ex){
					System.out.println(ex);
				}
			}
		});
		btnEdit.setText("Update");
		btnEdit.setBounds(600, 15, 150, 20);
		panel.add(btnEdit);
		
		
		JButton btnRow = new JButton();
		btnRow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				try{
				Profesor prof = new Profesor();
				prof.setId(profesori.size()+1);
				model.addRow(prof);
				tabelProfesori.setModel(model);
				
				}catch(Exception ex){
					System.out.println(ex);
				}
			}
		});
		btnRow.setText("New row");
		btnRow.setBounds(1040, 120, 150, 20);
		panel.add(btnRow);
		
		setLocationRelativeTo(null);


		
		setVisible(true);
		setSize(1300, 800);
		
		
		
	}


		
	
}

