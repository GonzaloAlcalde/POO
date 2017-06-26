package sistemaPerezHnos;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class VentanaPedidoNuevo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private int idCliente;

	public VentanaPedidoNuevo(VentanaAdministracion ventanaAdministracion) {
		setResizable(false);
		setTitle("Pedido nuevo");
		setBounds(100, 100, 500, 150);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new GridLayout(0, 3, 0, 0));
			{
				JLabel lblCliente = new JLabel("                           Cliente");
				lblCliente.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblCliente);
			}
			{
				textField = new JTextField();
				textField.setEditable(false);
				panel.add(textField);
				textField.setColumns(10);
			}
			{
				JButton btnBuscar = new JButton("Buscar");
				btnBuscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//Se crea la ventana buscar cliente
						VentanaBuscarCliente v= new VentanaBuscarCliente();
						v.setModal(true);
						v.setVisible(true);
						textField.setText(v.getRazon_Social());
						idCliente = v.getIdCliente();
					}
				});
				panel.add(btnBuscar);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JLabel lblDetalles = new JLabel("Detalles");
				lblDetalles.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblDetalles);
			}
			{
				textField_1 = new JTextField();
				panel.add(textField_1);
				textField_1.setColumns(10);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JLabel lblFecha = new JLabel("Fecha");
				lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblFecha);
			}
			{
				textField_2 = new JTextField();
				panel.add(textField_2);
				textField_2.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						crearPedido();
						setVisible(false);
						ventanaAdministracion.vaciarTabla();
						ventanaAdministracion.actualizarPedidos();
					}
				});
				
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void crearPedido(){
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
