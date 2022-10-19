package clustering.interfaz;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GTable extends JTable{
	private Map<Integer,Map<Integer,Integer>> tabla;
	
	public GTable() {
		super();
	}
	
	public void loadTable(Map<Integer,Map<Integer,Integer>> grafo){
		String[][] tabla = new String[grafo.size() + 1][grafo.size() + 2];
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
	
	
	public Map<Integer,Map<Integer,Integer>> getTable() {
		return tabla;
	}
}
