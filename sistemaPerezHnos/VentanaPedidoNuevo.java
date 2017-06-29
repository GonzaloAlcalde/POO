package sistemaPerezHnos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.SqlDateModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

//Libreria del calendario -- http://www.java2s.com/Code/Jar/j/Downloadjdatepicker132jar.htm
//Tutorial -- http://www.codejava.net/java-se/swing/how-to-use-jdatepicker-to-display-calendar-component

public class VentanaPedidoNuevo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textoRazonSocial;
	private JTextField textoDetalles;
	private JDatePickerImpl datePicker;
	private int idCliente;

	public VentanaPedidoNuevo(VentanaAdministracion ventanaAdministracion) {
		setResizable(false);
		setTitle("Pedido nuevo");
		setBounds(100, 100, 360, 175);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JLabel lblCliente = new JLabel("Cliente");
				lblCliente.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblCliente, BorderLayout.NORTH);
			}
			{
				JLabel lblDetalles = new JLabel("Detalles");
				panel.add(lblDetalles, BorderLayout.CENTER);
				lblDetalles.setHorizontalAlignment(SwingConstants.CENTER);
			}
			
			JLabel lblFecha = new JLabel("Fecha");
			panel.add(lblFecha, BorderLayout.SOUTH);
			lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.EAST);
			panel.setLayout(new BorderLayout(0, 0));
			{
				textoDetalles = new JTextField();
				panel.add(textoDetalles, BorderLayout.CENTER);
				textoDetalles.setColumns(10);
			}
			SqlDateModel model = new SqlDateModel();
			JDatePanelImpl datePanel = new JDatePanelImpl(model);
			datePicker = new JDatePickerImpl(datePanel);
			panel.add(datePicker, BorderLayout.SOUTH);
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.NORTH);
				panel_1.setLayout(new BorderLayout(0, 0));
				{
					textoRazonSocial = new JTextField();
					panel_1.add(textoRazonSocial, BorderLayout.CENTER);
					textoRazonSocial.setEditable(false);
					textoRazonSocial.setColumns(10);
				}
				{
					JButton btnBuscar = new JButton("Buscar");
					panel_1.add(btnBuscar, BorderLayout.EAST);
					btnBuscar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							VentanaBuscarCliente v= new VentanaBuscarCliente();
							v.setModal(true);
							v.setVisible(true);
							textoRazonSocial.setText(v.getRazon_Social());
							idCliente = v.getIdCliente();
						}
					});
				}
			}
			datePicker.getModel().setValue(null);
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.SOUTH);
			{
				JPanel buttonPane = new JPanel();
				panel.add(buttonPane);
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				{
					JButton okButton = new JButton("OK");
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(Pedido.pedidoValido(textoRazonSocial, datePicker))
							{
								Pedido.crearPedido(idCliente, textoDetalles, datePicker);
								setVisible(false);
								Tabla.vaciarTabla(ventanaAdministracion.getModelo());
								Tabla.actualizarPedidosAdministracion(ventanaAdministracion.getModelo());
							}
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
		
	}
}
