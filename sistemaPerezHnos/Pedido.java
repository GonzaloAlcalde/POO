package sistemaPerezHnos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;

import net.sourceforge.jdatepicker.impl.JDatePickerImpl;

public class Pedido {
	
	public static boolean pedidoValido(JTextField textoRazonSocial, JDatePickerImpl datePicker)
	{
		boolean usuarioElegido = false;
		boolean fechaFueElegida = false;
		
		if (!(textoRazonSocial.getText().isEmpty()))
		{
			usuarioElegido = true;
		}
		if (datePicker.getModel().getValue() != null)
		{
			fechaFueElegida = true;
		}
		
		return (usuarioElegido && fechaFueElegida);	
	}
	
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
	
	public static void cambiarSector(Integer idPedido, String sector)
	{
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/TP_Objetos", "root", "1234");
			
			String idPedidoString = Integer.toString(idPedido);
			
			String sql = "UPDATE pedidos SET sector = '" + sector + "' WHERE idPedido = " + idPedidoString + ";";  
			
			Statement st1 = conexion.createStatement();
			st1.executeUpdate(sql);
			
			st1.close();
			conexion.close();
					
		} catch (SQLException s){
			s.printStackTrace();
		}
	}
	
	public static void asignarMaquina(Integer idPedido, String maquina){
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/TP_Objetos", "root", "1234");

			String idPedidoString = Integer.toString(idPedido);
			
			String sql = "UPDATE pedidos SET maquina = '" + maquina + "' WHERE idPedido = " + idPedidoString + ";"; 
			
			Statement st1 = conexion.createStatement();
			st1.executeUpdate(sql);
			
			st1.close();
			conexion.close();
			
			Pedido.cambiarSector(idPedido, "Produccion");
					
		} catch (SQLException s){
			s.printStackTrace();
		}
	}
	
	public static void despacharPedido(Integer idPedido){
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/TP_Objetos", "root", "1234");
			
			String idPedidoString = Integer.toString(idPedido);

			String sql = "UPDATE pedidos SET despachado = true WHERE idPedido = " + idPedidoString;
			
			Statement st1 = conexion.createStatement();
			st1.executeUpdate(sql);
			
			st1.close();
			conexion.close();
					
		} catch (SQLException s){
			s.printStackTrace();
		}
	}
}
