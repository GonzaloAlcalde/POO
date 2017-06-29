package sistemaPerezHnos;
import javax.swing.table.DefaultTableModel;

public class Cliente {
	public static DefaultTableModel buscar(String texto){
		DefaultTableModel modelo;
		
		String tabla = "cliente";
		String campos[] = {"idCliente", "razon_social", "cuit", "telefono", "direccion", "email"};
		String busqueda = texto;
		
		modelo = Tabla.ejecutarConsultaUsuario(tabla, campos, busqueda);
		
		return modelo;
	}
}
