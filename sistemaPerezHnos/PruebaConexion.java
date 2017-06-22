package sistemaPerezHnos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

public class PruebaConexion {
	public static DefaultTableModel ejecutarConsulta(String tabla, String[] campos, String busqueda){
		DefaultTableModel modelo = new DefaultTableModel(){
			 @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
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
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException e){
			e.printStackTrace();
		}
		
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/TP_Objetos", "root", "1234");

			String cadenaCampos = "";
			String coma = ""; 
			for (String c : campos){
				cadenaCampos += coma + c;
				coma = ",";
			}
			
			//SELECT codigo, razon_social FROM cliente WHERE razon_social LIKE '%perez%';
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return modelo;
	}

}
