package juegico;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import com.google.common.collect.Lists;

public class VentanaPrincipalMulti extends JFrame {
	private static final long serialVersionUID = 1L;
	private final Integer DIFICULTAD = 500, DELAY = 1500;

	private JButton[] botones;
	private Integer puntuacion = 0, botonRojo, botonDaPorCulo, nBotones;
	private Timer temp;
	private Random generador = new Random();
	private List<Color> colores = colores();
	private Integer pulsable = generador.nextInt(colores.size());
	private JLabel label;
	private JFrame pantalla;

	public VentanaPrincipalMulti(Integer nBotones) {
		super("Juegico");
		this.nBotones = nBotones;
		JOptionPane.showMessageDialog(this, "¡Pulsa el botón "
				+ ColorToString(colores.get(pulsable)) + "!");
		setLayout(new GridLayout(0, (int) Math.sqrt(nBotones)));
		botones = new JButton[nBotones];
		AccionClick ac = new AccionClick();
		for (int i = 0; i < nBotones; i++) {
			botones[i] = new JButton();
			botones[i].addActionListener(ac);
			add(botones[i]);
		}
		temp = new Timer(DELAY, new AccionColor());

		pantalla = new JFrame();
		pantalla.setLayout(new FlowLayout());
		pantalla.setLocation(600, 300);
		label = new JLabel("¡Ahora pulsa el botón "
				+ ColorToString(colores.get(pulsable)) + "!");
		pantalla.add(label);
		pantalla.pack();
		pantalla.setVisible(true);
		pantalla.setDefaultCloseOperation(EXIT_ON_CLOSE);

		setSize(600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		temp.start();
	}

	private class AccionClick implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			if (b.getBackground().equals(colores.get(pulsable))) {
				puntuacion++;
				b.setBackground(null);
				setTitle("Juegico: " + puntuacion);
			} else {
				fin();
			}

		}
	}

	private class AccionColor implements ActionListener {
		private boolean state = false;

		public void actionPerformed(ActionEvent e) {
			if (state) {
				state = false;
				if (botonRojo == null
						|| !botones[botonRojo].getBackground().equals(
								colores.get(pulsable))) {
					do {
						botonRojo = generador.nextInt(nBotones);
					} while (botonDaPorCulo == botonRojo);

					botones[botonRojo].setBackground(colores.get(pulsable));
					if (temp.getDelay() > DIFICULTAD)
						temp.setDelay((int) (temp.getDelay() * 0.97));

				} else {
					botones[botonRojo].setBackground(null);
					fin();
				}

			} else {
				state = true;
				if (puntuacion > 10) {
					do {
						botonDaPorCulo = generador.nextInt(nBotones);
					} while (botonDaPorCulo == botonRojo);
					Color col;
					do {
						Float a = generador.nextFloat();
						Float b = generador.nextFloat();
						Float c = generador.nextFloat();
						col = new Color(a, b, c);
					} while (col.equals(colores.get(pulsable)));
					botones[botonDaPorCulo].setBackground(col);

					if (generador.nextInt(100) > 90) {
						for (JButton b : botones)
							b.setBackground(null);
						pulsable = generador.nextInt(colores.size());
						label.setText("¡Ahora pulsa el botón "
								+ ColorToString(colores.get(pulsable)) + "!");
						pantalla.getContentPane().setBackground(
								colores.get(pulsable));
					}
				}
			}
		}
	}

	private List<Color> colores() {
		List<Color> res = Lists.newArrayList();
		res.add(Color.RED);
		res.add(Color.BLACK);
		res.add(Color.BLUE);
		res.add(Color.GREEN);
		res.add(Color.ORANGE);
		res.add(Color.WHITE);
		res.add(Color.PINK);
		res.add(Color.YELLOW);

		return res;
	}

	private void fin() {
		temp.stop();
		JOptionPane.showMessageDialog(this, "Game Over. Tu puntuación es: "
				+ puntuacion);
		int again = JOptionPane.showConfirmDialog(this, "Try again?", null,
				JOptionPane.YES_NO_OPTION);
		if (again == JOptionPane.YES_OPTION) {
			dispose();
			pantalla.dispose();
			new InterfazInicio();
		} else
			System.exit(0);
	}

	public static String ColorToString(Color c) {
		String res = "";
		if (c.equals(Color.RED))
			res = "rojo";
		else if (c.equals(Color.BLACK))
			res = "negro";
		else if (c.equals(Color.BLUE))
			res = "azul";
		else if (c.equals(Color.GREEN))
			res = "verde";
		else if (c.equals(Color.ORANGE))
			res = "naranja";
		else if (c.equals(Color.WHITE))
			res = "blanco";
		else if (c.equals(Color.PINK))
			res = "rosa";
		else if (c.equals(Color.YELLOW))
			res = "amarillo";

		return res;
	}

}
