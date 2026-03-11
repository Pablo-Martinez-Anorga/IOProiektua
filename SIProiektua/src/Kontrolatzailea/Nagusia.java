package Kontrolatzailea;

import Bista.HasieraLeihoa; // Ziurtatu izena ondo idatzita dagoela
import Eredua.JokoKudeatzailea;

public class Nagusia {

	public static void main(String[] args) {
		// 1. Eredua hasieratu
		JokoKudeatzailea.getNireJK();

		// 2. Bista berria sortu
		HasieraLeihoa hl = new HasieraLeihoa();

		// 3. Kontrolatzailea sortu eta leihoa pasatu
		BotoiKontroladorea bk = new BotoiKontroladorea(hl);

		// 4. Lotu bista eta kontrolatzailea
		hl.setKontrolatzailea(bk);

		// 5. Erakutsi leihoa
		hl.setVisible(true);
	}
}