package sistemaPerezHnos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class VentanaBuscarCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtBuscar;
	private JTable tabla;
	
	private String razon_social;
	private int idCliente;
	
	public String getRazon_Social(){
		return razon_social;
	}
	
	public int getIdCliente(){
		return idCliente;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentanaBuscarCliente dialog = new VentanaBuscarCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VentanaBuscarCliente() {

		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPanel.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			JButton okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cerrarRetornarResultado();
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
		
		txtBuscar = new JTextField();
		panel.add(txtBuscar);
		txtBuscar.setColumns(30);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel modelo = Cliente.buscar(txtBuscar.getText());
				
				tabla.setModel(modelo);
			}
		});
		panel.add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane, BorderLayout.CENTER);
		
		tabla = new JTable();
		
		tabla.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if (e.getClickCount()==2){
					cerrarRetornarResultado();
				
				}
			}
		});
		
		scrollPane.setViewportView(tabla);

	}
	
	private void cerrarRetornarResultado(){
		
		int row = tabla.getSelectedRow();
		//int column = tabla.getSelectedColumn();
		
		DefaultTableModel modelo = (DefaultTableModel)tabla.getModel();
		this.razon_social = (String)modelo.getValueAt(row, 1);
		this.idCliente = (int)modelo.getValueAt(row, 0);
		
		setVisible(false);
	}

}
