package Bista;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;

import Eredua.Gelaxka;
import Eredua.JokoKudeatzailea;

public class GelaxkaBista extends JLabel implements Observer {

	private static final long serialVersionUID = 1L;

	public GelaxkaBista() {
		this.setOpaque(true); 
		this.setBackground(Color.BLACK); 
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Gelaxka) {
			Gelaxka g = (Gelaxka) o;
			
			String mota = g.getEdukiaMota();
			
			if (mota.equals("HUTSA")) {
				this.setBackground(Color.BLACK);
			} else if (mota.equals("ESPAZIONTZIA")) {
				String kolorea = JokoKudeatzailea.getNireJK().getOntziKolorea();
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