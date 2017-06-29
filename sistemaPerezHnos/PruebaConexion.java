package sistemaPerezHnos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PruebaConexion {
	public static Connection getConnection() throws SQLException
	{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException e){
			e.printStackTrace();
		}
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TP_Objetos?autoReconnect=true&useSSL=false", "root", "1234");
		return con;
	}
}
