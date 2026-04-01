package Bista;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;

import Eredua.Partida;

public class GelaxkaBista extends JLabel implements Observer {

	private static final long serialVersionUID = 1L;

	public GelaxkaBista() {
		this.setOpaque(true); // Kolorea ikusteko beharrezkoa
		this.setBackground(Color.BLACK); // Hasieran beltza
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String) {
			String mota = (String) arg;
			
			// Modeloaren egoeraren arabera kolorea aldatu.
			if (mota.equals("HUTSA")) {
				this.setBackground(Color.BLACK);
			} else if (mota.equals("ESPAZIONTZIA")) {
				// Aukeratutako kolorea (String) irakurri eta Color bihurtu bistan
				String kolorea = Partida.getNirePartida().getOntziKolorea();
				if ("RED".equals(kolorea)) {
					this.setBackground(Color.RED);
				} else if ("BLUE".equals(kolorea)) {
					this.setBackground(Color.BLUE);
				} else {
					this.setBackground(Color.GREEN);
				}
			} else if (mota.equals("ETSAIA")) {
				this.setBackground(Color.RED);
			} else if (mota.equals("TIROA")) {
				this.setBackground(Color.YELLOW);
			}
		}
	}
}