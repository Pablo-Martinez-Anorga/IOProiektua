package Kontrolatzailea;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Eredua.JokoKudeatzailea;

public class TeklatuKontroladorea extends KeyAdapter {
	
	//Metodoak
	@Override
	public void keyPressed(KeyEvent e) {
		int tekla = e.getKeyCode();
		//Espaziontzia mugitzeko
		if (tekla == KeyEvent.VK_RIGHT) {
			JokoKudeatzailea.getNireJK().mugituOntzia("Eskumara");
		}
		else if (tekla == KeyEvent.VK_LEFT) {
			JokoKudeatzailea.getNireJK().mugituOntzia("Ezkerrera");
		}
		else if (tekla == KeyEvent.VK_UP) {
			JokoKudeatzailea.getNireJK().mugituOntzia("Gora");
		}
		else if (tekla == KeyEvent.VK_DOWN) {
			JokoKudeatzailea.getNireJK().mugituOntzia("Behera");
		}
		//Tiro egiteko
		else if (tekla == KeyEvent.VK_SPACE) {
			JokoKudeatzailea.getNireJK().tiroEgin();
		}
	}

}
