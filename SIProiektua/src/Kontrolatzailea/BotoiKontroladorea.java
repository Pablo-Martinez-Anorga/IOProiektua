package Kontrolatzailea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Bista.HasieraLeihoa;
import Eredua.Partida;

public class BotoiKontroladorea implements ActionListener {

	private HasieraLeihoa leihoa; 

	public BotoiKontroladorea(HasieraLeihoa leihoa) {
		this.leihoa = leihoa;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String aukera = e.getActionCommand(); 
		
		// 1. Jokoa itxi nahi badu, erabat itxi aplikazioa
		if ("EXIT".equals(aukera)) {
			System.exit(0);
			return;
		}
		
		// 2. Izena lortu eta ezarri
		String izena = leihoa.getSartutakoIzena();
		if (izena == null || izena.trim().isEmpty()) {
			izena = "Anonimoa";
		}
		Partida.getNirePartida().ezarriUnekoJokalaria(izena);

		// 3. Partida hasi kolorearen arabera
		if ("G".equals(aukera)) {
			Partida.getNirePartida().hasiPartida("GREEN");
		} else if ("B".equals(aukera)) {
			Partida.getNirePartida().hasiPartida("BLUE");
		} else if ("R".equals(aukera)) {
			Partida.getNirePartida().hasiPartida("RED");
		}
	}
}