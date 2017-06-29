package sistemaPerezHnos;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Comentarios {
	public static void agregarComentario(Integer idPedido, String comentario, String sector)
	{
		try {
			Connection conexion= PruebaConexion.getConnection();
			
			String sql = "insert into comentario (idPedido, comentario, sector) VALUES ("+ Integer.toString(idPedido) + ", '"+ comentario +"', '" + sector + "');";
			
			Statement st1 = conexion.createStatement();
			st1.executeUpdate(sql);
			
			st1.close();
			conexion.close();
					
		} catch (SQLException s){
			s.printStackTrace();
		}
	}
}
