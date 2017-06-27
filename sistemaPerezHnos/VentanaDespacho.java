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

public class VentanaDespacho extends JFrame {
	
	private VentanaInicio home;

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel modelo;
	
	public void vaciarTabla()
	{
		modelo.setRowCount(0);
	}
	
	public void actualizarPedidos()
	{
		try{
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/TP_Objetos", "root", "1234");
	
			String campos[] = {"idPedido", "razon_social", "fecha", "detalles", "sector", "comentarios"};
			String cadenaCampos = "";
			String coma = ""; 
			for (String c : campos){
				cadenaCampos += coma + c;
				coma = ",";
			}
			
			String sql = "SELECT " + cadenaCampos + " FROM " + "pedidos, cliente WHERE pedidos.idCliente = cliente.idCliente AND sector = 'Despacho' ORDER BY fecha;";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			Object registro[] = new Object[6];
			
			while (rs.next()){
				
				registro[0] = rs.getObject("idPedido");
				registro[1] = rs.getObject("razon_social");
				registro[2] = rs.getObject("detalles");
				
			    java.sql.Date dbSqlDate = rs.getDate("fecha");
			    java.util.Date dbSqlDateConverted = new java.util.Date(dbSqlDate.getTime());
			    registro[3] = dbSqlDateConverted;
			    
				registro[5] = rs.getObject("comentarios");
				
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

	/**
	 * Create the frame.
	 */
	public VentanaDespacho(VentanaInicio inicio) {
		setTitle("P\u00E9rez Hnos. - Despacho");
		
		home = inicio;
		home.setVisible(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
				int row = table.getSelectedRow();
				DefaultTableModel modelo = (DefaultTableModel)table.getModel();
				Integer idPedido = (Integer) modelo.getValueAt(row, 0);
				Pedido.eliminarPedido(idPedido);
				vaciarTabla();
				actualizarPedidos();
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
		modelo.addColumn("Comentarios");
		

		table.setModel(modelo);
		scrollPane.setViewportView(table);
		
		this.actualizarPedidos();
	}
}
