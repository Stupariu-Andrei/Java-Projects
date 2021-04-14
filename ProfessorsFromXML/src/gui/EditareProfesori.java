package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;






import procesare.Profesor;


@SuppressWarnings("serial")
public class EditareProfesori extends DefaultTableModel {

	private List<Profesor> profesor = null;

	public void setProfesori(List<Profesor> profesori) {
		this.profesor = profesori;
	}

	@Override
	public int getRowCount() {
		if(profesor == null)
			return 0;
		return  profesor.size();
	}

	@Override
	public int getColumnCount() {
		return 7;
	}

	@Override
	public String getColumnName(int columnIndex) {
		if (columnIndex > 7) {
			return "";
		}
		switch (columnIndex) {
			case 0:
				return "Id";
			case 1:
				return "Nume";
			case 2:
				return "Prenume";
			case 3:
				return "Salariu";
			case 4:
				return "Sex" ;
			case 5:
				return "Telefon";
			case 6:
				return "Facultate";
			}

		return "";
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
			case 0:
			case 4:
				return Integer.class;
			}
		return String.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		
		return columnIndex == 7;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		if(rowIndex >= profesor.size() || columnIndex > 7) {
				return null;
			}
			Profesor p  = profesor.get(rowIndex);
		switch (columnIndex) {
			case 0:
				return p.getId();
			case 1:
				return p.getNume();
			case 2:
				return p.getPrenume();
			case 3:
				return p.getSalariu();
			case 4:
				return p.getSex();
			case 5:
				return p.getTelefon();
			case 6:
				return p.getFacultate();
			}
		
		return null;
	}
}
