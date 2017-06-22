package sistemaPerezHnos;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;

public class Test {
	
	public static void main(String[] args) {
		System.out.println("Probando probando");
		
		/*
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		try
		{
			Connection conexion= DriverManager.getConnection("jdbc:mysql://localhost/NOMBREDB", user, password);
			
			String sql= "insert into cliente (codigo, razon, cuit, telefono) VALUES (?, ?, ?, ?);";
			
			PreparedStatement pst= conexion.prepareStatement(sql);
			pst.setInt(1, codigo);
			pst.setString(2, razon);
			pst.setString(3, cuit);
			pst.setString(4, telefono);
			pst.executeUpdate();
			
			pst.close();
			conexion.close();
			
		} catch (SqlException s){
			s.printStackTrace();
		}
		
		*/
	}

}
