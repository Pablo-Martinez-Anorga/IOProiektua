package Bista;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;

import Eredua.Gelaxka;
import Eredua.Espaziontzia;
import Eredua.Etsaia;
import Eredua.Tiroa;
import Eredua.JokoKudeatzailea;

public class GelaxkaBista extends JLabel implements Observer {

	private static final long serialVersionUID = 1L;

	public GelaxkaBista() {
		this.setOpaque(true); // Kolorea ikusteko beharrezkoa
		this.setBackground(Color.BLACK); // Hasieran beltza
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Gelaxka) {
			Gelaxka g = (Gelaxka) o;
			
			// Modeloaren egoeraren arabera kolorea aldatu
			if (g.isHutsik()) {
				this.setBackground(Color.BLACK);
			} else if (g.getEntitatea() instanceof Espaziontzia) {
				// Aukeratutako kolorea (String) irakurri eta Color bihurtu bistan
				String kolorea = JokoKudeatzailea.getNireJK().getOntziKolorea();
				if ("RED".equals(kolorea)) {
					this.setBackground(Color.RED);
				} else if ("BLUE".equals(kolorea)) {
					this.setBackground(Color.BLUE);
				} else {
					this.setBackground(Color.GREEN);
				}
			} else if (g.getEntitatea() instanceof Etsaia) {
				this.setBackground(Color.RED);
			} else if (g.getEntitatea() instanceof Tiroa) {
				this.setBackground(Color.YELLOW);
			}
		}
	}
}