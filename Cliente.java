package sistemaPerezHnos;

import javax.swing.table.DefaultTableModel;

public class Cliente {
	private String razonSocial;
	private String CUIT;
	private String direccion;
	private String telefono;
	private String email;
	
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	
	public String getCUIT() {
		return CUIT;
	}
	public void setCUIT(String cUIT) {
		CUIT = cUIT;
	}
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public static DefaultTableModel buscar(String texto){
		DefaultTableModel modelo;
		
		String tabla = "cliente";
		String campos[] = {"idCliente", "razon_social", "cuit", "telefono", "direccion", "email"};
		String busqueda = texto;
		
		modelo = PruebaConexion.ejecutarConsulta(tabla, campos, busqueda);
		
		return modelo;
	}
}
