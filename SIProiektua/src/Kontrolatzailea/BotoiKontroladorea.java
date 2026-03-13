package Kontrolatzailea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Eredua.Partida;

public class BotoiKontroladorea implements ActionListener {

	// Ereduari bakarrik deitu, bista kudeatu gabe
	@Override
	public void actionPerformed(ActionEvent e) {
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