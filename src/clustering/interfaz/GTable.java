package clustering.interfaz;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class GTable extends JTable{
	private Map<Integer,Map<Integer,Integer>> tabla;
	private int dimencion;
	private String[] header;
	private JTable hColumn;
	private JTable hRow;
	private JFrame padre;
	public GTable(JFrame frame) {
		super();
		this.padre = frame;
		this.dimencion = 9;
		inicializar();
	}
	
	private void inicializar() {
		hColumn = new JTable();
		hRow = new JTable();
		hRow.setBackground(new Color(100, 100, 100));
		hColumn.setBackground(new Color(100, 100, 100));
		hRow.setBounds(100, 45, 45, 330);
		hColumn.setBounds(145, 30, 502, 15);
		padre.getContentPane().add(hRow);
		padre.getContentPane().add(hColumn);
	}
	public void loadTable(Map<Integer,Map<Integer,Integer>> grafo){
		//validacion de dimencion de la tabla
		
		String[][] tabla = new String[this.dimencion][this.dimencion+1];
		String[] header = new String[grafo.size() + 1];
		int x = 1;
		int i = 0;
		Map<Integer, Integer> pociciones = new HashMap<Integer,Integer>();
		for(Map.Entry<Integer,Map<Integer,Integer>> col : grafo.entrySet()) {
			tabla[0][x] = "G: " + col.getKey();
			tabla[x][0] = "G: " + col.getKey();
			pociciones.put(col.getKey(), x++);
		}
		x = 1;
		for(Map.Entry<Integer,Map<Integer,Integer>> col : grafo.entrySet()) {
			i = 0;
			for(Map.Entry<Integer,Integer> fil : col.getValue().entrySet()) {
				tabla[x][pociciones.get(fil.getKey())] = "G:" + col.getKey() + " P: " + fil.getValue() + "";
				i++;
			}
			x++;
		}
		this.setModel(new DefaultTableModel(tabla, header));
	}
	
	public void loadTable(){
		//validacion de dimencion de la tabla
		
		String[][] tabla = new String[this.dimencion][this.dimencion+1];
		String[][] header = new String[1][this.dimencion];
		String[][] hRow = new String[this.dimencion][1];
		
		for(int x = 0; x < this.dimencion; x++) {
			tabla[x][x] = "null";
			header[0][x] = "vacio";
			hRow[x][0] = "vacio";
		}
		this.setModel(new DefaultTableModel(tabla,  new String[this.dimencion]));
		this.hColumn.setModel(new DefaultTableModel(header,  new String[this.dimencion]));
		this.hRow.setModel(new DefaultTableModel(hRow,  new String[1]));
	}
	
	public Map<Integer,Map<Integer,Integer>> getTable() {
		return tabla;
	}
	
	public int getDimencion() {
		return dimencion;
	}
	
	public void setDimencion(int dim) {
		this.dimencion = dim;
	}
}
