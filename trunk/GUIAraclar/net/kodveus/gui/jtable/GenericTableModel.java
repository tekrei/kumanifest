/*
 * Emre tarafindan Jun 10, 2005 tarihinde yaratilmistir.
 */
package net.kodveus.gui.jtable;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import net.kodveus.gui.araclar.AliasMap;
import net.kodveus.gui.araclar.ClassParser;
import net.kodveus.gui.araclar.TipCevirici;
import net.kodveus.gui.araclar.VeriSinif;

public class GenericTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private AliasMap map;
	private List<?> dvoList;
	private ArrayList<String> columns;
	private boolean isEditable;
	private boolean rowCountColumnVisible;

	public GenericTableModel(AliasMap _map, List<?> _dvoList,
			boolean _isEditable, boolean _rowCountColumn) {
		this.map = _map;
		this.dvoList = _dvoList;
		this.isEditable = _isEditable;
		this.rowCountColumnVisible = _rowCountColumn;
		baslikHazirla();
	}

	private void baslikHazirla() {
		columns = new ArrayList<String>();

		//
		if (rowCountColumnVisible) {
			this.columns.add("");
		}

		for (int i = 0; i < map.getAliasCount(); i++) {
			this.columns.add(map.getAlias(i));
		}
	}

	public int getColumnCount() {
		return columns.size();
	}

	public int getRowCount() {
		if (dvoList != null) {
			return dvoList.size();
		}

		return 0;
	}

	public String getColumnName(int col) {
		return (String) this.columns.get(col);
	}

	public Object getValueAt(int row, int col) {

		if (rowCountColumnVisible && col == 0) {
			return row+1;//1'den baslamali
		}
		String colName = this.getColumnName(col);
		Object obj = dvoList.get(row);

		try {
			Object value = map.getValueOfAttribute(obj, colName);

			if ((value == null) && (value instanceof String)) {
				return "";
			}

			try {
				if ((value != null)
						&& (value.getClass().equals(Date.class) || value
								.getClass().equals(Timestamp.class))) {
					value = TipCevirici.cevirDate((Date) value);
				}

				if ((value != null)
						&& (value.getClass().equals(BigDecimal.class))) {
					value = TipCevirici.cevirBigDecimal((BigDecimal) value);
				}
			} catch (Exception ex) {
			}

			return value;
		} catch (Exception ex) {
			ex.printStackTrace();

			return "r:" + row + ",col:" + col;
		}
	}

	public Object getObjectAtRow(int at) {
		return dvoList.get(at);
	}

	/*
	 * JTable uses this method to determine the default renderer/ editor for
	 * each cell. If we didn't implement this method, then the last column would
	 * contain text ("true"/"false"), rather than a check box.
	 */
	@SuppressWarnings("unchecked")
	public Class getColumnClass(int c) {
		if (getValueAt(0, c) == null) {
			return String.class;
		}

		if (getValueAt(0, c).getClass().equals(Date.class)) {
			return String.class;
		}

		return getValueAt(0, c).getClass();
	}

	/*
	 * Don't need to implement this method unless your table's editable.
	 */
	public boolean isCellEditable(int row, int col) {
		// Note that the data/cell address is constant,
		// no matter where the cell appears onscreen.
		return isEditable;
	}

	/*
	 * Don't need to implement this method unless your table's data can change.
	 */
	public void setValueAt(Object value, int row, int col) {
		try {
			if (rowCountColumnVisible && col == 0) {
				return;
			}
			VeriSinif dvo = (VeriSinif) dvoList.get(row);
			String propertyName = map.getAttributeName(map.getAlias(col));
			ClassParser parser = new ClassParser();
			Class propertyClass = parser.getPropertyType(dvo, propertyName);
			ClassParser.setProperty(dvo, map
					.getAttributeName(map.getAlias(col)), generateValue(
					propertyClass.getName(), value));
			fireTableCellUpdated(row, col);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	private Object generateValue(String className, Object value) {
		if (className.indexOf("BigDecimal") > 0) {
			if (value instanceof BigDecimal) {
				return value;
			}

			return TipCevirici.cevirBigDecimal(value.toString());
		}

		if (className.indexOf("Date") > 0) {
			if (value instanceof Date) {
				return value;
			}

			return TipCevirici.cevirDate(value.toString());
		}

		return value;
	}
}
