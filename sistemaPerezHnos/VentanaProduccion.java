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

public class VentanaProduccion extends JFrame {

	private VentanaInicio home;

	private VentanaProduccion thisWindow= this;
	
	private JPanel contentPane;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnProducido;
	private DefaultTableModel modelo;
	private JPanel panel_1;
	private JButton btnComentarios;

	private String sector = "Produccion";
	
	public String getSector(){
		return sector;
	}
	
	public VentanaProduccion(VentanaInicio inicio) {
		setTitle("P\u00E9rez Hnos. - Producci\u00F3n");
		
		home = inicio;
		home.setVisible(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.EAST);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				home.setVisible(true);
				setVisible(false);
			}
		});
		
		panel.add(btnVolver, BorderLayout.SOUTH);
		
		btnProducido = new JButton("Producido");
		btnProducido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRow() != -1){
					int row = table.getSelectedRow();
					DefaultTableModel modelo = (DefaultTableModel)table.getModel();
					Integer idPedido = (Integer) modelo.getValueAt(row, 0);
					Pedido.cambiarSector(idPedido, "Despacho");
					Tabla.vaciarTabla(modelo);
					Tabla.actualizarPedidosProduccion(modelo);
				}
			}
		});
		panel.add(btnProducido, BorderLayout.NORTH);
		
		panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		btnComentarios = new JButton("Comentarios");
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
		panel_1.add(btnComentarios, BorderLayout.NORTH);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		modelo = new DefaultTableModel(){
			 @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		modelo.addColumn("idPedido");
		modelo.addColumn("Razon social");
		modelo.addColumn("Detalles");
		modelo.addColumn("Fecha");
		modelo.addColumn("Maquina asignada");
		

		table.setModel(modelo);
		scrollPane.setViewportView(table);
		
		Tabla.actualizarPedidosProduccion(modelo);
	}
	

}
