package sistemaPerezHnos;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentanaComentarios extends JFrame {

	private JPanel contentPane;
	private JTable tablaComentarios;
	private JFrame home;
	

	private DefaultTableModel modelo;
	
	public void vaciarTabla()
	{
		modelo.setRowCount(0);
	}
	
	public void actualizarPedidos()
	{
		try{
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/TP_Objetos", "root", "1234");
	
			String campos[] = {"Pedidos.idPedido", "Comentarios.idPedido", "Comentario"};
			String cadenaCampos = "";
			String coma = ""; 
			for (String c : campos){
				cadenaCampos += coma + c;
				coma = ",";
			}
			
			String sql = "SELECT " + cadenaCampos + " FROM pedidos, comentarios WHERE " + campos[0] + " = " + campos[1] + ";";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			Object registro[] = new Object[1];
			
			while (rs.next()){
				registro[0] = rs.getObject("Comentario");				
				modelo.addRow(registro);
			}
			
			rs.close();
			ps.close();
			con.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	public VentanaComentarios(JFrame ventanaPadre) {
		
		home = ventanaPadre;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		tablaComentarios = new JTable();
		tablaComentarios.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
			},
			new String[] {
				"Comentarios"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		contentPane.add(tablaComentarios, BorderLayout.CENTER);
		
		this.actualizarPedidos();
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnAgregarComentario = new JButton("Agragar Comentario");
		panel.add(btnAgregarComentario);
		
		JButton btnAtrás = new JButton("Atr\u00E1s");
		btnAtrás.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
				home.setVisible(true);
			}
		});
		panel.add(btnAtrás);
	}

}
