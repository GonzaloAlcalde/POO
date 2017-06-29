package sistemaPerezHnos;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class VentanaDespacho extends JFrame {
	
	private VentanaInicio home;

	private VentanaDespacho thisWindow= this;
	
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel modelo;
	
	private String sector = "Despacho";
	
	public String getSector(){
		return sector;
	}

	public VentanaDespacho(VentanaInicio inicio) {
		setTitle("P\u00E9rez Hnos. - Despacho");
		
		home = inicio;
		home.setVisible(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("Despachar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1){
					int row = table.getSelectedRow();
					DefaultTableModel modelo = (DefaultTableModel)table.getModel();
					Integer idPedido = (Integer) modelo.getValueAt(row, 0);
					Pedido.despacharPedido(idPedido);
					Tabla.vaciarTabla(modelo);
					Tabla.actualizarPedidosDespacho(modelo);
				}
			}
		});
		panel_1.add(btnNewButton, BorderLayout.NORTH);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				home.setVisible(true);
			}
		});
		panel_1.add(btnVolver, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		panel_1.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btnComentarios = new JButton("Comentarios");
		btnComentarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1){
					int row = table.getSelectedRow();
					DefaultTableModel modelo = (DefaultTableModel)table.getModel();
					Integer idPedido = (Integer) modelo.getValueAt(row, 0);
					VentanaComentarios v = new VentanaComentarios(thisWindow, idPedido, thisWindow.getSector());
					v.setVisible(true);
				}
			}
		});
		panel.add(btnComentarios, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		modelo = new DefaultTableModel(){
			 @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		modelo.addColumn("idPedido");
		modelo.addColumn("Razon social");
		modelo.addColumn("Detalles");
		modelo.addColumn("Fecha");
		

		table.setModel(modelo);
		scrollPane.setViewportView(table);
		
		Tabla.actualizarPedidosDespacho(modelo);
	}
}
