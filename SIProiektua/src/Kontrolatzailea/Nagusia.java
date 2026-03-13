package Kontrolatzailea;

import Bista.HasieraLeihoa;

public class Nagusia {

	public static void main(String[] args) {
		HasieraLeihoa hl = new HasieraLeihoa();
		BotoiKontroladorea bk = new BotoiKontroladorea();
		hl.setKontrolatzailea(bk);
		hl.setVisible(true);
	}

}