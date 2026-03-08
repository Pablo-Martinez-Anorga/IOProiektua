package Kontrolatzailea;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame; // JFrame orokorra erabiliko dugu erroreak saihesteko
import Eredua.JokoKudeatzailea;
import Bista.JokoarenPanela;

public class BotoiKontroladorea implements ActionListener {
	
	private JFrame leihoa; // JFrame denez, HasieraLehioa onartuko du arazorik gabe

	// Konstruktore honek JFrame bat jasotzen du (zure HasieraLehioa JFrame bat da)
	public BotoiKontroladorea(JFrame pLeihoa) {
		this.leihoa = pLeihoa;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String aukera = e.getActionCommand(); 
		JokoKudeatzailea jk = JokoKudeatzailea.getNireJK();

		if ("G".equals(aukera)) {
			jk.setOntziKolorea(Color.GREEN);
		} else if ("B".equals(aukera)) {
			jk.setOntziKolorea(Color.BLUE);
		} else if ("R".equals(aukera)) {
			jk.setOntziKolorea(Color.RED);
		}

		jk.hasiJokoa();

		// Hasierako leihoa itxi
		if (leihoa != null) {
			leihoa.dispose();
		}
		
		// Jokoaren bista ireki
		JokoarenPanela jp = new JokoarenPanela();
		jp.setVisible(true);
		
		System.out.println("Jokoa hasi da " + aukera + " kolorearekin.");
	}
}