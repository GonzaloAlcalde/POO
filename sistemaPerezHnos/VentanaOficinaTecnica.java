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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaOficinaTecnica extends JFrame {

	private VentanaInicio home;

	private JPanel contentPane;
	private JPanel panel;
	private JButton btnAsignarMaquina;
	private DefaultTableModel modelo;
	private JScrollPane scrollPane;
	private JTable tablaPedidos;

	public void vaciarTabla()
	{
		modelo.setRowCount(0);
	}
	
	public void actualizarPedidos()
	{
		try{
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/TP_Objetos", "root", "1234");
	
			String campos[] = {"idPedido", "idCliente", "fecha", "detalles", "sector", "comentarios"};
			String cadenaCampos = "";
			String coma = ""; 
			for (String c : campos){
				cadenaCampos += coma + c;
				coma = ",";
			}
			
			String sql = "SELECT " + cadenaCampos + " FROM " + "pedidos WHERE sector = 'oficina_tecnica' ORDER BY fecha;";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			Object registro[] = new Object[6];
			
			while (rs.next()){
				
				registro[0] = rs.getObject("idPedido");
				registro[1] = rs.getObject("idCliente");
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
	public VentanaOficinaTecnica(VentanaInicio inicio) {
		setTitle("P\u00E9rez Hnos. - Oficina T\u00E9cnica");
		
		home = inicio;
		home.setVisible(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 553, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.EAST);
		panel.setLayout(new BorderLayout(0, 0));
		
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
		modelo.addColumn("Fecha");
		modelo.addColumn("Comentarios");
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException e){
			e.printStackTrace();
		}
		
		this.actualizarPedidos();
		
		btnAsignarMaquina = new JButton("Asignar Maquina");
		btnAsignarMaquina.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(tablaPedidos.getSelectedRow() != -1)
				{
					int fila = tablaPedidos.getSelectedRow();

					DefaultTableModel modelo = (DefaultTableModel)tablaPedidos.getModel();
					Integer idPedido = (Integer) modelo.getValueAt(fila, 0);
					VentanaAsignarMaquina v = new VentanaAsignarMaquina(idPedido);
					v.setVisible(true);
				}
			}
		});
		panel.add(btnAsignarMaquina, BorderLayout.NORTH);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				home.setVisible(true);
				setVisible(false);
			}
		});
		
		panel.add(btnVolver, BorderLayout.SOUTH);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		tablaPedidos = new JTable();
		
		tablaPedidos.setModel(modelo);
		scrollPane.setViewportView(tablaPedidos);
	}

}
