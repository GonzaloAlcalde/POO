package sistemaPerezHnos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;

import javax.swing.JTextField;

public class Pedido {
	public static void crearPedido(int idCliente, JTextField textField_1){
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/TP_Objetos", "root", "1234");
			
			String detalle = textField_1.getText();
			String fecha = "NOW()";
			
			//"insert into cliente (codigo, razon_social, CUIT, telefonos) VALUES (1, 'Perex S.A.', '2344455544', '0303456 (preguntar por pipo)');"
		
			String sql = "insert into pedidos (idCliente, detalles, fecha) VALUES ("+ idCliente +", '"+ detalle +"', "+ fecha +");";
			
			Statement st1 = conexion.createStatement();
			st1.executeUpdate(sql);
			
			st1.close();
			conexion.close();
					
		} catch (SQLException s){
			s.printStackTrace();
		}
	}
}
