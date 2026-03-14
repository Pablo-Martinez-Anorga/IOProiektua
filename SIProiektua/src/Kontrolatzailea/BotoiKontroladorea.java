package Kontrolatzailea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Eredua.Partida;

public class BotoiKontroladorea implements ActionListener {
	
	//private JFrame leihoa;

	public BotoiKontroladorea() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    String aukera = e.getActionCommand(); 
	    
	    // El Controlador le pasa el color elegido directamente a Partida
	    if ("G".equals(aukera)) {
	        Partida.getNirePartida().hasiJokoa("GREEN");
	    } else if ("B".equals(aukera)) {
	        Partida.getNirePartida().hasiJokoa("BLUE");
	    } else if ("R".equals(aukera)) {
	        Partida.getNirePartida().hasiJokoa("RED");
	    }
	    /*
	    // Hasierako leihoa itxi
	    if (leihoa != null) {
	        leihoa.dispose();
	    }
	    
	    // Jokoaren bista ireki
	    JokoarenPanela jp = new JokoarenPanela();
	    jp.setVisible(true);
	}
	*/
	}
}