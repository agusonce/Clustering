package clustering;

import java.awt.EventQueue;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.table.TableColumn;

import clustering.backend.Grafo;
import clustering.backend.GrafoAlgoritmos;
import clustering.interfaz.GScreen;
import clustering.interfaz.GTable;

import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class app {

	private GScreen pantalla;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					app window = new app();
					window.pantalla.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public app() {
		this.pantalla = new GScreen();
	}

	
}
