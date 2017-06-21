package sistemaPerezHnos;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class VentanaOficinaTecnica extends JFrame {

	private VentanaInicio home;

	private JPanel contentPane;
	private JTable table;
	private JPanel panel;
	private JButton btnAsignarMaquina;

	/**
	 * Create the frame.
	 */
	public VentanaOficinaTecnica(VentanaInicio inicio) {
		setTitle("P\u00E9rez Hnos. - Oficina T\u00E9cnica");
		
		home = inicio;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 408, 324);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Cliente", "Detalle", "Fecha", "Maquina", "Comentarios"},
			},
			new String[] {
				"Cliente", "Detalle", "Fecha", "Maquina", "Comentarios"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		contentPane.add(table, BorderLayout.WEST);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.EAST);
		panel.setLayout(new BorderLayout(0, 0));
		
		btnAsignarMaquina = new JButton("Asignar Maquina");
		panel.add(btnAsignarMaquina, BorderLayout.NORTH);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				home.setVisible(true);
				setVisible(false);
			}
		});
		
		panel.add(btnVolver, BorderLayout.SOUTH);
		
		
	}

}

