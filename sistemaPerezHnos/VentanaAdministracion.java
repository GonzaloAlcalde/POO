package sistemaPerezHnos;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class VentanaAdministracion extends JFrame {

	private VentanaInicio home;
	private VentanaAdministracion thisWindow= this;
	
	private JPanel contentPane;
	private JTable tablaPedidos;
	private DefaultTableModel modelo;
	
	private String sector = "Administracion";
	
	public String getSector(){
		return sector;
	}
	
	public DefaultTableModel getModelo(){
		return modelo;
	}
	
	public VentanaAdministracion(VentanaInicio inicio) {
		
		home = inicio;
		
		home.setVisible(false);
		
		setTitle("P\u00E9rez Hnos. - Administraci\u00F3n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
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
		modelo.addColumn("Sector");
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException e){
			e.printStackTrace();
		}
		
		Tabla.actualizarPedidosAdministracion(modelo);
		
		JPanel panelBotonesPedidos = new JPanel();
		contentPane.add(panelBotonesPedidos, BorderLayout.EAST);
		panelBotonesPedidos.setLayout(new BorderLayout(0, 0));
		
		JButton btnNuevoPedido = new JButton("Nuevo Pedido");
		btnNuevoPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//SE CREA LA VENTANA PEDIDO
				VentanaPedidoNuevo v= new VentanaPedidoNuevo(thisWindow);
				v.setModal(true);
				v.setVisible(true);
			}
		});
		panelBotonesPedidos.add(btnNuevoPedido, BorderLayout.NORTH);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				home.setVisible(true);
				setVisible(false);
			}
		});
		panelBotonesPedidos.add(btnVolver, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		panelBotonesPedidos.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btnComentarios = new JButton("Comentarios");
		btnComentarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tablaPedidos.getSelectedRow() != -1){
					int row = tablaPedidos.getSelectedRow();
					DefaultTableModel modelo = (DefaultTableModel)tablaPedidos.getModel();
					Integer idPedido = (Integer) modelo.getValueAt(row, 0);
					VentanaComentarios v = new VentanaComentarios(thisWindow, idPedido, thisWindow.getSector());
					v.setVisible(true);
				}
			}
		});
		panel.add(btnComentarios, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		tablaPedidos = new JTable();
		
		tablaPedidos.setModel(modelo);
		scrollPane.setViewportView(tablaPedidos);
	}

}
