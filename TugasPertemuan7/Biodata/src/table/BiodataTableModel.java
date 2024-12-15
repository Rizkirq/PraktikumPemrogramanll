package table;

import model.Biodata;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class BiodataTableModel extends AbstractTableModel {
    private List<Biodata> list;
    private final String[] columnNames = {"ID", "Nama", "Alamat"};

    public BiodataTableModel(List<Biodata> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Biodata biodata = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return biodata.getId();
            case 1:
                return biodata.getNama();
            case 2:
                return biodata.getAlamat();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
