package Kontrolatzailea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Eredua.Partida;

public class BotoiKontroladorea implements ActionListener {

	public BotoiKontroladorea() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    String aukera = e.getActionCommand(); 
	    
	    // Kontrolatzaileak kolorea pasatzen dio Partida-ri
	    if ("G".equals(aukera)) {
	        Partida.getNirePartida().hasiJokoa("GREEN");
	    } else if ("B".equals(aukera)) {
	        Partida.getNirePartida().hasiJokoa("BLUE");
	    } else if ("R".equals(aukera)) {
	        Partida.getNirePartida().hasiJokoa("RED");
	    }
	  
	}
}