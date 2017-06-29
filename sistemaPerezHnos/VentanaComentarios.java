package sistemaPerezHnos;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaComentarios extends JFrame {

	private JPanel contentPane;
	private JFrame home;
	
	private VentanaComentarios thisWindow= this;
	
	private DefaultTableModel modelo;
	private JTable table;
	
	public DefaultTableModel getModelo() {
		return modelo;
	}	
	
	public VentanaComentarios(JFrame ventanaPadre, Integer idPedido, String sector) {
		
		home = ventanaPadre;
		home.setVisible(false);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnAgregarComentario = new JButton("Agregar Comentario");
		btnAgregarComentario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaAgregarComentario v = new VentanaAgregarComentario(idPedido, thisWindow, sector);
				v.setVisible(true);
				
			}
		});
		panel.add(btnAgregarComentario);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
				home.setVisible(true);
			}
		});
		panel.add(btnAtras);
		
		modelo = new DefaultTableModel(){
			 @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		modelo.addColumn("Comentarios");
		modelo.addColumn("Sector");
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(modelo);
		scrollPane.setViewportView(table);
		
		Tabla.actualizarComentarios(idPedido, modelo);
	}
}
