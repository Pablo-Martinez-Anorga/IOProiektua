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
		String izena = leihoa.getSartutakoIzena();
		
		if (izena == null || izena.trim().isEmpty()) {
			izena = "Anonimoa";
		}
		
		// 2. Izena Ereduan ezarri (jokalari berria sortu)
		Partida.getNirePartida().ezarriUnekoJokalaria(izena);

		// 3. Jokoa kolorearen arabera hasi
		String aukera = e.getActionCommand(); 
		if ("G".equals(aukera)) {
			Partida.getNirePartida().hasiPartida("GREEN");
		} else if ("B".equals(aukera)) {
			Partida.getNirePartida().hasiPartida("BLUE");
		} else if ("R".equals(aukera)) {
			Partida.getNirePartida().hasiPartida("RED");
		}
	}
}