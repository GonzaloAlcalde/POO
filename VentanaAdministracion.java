package sistemaPerezHnos;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAdministracion extends JFrame {

	private VentanaInicio home;
	
	private JPanel contentPane;
	private JTable tablaPedidos;

	/**
	 * Create the frame.
	 */
	public VentanaAdministracion(VentanaInicio inicio) {
		
		home = inicio;
		
		home.setVisible(false);
		
		setTitle("P\u00E9rez Hnos. - Administraci\u00F3n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		tablaPedidos = new JTable();
		tablaPedidos.setModel(new DefaultTableModel(
			new Object[][] {
				{"Cliente", "Detalles", "Fecha"},
			},
			new String[] {
				"Cliente", "Detalles", "Fecha"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, String.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		contentPane.add(tablaPedidos, BorderLayout.CENTER);
		
		JPanel panelBotonesPedidos = new JPanel();
		contentPane.add(panelBotonesPedidos, BorderLayout.EAST);
		panelBotonesPedidos.setLayout(new BorderLayout(0, 0));
		
		JButton btnNuevoPedido = new JButton("Nuevo Pedido");
		panelBotonesPedidos.add(btnNuevoPedido, BorderLayout.NORTH);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				home.setVisible(true);
				setVisible(false);
			}
		});
		panelBotonesPedidos.add(btnVolver, BorderLayout.SOUTH);
	}

}
