package Kontrolatzailea;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Bista.HasieraPanela;
import Bista.JokoarenPanela;
import Eredua.JokoKudeatzailea;

public class BotoiKontroladorea implements ActionListener {
	
	private HasieraPanela hp;

	public BotoiKontroladorea(HasieraPanela pHasiera) {
		this.hp = pHasiera;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String aukera = e.getActionCommand(); // Botoiaren testua jasotzen du (G, B, R)
		JokoKudeatzailea jk = JokoKudeatzailea.getNireJK();

		// 1. Kolorea esleitu jokalariaren aukeraren arabera (E12)
		if (aukera.equals("G")) {
			jk.setOntziKolorea(Color.GREEN);
		} else if (aukera.equals("B")) {
			jk.setOntziKolorea(Color.BLUE);
		} else if (aukera.equals("R")) {
			jk.setOntziKolorea(Color.RED);
		}

		// 2. Jokoa hasieratu ereduaren aldetik (E13)
		jk.hasiJokoa();

		// 3. Pantaila aldaketa (E14): Hasierakoa itxi eta jokoarena ireki
		hp.dispose(); // Hasierako JFrame-a ixten du
		
		JokoarenPanela jp = new JokoarenPanela();
		jp.setVisible(true);
		
		System.out.println("Jokoa hasi da " + aukera + " kolorearekin.");
	}
}