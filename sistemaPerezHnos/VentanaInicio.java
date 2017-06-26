package sistemaPerezHnos;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.awt.Canvas;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class VentanaInicio extends JFrame {

	private VentanaInicio home = this;
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicio frame = new VentanaInicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaInicio() {
		
		
		setTitle("P\u00E9rez Hnos. - Bienvenido");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblPerezHermanos = new JLabel("\r\nP\u00E9rez Hermanos SA");
		lblPerezHermanos.setFont(new Font("Calibri", Font.BOLD, 40));
		lblPerezHermanos.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblPerezHermanos);
		
//		JLabel lblDibujo = new JLabel("DIBUJO");
//		contentPane.add(lblDibujo, BorderLayout.WEST);
//		
//		JLabel lblDibujo_1 = new JLabel("DIBUJO");
//		contentPane.add(lblDibujo_1, BorderLayout.EAST);
		
		JPanel panelSeleccion = new JPanel();
		contentPane.add(panelSeleccion);
		panelSeleccion.setLayout(new GridLayout(2, 2));
		
		JButton btnAdministracion = new JButton("Administraci\u00F3n\r\n");
		btnAdministracion.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnAdministracion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				VentanaAdministracion t = new VentanaAdministracion(home);
				t.setVisible(true);
			}
		});
		panelSeleccion.add(btnAdministracion);
		
		JButton btnOficinaTecnica = new JButton("Oficina T\u00E9cnica");
		btnOficinaTecnica.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnOficinaTecnica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VentanaOficinaTecnica t = new VentanaOficinaTecnica(home);
				t.setVisible(true);
			}
		});
		panelSeleccion.add(btnOficinaTecnica);
		
		JButton btnProduccion = new JButton("Producci\u00F3n");
		btnProduccion.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnProduccion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VentanaProduccion t = new VentanaProduccion(home);
				t.setVisible(true);
			}
		});
		panelSeleccion.add(btnProduccion);
		
		JButton btnDespacho = new JButton("Despacho\r\n");
		btnDespacho.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnDespacho.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VentanaDespacho t = new VentanaDespacho(home);
				t.setVisible(true);
			}
		});
		panelSeleccion.add(btnDespacho);
		
		//JPanel panelCentral = new JPanel();
		//panelCentral.setBorder(new LineBorder(null, 0));
		//contentPane.add(panelCentral, BorderLayout.CENTER);
		
		//Canvas canvas = new Canvas();
		//panelCentral.add(canvas);
	}

}
