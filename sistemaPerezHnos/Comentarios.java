package sistemaPerezHnos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Comentarios {
	public static void agregarComentario(Integer idPedido, String comentario)
	{
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/TP_Objetos", "root", "1234");
			
			String sql = "insert into comentario (idPedido, comentario) VALUES ("+ Integer.toString(idPedido) + ", '"+ comentario +"');";
			
			Statement st1 = conexion.createStatement();
			st1.executeUpdate(sql);
			
			st1.close();
			conexion.close();
					
		} catch (SQLException s){
			s.printStackTrace();
		}
	}
}