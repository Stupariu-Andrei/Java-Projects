import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class AngajatCellRender implements TableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// TODO Auto-generated method stub
		JLabel lbl = new JLabel();
		if (column == 1) {
			lbl.setForeground(Color.BLUE);
		} else if (column == 2) {
			lbl.setForeground(Color.RED);
		} else {
			lbl.setForeground(Color.BLACK);
		}
		lbl.setText(value.toString());
		return lbl;
	}

}
