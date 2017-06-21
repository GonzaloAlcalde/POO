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
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Canvas;

public class VentanaInicio extends JFrame {

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

	/**
	 * Create the frame.
	 */
	public VentanaInicio() {
		setTitle("P\u00E9rez Hnos. - Bienvenido");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblPerezHermanos = new JLabel("\r\nP\u00E9rez Hermanos SA");
		lblPerezHermanos.setFont(new Font("Calibri", Font.BOLD, 20));
		lblPerezHermanos.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblPerezHermanos, BorderLayout.NORTH);
		
		JLabel lblDibujo = new JLabel("DIBUJO");
		contentPane.add(lblDibujo, BorderLayout.WEST);
		
		JLabel lblDibujo_1 = new JLabel("DIBUJO");
		contentPane.add(lblDibujo_1, BorderLayout.EAST);
		
		JPanel panelSeleccion = new JPanel();
		contentPane.add(panelSeleccion, BorderLayout.SOUTH);
		panelSeleccion.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnAdministracion = new JButton("Administraci\u00F3n\r\n");
		panelSeleccion.add(btnAdministracion);
		
		JButton btnOficinaTecnica = new JButton("Oficina T\u00E9cnica");
		panelSeleccion.add(btnOficinaTecnica);
		
		JButton btnProduccion = new JButton("Producci\u00F3n");
		panelSeleccion.add(btnProduccion);
		
		JButton btnDespacho = new JButton("Despacho\r\n");
		panelSeleccion.add(btnDespacho);
		
		JPanel panelCentral = new JPanel();
		panelCentral.setBorder(new LineBorder(new Color(0, 0, 0), 8));
		contentPane.add(panelCentral, BorderLayout.CENTER);
		
		Canvas canvas = new Canvas();
		panelCentral.add(canvas);
	}

}
