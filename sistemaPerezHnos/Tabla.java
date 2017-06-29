package sistemaPerezHnos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

public class Tabla {
	public static void vaciarTabla(DefaultTableModel modelo)
	{
		modelo.setRowCount(0);
	}
	
	public static String encadenarCampos(String campos[]){
		String cadenaCampos = "";
		String coma = ""; 
		for (String c : campos){
			cadenaCampos += coma + c;
			coma = ",";
		}
		return cadenaCampos;
	}
	
	public static void actualizarPedidosAdministracion(DefaultTableModel modelo)
	{
		try{
			Connection con= PruebaConexion.getConnection();
	
			String campos[] = {"idPedido", "razon_social", "fecha", "detalles", "sector"};
			String cadenaCampos = encadenarCampos(campos);
			
			String sql = "SELECT " + cadenaCampos + " FROM pedidos, cliente WHERE pedidos.idCliente = cliente.idCliente AND despachado = false ORDER BY fecha;";
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
				
				registro[4] = rs.getObject("sector");
				
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
	
	
	public static void actualizarPedidosDespacho(DefaultTableModel modelo)
	{
		try{
			Connection con= PruebaConexion.getConnection();
	
			String campos[] = {"idPedido", "razon_social", "fecha", "detalles", "sector"};
			String cadenaCampos = encadenarCampos(campos);
			
			String sql = "SELECT " + cadenaCampos + " FROM pedidos, cliente WHERE pedidos.idCliente = cliente.idCliente AND sector = 'Despacho' AND despachado = false ORDER BY fecha;";
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


	public static void actualizarPedidosProduccion(DefaultTableModel modelo)
	{
		try{
			Connection con= PruebaConexion.getConnection();
	
			String campos[] = {"idPedido", "razon_social", "fecha", "detalles", "maquina"};
			String cadenaCampos = encadenarCampos(campos);
			
			String sql = "SELECT " + cadenaCampos + " FROM pedidos, cliente WHERE pedidos.idCliente = cliente.idCliente AND sector = 'Produccion' ORDER BY fecha;";
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
	

	public static void actualizarPedidosOficinaTecnica(DefaultTableModel modelo)
	{
		try{
			Connection con= PruebaConexion.getConnection();
	
			String campos[] = {"idPedido", "razon_social", "fecha", "detalles", "sector"};
			String cadenaCampos = encadenarCampos(campos);
			
			String sql = "SELECT " + cadenaCampos + " FROM pedidos, cliente WHERE pedidos.idCliente = cliente.idCliente AND sector = 'oficina_tecnica' ORDER BY fecha;";
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
	
	public static void actualizarComentarios(Integer idPedido, DefaultTableModel modelo)
	{
		try{
			Connection con= PruebaConexion.getConnection();
	
			String sql = "SELECT Comentario, Comentario.sector FROM pedidos, comentario WHERE Comentario.idPedido = " + Integer.toString(idPedido) + " AND Pedidos.idPedido = " + Integer.toString(idPedido) + ";";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			Object registro[] = new Object[2];
			
			while (rs.next()){
				registro[0] = rs.getObject("Comentario");
				registro[1] = rs.getObject("Comentario.sector");
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
	
	public static DefaultTableModel ejecutarConsultaUsuario(String tabla, String[] campos, String busqueda){
		DefaultTableModel modelo = new DefaultTableModel(){
			 @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		modelo.addColumn("Codigo");
		modelo.addColumn("Razon Social");
		modelo.addColumn("CUIT");
		modelo.addColumn("Telefono");
		modelo.addColumn("Direccion");
		modelo.addColumn("email");
		
		try {
			Connection con= PruebaConexion.getConnection();
			String cadenaCampos = encadenarCampos(campos);
			
			String sql = "SELECT " + cadenaCampos + " FROM " + tabla + " WHERE razon_social LIKE ?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + busqueda.trim() + "%");
			
			ResultSet rs = ps.executeQuery();
			
			Object registro[] = new Object[6];
			
			while (rs.next()){
				
				registro[0] = rs.getObject("idCliente");
				registro[1] = rs.getObject("razon_social");
				registro[2] = rs.getObject("cuit");
				registro[3] = rs.getObject("telefono");
				registro[4] = rs.getObject("direccion");
				registro[5] = rs.getObject("email");
				
				modelo.addRow(registro);
			}
			
			rs.close();
			ps.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return modelo;
	}


}
