package sistemaPerezHnos;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaDespacho extends JFrame {
	
	private VentanaInicio home;

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public VentanaDespacho(VentanaInicio inicio) {
		setTitle("P\u00E9rez Hnos. - Despacho");
		
		home = inicio;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				home.setVisible(true);
				setVisible(false);
			}
		});
		contentPane.add(btnVolver, BorderLayout.CENTER);
	}

}
