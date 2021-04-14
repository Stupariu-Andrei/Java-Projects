package gui;

import javax.swing.table.DefaultTableModel;

import procesare.Catalog;
import procesare.Materie;
import procesare.Nota;
import procesare.Situatie;

@SuppressWarnings("serial")
public class CatalogDataModel extends DefaultTableModel {

	private Catalog catalog = null;

	public CatalogDataModel(Catalog catalog) {
		super();
		this.catalog = catalog;
	}

	@Override
	public int getRowCount() {
		if(catalog == null) return 0;
		int r = 0;
		for (Situatie s : catalog.getSituatii()) {
			r += s.getNote().size();
		}
		return r;
	}

	@Override
	public int getColumnCount() {
		return 7;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "Nume";
		case 1:
			return "Prenume";
		case 2:
			return "Medie";
		case 3:
			return "Materie";
		case 4:
			return "Nota";
		case 5:
			return "Bursier";
		case 6:
			return "Restantier";
		}
		return "";
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 2:
		case 4:
			return Float.class;
		}
		return String.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		
		return true;
	}

	private Materie getMaterie(int idMaterie) {
		for (Materie m : catalog.getMaterii()) {
			if (m.getId() == idMaterie)
				return m;
		}
		return null;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		int i = 0;
		for (Situatie s : catalog.getSituatii()) {
			int n = s.getNote().size();
			if (i + n > rowIndex) {
				Nota nota = s.getNote().get(rowIndex - i);
				Materie m = getMaterie(nota.getIdMaterie());
				switch (columnIndex) {
				
				case 0:
					return s.getStudent().getNume();
				case 1:
					return s.getStudent().getPrenume();
				case 2:
					return s.getMedie();
				case 3:
					return m.getDenumire();
				case 4:
					return nota.getNota();
				case 5:
					return s.getBursa();
			case 6:
				return s.getRestante();
			}
				return null;
			} else {
				i += n;
			}
		}
		return null;
	}
}
