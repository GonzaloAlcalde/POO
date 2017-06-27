package sistemaPerezHnos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalityType;

public class VentanaAsignarMaquina extends JDialog {

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JTable tablaMaquina;
	private JButton btnAsignar;
	
	private Integer idPedido;

	/**
	 * Create the frame.
	 */
	public VentanaAsignarMaquina(Integer idPedido) {
		this.idPedido = idPedido;
		
		setResizable(false);
		setTitle("P\u00E9rez Hnos. - Asignar M\u00E1quina");
		setModal(true);
		setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		setBounds(100, 100, 173, 139);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		tablaMaquina= new JTable();
		panel.add(tablaMaquina, BorderLayout.CENTER);
		tablaMaquina.setModel(new DefaultTableModel(
			new Object[][] {
				{"Oxigeno 1"},
				{"Oxigeno 2"},
				{"Oxigeno 3"},
				{"Oxigeno 4"},
				{"Plasma 5"},
				{"Plasma 6"},
			},
			new String[] {
				"M\u00E1quina"
			}
		));
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		btnAsignar = new JButton("Asignar");
		btnAsignar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tablaMaquina.getSelectedRow() != -1)
				{
					cerrarRetornarResultado();
				}
			}
		});
		panel_1.add(btnAsignar, BorderLayout.SOUTH);
	}
	
	private void cerrarRetornarResultado()
	{
		int fila= tablaMaquina.getSelectedRow();
		
		DefaultTableModel modelo = (DefaultTableModel)tablaMaquina.getModel();
		
		Pedido.asignarMaquina(idPedido, (String)modelo.getValueAt(fila, 0));
		
		setVisible(false);
	}
}
