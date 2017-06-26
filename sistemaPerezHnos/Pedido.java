package sistemaPerezHnos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;

import net.sourceforge.jdatepicker.impl.JDatePickerImpl;

public class Pedido {
	public static void crearPedido(int idCliente, JTextField textField_1, JDatePickerImpl datePicker){
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/TP_Objetos", "root", "1234");
			
			String detalle = textField_1.getText();
			
			java.sql.Date selectedDate = (java.sql.Date) datePicker.getModel().getValue();
			String fecha= selectedDate.toString();
			
			String sql = "insert into pedidos (idCliente, detalles, fecha) VALUES ("+ idCliente +", '"+ detalle +"', '"+ fecha +"');";
			
			Statement st1 = conexion.createStatement();
			st1.executeUpdate(sql);
			
			st1.close();
			conexion.close();
					
		} catch (SQLException s){
			s.printStackTrace();
		}
	}
}
