import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class AngajatiTableModel extends DefaultTableModel {
	private List<Angajat> angajati;

	public AngajatiTableModel(List<Angajat> lista) {
		// TODO Auto-generated constructor stub
		angajati = lista;
	}

	public List<Angajat> getAngajati()
	{
		return angajati;
	}

	@Override
	public int getRowCount() {
		if (angajati != null)
		{
			return angajati.size();
		}
		else
		{
			return 0;
		}
	}
	
	@Override
	public int getColumnCount() {
		return 6;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		switch(columnIndex)
		{
		case 0: return "Id";
		case 1: return "Name";
		case 2: return "Age";
		case 3: return "Gender";
		case 4: return "Role";
		}
		return "";
	}
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex == 0 || columnIndex == 2)
			return Integer.class;
		if (columnIndex == 5) {
			return JButton.class;
		}
		return String.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return columnIndex == 5;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		if (rowIndex >= angajati.size())
			return null;
		Angajat angajat = angajati.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return angajat.getId();
		case 1:
			return angajat.getName();
		case 2:
			return angajat.getAge();
		case 3:
			return angajat.getSex();
		case 4:
			return angajat.getRole();
		}
		return null;
	}


	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}
}
