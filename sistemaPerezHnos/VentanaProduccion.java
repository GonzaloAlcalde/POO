package sistemaPerezHnos;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class VentanaProduccion extends JFrame {

	private VentanaInicio home;

	private VentanaProduccion thisWindow= this;
	
	private JPanel contentPane;
	private JPanel panel;
	private JButton btnAsignarMaquina;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnProducido;
	private DefaultTableModel modelo;
	private JPanel panel_1;
	private JButton btnComentarios;

	public void vaciarTabla()
	{
		modelo.setRowCount(0);
	}
	
	public void actualizarPedidos()
	{
		try{
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/TP_Objetos", "root", "1234");
	
			String campos[] = {"idPedido", "razon_social", "fecha", "detalles", "maquina"};
			String cadenaCampos = "";
			String coma = ""; 
			for (String c : campos){
				cadenaCampos += coma + c;
				coma = ",";
			}
			
			String sql = "SELECT " + cadenaCampos + " FROM " + "pedidos, cliente WHERE pedidos.idCliente = cliente.idCliente AND sector = 'Produccion' ORDER BY fecha;";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			Object registro[] = new Object[5];
			
			while (rs.next()){
				
				registro[0] = rs.getObject("idPedido");
				registro[1] = rs.getObject("razon_social");
				registro[2] = rs.getObject("detalles");
				
			    java.sql.Date dbSqlDate = rs.getDate("fecha");
			    java.util.Date dbSqlDateConverted = new java.util.Date(dbSqlDate.getTime());
			    registro[3] = dbSqlDateConverted;
				
				registro[4] = rs.getObject("maquina");
				
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
					vaciarTabla();
					actualizarPedidos();
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
					VentanaComentarios v = new VentanaComentarios(thisWindow, idPedido);
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
		       //all cells false
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
		
		this.actualizarPedidos();
	}
	

}
