package clustering.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.TableColumn;

import clustering.backend.Grafo;
import clustering.backend.GrafoAlgoritmos;

public class GScreen extends JFrame{
	private GTable table;
	private JButton btnNewButton;
	private JButton btnGrafo;
	private JLabel label;
	private JTextField tfSplit;
	private Grafo grafo;
	
	
	public GScreen() {
			super();
			initialize();
	}
	private void initialize() {
		this.setBounds(200, 200, 620, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		
		/*
		 * Tabla con los puntos
		 */
		table = new GTable();
		this.grafo = grafoDefault();
		table.setBackground(new Color(110, 110, 155));
		table.addColumn(new TableColumn());
		table.setBounds(100, 30, 502, 330);
		this.getContentPane().add(table);
		table.loadTable(grafo.getGrafo());;
		/*
		 * boton calcular
		 */
		btnNewButton = new JButton("Calcular");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcular();
			}
		});
		
		btnNewButton.setBounds(0, 60, 100, 30);
		this.getContentPane().add(btnNewButton);
		
		/*
		 * boton calcular
		 */
		btnGrafo = new JButton("restaurar");
		btnGrafo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restaurar();
			}
		});
		
		btnGrafo.setBounds(0, 90, 100, 30);
		this.getContentPane().add(btnGrafo);
		
		//Label titulo
		label = new JLabel("Clustering");
		label.setBounds(0, 0, 400, 30);
		label.setVerticalTextPosition(SwingConstants.CENTER);
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		this.getContentPane().add(label);
		
		tfSplit = new JTextField();
		this.getContentPane().add(tfSplit, BorderLayout.SOUTH);
		tfSplit.setBounds(0, 30, 100, 30);;
	}
	
	public void calcular() {
		try{
			validarNumero(tfSplit.getText());
			int split =  Integer.parseInt(tfSplit.getText());
			
			grafo = GrafoAlgoritmos.clastering(grafo, split);
			table.loadTable(grafo.getGrafo());
		}catch(IllegalArgumentException e) {
			System.out.println(e.toString());
		}
		
	}
	
	public void restaurar(){
		this.grafo = grafoDefault();
		table.loadTable(grafo.getGrafo());
	}
	public Grafo grafoDefault() {
		Grafo g = new Grafo(1);//1
		g.agregarArista(2,3,4);
		g.agregarArista(1,2,2);
		g.agregarArista(2,4,5);
		g.agregarArista(3,1,4);
		g.agregarArista(6,4,3);
		g.agregarArista(7,3,4);
		g.agregarArista(3,6,6);
		g.agregarArista(6,7,9);
		g.agregarArista(6,5,6);
		g.agregarArista(7,9,6);
		g.agregarArista(5,9,4);
		return g;
	}
	private void validarNumero(String split){
		if (split == null || "".equals(split)) {
			throw new IllegalArgumentException("parametro split null/vacio");
		}
		if(!this.isInteger(split)) {
			throw new IllegalArgumentException("el paràmetro split debe ser de tipo numerico");
		}
		int numero = Integer.parseInt(split);
		if(numero < 0) {
			throw new IllegalArgumentException("el paràmetro split debe ser positivo");
		}
	}
	
	
	public boolean isInteger(String numero){
	    try{
	        Integer.parseInt(numero);
	        return true;
	    }catch(NumberFormatException e){
	        return false;
	    }
	}
}
