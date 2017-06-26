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
import java.awt.GridLayout;
import javax.swing.SwingConstants;

//Libreria del calendario -- http://www.java2s.com/Code/Jar/j/Downloadjdatepicker132jar.htm
//Tutorial -- http://www.codejava.net/java-se/swing/how-to-use-jdatepicker-to-display-calendar-component

public class VentanaPedidoNuevo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JDatePickerImpl datePicker;
	private int idCliente;

	private boolean pedidoValido()
	{
		boolean usuarioElegido = false;
		boolean fechaFueElegida = false;
		boolean fechaFutura = false;
		
		if (!(textField.getText().isEmpty()))
		{
			usuarioElegido = true;
		}
		if (datePicker.getModel().getValue() != null)
		{
			fechaFueElegida = true;
		}
		
		return (usuarioElegido && fechaFueElegida);
		
		
	}
	
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
			
			SqlDateModel model = new SqlDateModel();
			JDatePanelImpl datePanel = new JDatePanelImpl(model);
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			
			JLabel lblFecha = new JLabel("Fecha");
			lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(lblFecha);
			datePicker = new JDatePickerImpl(datePanel);
			datePicker.getModel().setValue(null);
			 
			panel.add(datePicker);
			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(pedidoValido())
						{
							Pedido.crearPedido(idCliente, textField_1, datePicker);
							setVisible(false);
							ventanaAdministracion.vaciarTabla();
							ventanaAdministracion.actualizarPedidos();
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
