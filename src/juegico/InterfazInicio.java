package juegico;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.google.common.base.Preconditions;

public class InterfazInicio extends JFrame {
	private static final long serialVersionUID = 1L;
	private static Integer nBotones;
	private JTextField t;

	public InterfazInicio() {
		super("Button Crasher");
		setLayout(new FlowLayout());
		JLabel l = new JLabel("Introduzca el número de botones");
		t = new JTextField(10);
		JButton b = new JButton("OK");
		add(l);
		add(t);
		add(b);
		setLocation(300, 300);
		setSize(300, 150);
		setVisible(true);
		b.addActionListener(new AccionOK());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public Integer getNBotones() {
		return nBotones;
	}

	public static void main(String[] argument) {
		new InterfazInicio();
	}

	private class AccionOK implements ActionListener {

		public void actionPerformed(ActionEvent ae) {
			try {
				nBotones = new Integer(t.getText());
				Preconditions.checkArgument(nBotones > 2 && nBotones <= 30
						&& nBotones % 2 == 0);
				dispose();
				new VentanaPrincipalMulti(nBotones);
			} catch (NumberFormatException e) {
			} catch (IllegalArgumentException e) {
			}
		}
	}
}
