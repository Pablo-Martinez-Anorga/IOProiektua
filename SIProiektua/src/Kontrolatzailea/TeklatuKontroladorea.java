package Kontrolatzailea;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Eredua.Partida;

public class TeklatuKontroladorea extends KeyAdapter {
	
	//Metodoak
	@Override
	public void keyPressed(KeyEvent e) {
		int tekla = e.getKeyCode();
		//Espaziontzia mugitzeko
		if (tekla == KeyEvent.VK_RIGHT) {
			Partida.getNirePartida().mugituOntzia("Eskumara");
		}
		else if (tekla == KeyEvent.VK_LEFT) {
			Partida.getNirePartida().mugituOntzia("Ezkerrera");
		}
		else if (tekla == KeyEvent.VK_UP) {
			Partida.getNirePartida().mugituOntzia("Gora");
		}
		else if (tekla == KeyEvent.VK_DOWN) {
			Partida.getNirePartida().mugituOntzia("Behera");
		}
		//Tiro egiteko
		else if (tekla == KeyEvent.VK_SPACE) {
			Partida.getNirePartida().tiroEgin();
		}
		//Arma aldatzeko
		else if (tekla == KeyEvent.VK_C) {
			Partida.getNirePartida().aldatuArma();
		}
	}

}
