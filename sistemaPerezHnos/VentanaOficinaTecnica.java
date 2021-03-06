package sistemaPerezHnos;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaOficinaTecnica extends JFrame {

	private VentanaInicio home;

	private VentanaOficinaTecnica thisWindow = this;
	
	private JPanel contentPane;
	private JPanel panel;
	private JButton btnAsignarMaquina;
	private DefaultTableModel modelo;
	private JScrollPane scrollPane;
	private JTable tablaPedidos;
	private JPanel panel_1;
	private JButton btnComentarios;
	
	private String sector = "Oficina_tecnica";
	
	public String getSector(){
		return sector;
	}
	
	public VentanaOficinaTecnica(VentanaInicio inicio) {
		setTitle("P\u00E9rez Hnos. - Oficina T\u00E9cnica");
		
		home = inicio;
		home.setVisible(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.EAST);
		panel.setLayout(new BorderLayout(0, 0));
		
		modelo = new DefaultTableModel(){
			 @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		modelo.addColumn("idPedido");
		modelo.addColumn("Razon social");
		modelo.addColumn("Detalles");
		modelo.addColumn("Fecha");
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException e){
			e.printStackTrace();
		}
		
		Tabla.actualizarPedidosOficinaTecnica(modelo);
		
		btnAsignarMaquina = new JButton("Asignar Maquina");
		btnAsignarMaquina.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(tablaPedidos.getSelectedRow() != -1)
				{
					int fila = tablaPedidos.getSelectedRow();

					DefaultTableModel modelo = (DefaultTableModel)tablaPedidos.getModel();
					Integer idPedido = (Integer) modelo.getValueAt(fila, 0);
					VentanaAsignarMaquina v = new VentanaAsignarMaquina(idPedido);
					v.setVisible(true);
					
					Tabla.vaciarTabla(modelo);
					Tabla.actualizarPedidosOficinaTecnica(modelo);
				}
			}
		});
		panel.add(btnAsignarMaquina, BorderLayout.NORTH);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				home.setVisible(true);
				setVisible(false);
			}
		});
		
		panel.add(btnVolver, BorderLayout.SOUTH);
		
		panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		btnComentarios = new JButton("Comentarios");
		btnComentarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tablaPedidos.getSelectedRow() != -1){
					int row = tablaPedidos.getSelectedRow();
					DefaultTableModel modelo = (DefaultTableModel)tablaPedidos.getModel();
					Integer idPedido = (Integer) modelo.getValueAt(row, 0);
					VentanaComentarios v = new VentanaComentarios(thisWindow, idPedido, thisWindow.getSector());
					v.setVisible(true);
				}
			}
		});
		panel_1.add(btnComentarios, BorderLayout.NORTH);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		tablaPedidos = new JTable();
		
		tablaPedidos.setModel(modelo);
		scrollPane.setViewportView(tablaPedidos);
	}

}
