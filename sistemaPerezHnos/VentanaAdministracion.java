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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class VentanaAdministracion extends JFrame {

	private VentanaInicio home;
	private VentanaAdministracion ventanaAdministracion= this;
	
	private JPanel contentPane;
	private JTable tablaPedidos;
	private DefaultTableModel modelo;
	
	public void vaciarTabla()
	{
		modelo.setRowCount(0);
	}
	
	public void actualizarPedidos()
	{
		try{
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/TP_Objetos", "root", "1234");
	
			String campos[] = {"idPedido", "idCliente", "detalles", "sector", "comentarios"};
			String cadenaCampos = "";
			String coma = ""; 
			for (String c : campos){
				cadenaCampos += coma + c;
				coma = ",";
			}
			
			//SELECT codigo, razon_social FROM cliente WHERE razon_social LIKE '%perez%';
			String sql = "SELECT " + cadenaCampos + " FROM " + "pedidos;";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			Object registro[] = new Object[5];
			
			while (rs.next()){
				
				registro[0] = rs.getObject("idPedido");
				registro[1] = rs.getObject("idCliente");
				registro[2] = rs.getObject("detalles");
				//registro[3] = rs.getObject("fecha");
				registro[3] = rs.getObject("sector");
				registro[4] = rs.getObject("comentarios");
				
				modelo.addRow(registro);
			}
			
			rs.close();
			ps.close();
			con.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public VentanaAdministracion(VentanaInicio inicio) {
		
		home = inicio;
		
		home.setVisible(false);
		
		setTitle("P\u00E9rez Hnos. - Administraci\u00F3n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 300);
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
		modelo.addColumn("idCliente");
		modelo.addColumn("Detalles");
		//modelo.addColumn("Fecha");
		modelo.addColumn("Sector");
		modelo.addColumn("Comentarios");
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException e){
			e.printStackTrace();
		}
		
		this.actualizarPedidos();
		
/*		tablaPedidos.setModel(new DefaultTableModel(
			new Object[][] {
				{"Cliente", "Detalles", "Fecha", "Sector", "Comentarios"},
			},
			new String[] {
				"Cliente", "Detalles", "Fecha", "Sector", "Comentarios"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, String.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, true, true, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		}); */
		
		
		JPanel panelBotonesPedidos = new JPanel();
		contentPane.add(panelBotonesPedidos, BorderLayout.EAST);
		panelBotonesPedidos.setLayout(new BorderLayout(0, 0));
		
		JButton btnNuevoPedido = new JButton("Nuevo Pedido");
		btnNuevoPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//SE CREA LA VENTANA PEDIDO
				VentanaPedidoNuevo v= new VentanaPedidoNuevo(ventanaAdministracion);
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
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.WEST);
		
		tablaPedidos = new JTable();
		
		tablaPedidos.setModel(modelo);
		contentPane.add(tablaPedidos, BorderLayout.CENTER);
	}

}
