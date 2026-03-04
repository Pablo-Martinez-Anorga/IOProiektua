package Kontrolatzailea;

import Bista.JokoarenPanela;
import Eredua.JokoKudeatzailea;

public class Nagusia {
	public static void main(String[] args) {
		// EREDUA
		JokoKudeatzailea jk = JokoKudeatzailea.getNireJK();
		
		// BISTA
		JokoarenPanela jp = new JokoarenPanela();
		jp.setVisible(true);

		//Jokoa hasi
		jk.hasiJokoa();
	}

}
