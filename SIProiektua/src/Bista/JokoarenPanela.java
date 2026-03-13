package Bista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import Eredua.JokoKudeatzailea;
import Eredua.Partida;
import Eredua.Gelaxka;
import Kontrolatzailea.TeklatuKontroladorea;

import java.awt.GridLayout;

public class JokoarenPanela extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnlMatrizea;

	public JokoarenPanela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnlMatrizea(), BorderLayout.CENTER);
		
		// Matrizea sortu eta Ereduarekin lotu
		matrizeaSortu();
		
		// Partida behatu
		Partida.getNirePartida().addObserver(this);
		// Teklatua konektatu
		TeklatuKontroladorea teklatua = new TeklatuKontroladorea();
		this.addKeyListener(teklatua);
		this.setFocusable(true); 
		this.requestFocusInWindow();
	}
	
	private void matrizeaSortu() {
		getPnlMatrizea().setLayout(new GridLayout(60, 100, 0, 0));	
		
		// Goitik behera eta ezkerretik eskumara (GridLayout ordenan)
		for (int y = 0; y < 60; y++) {
			for (int x = 0; x < 100; x++) {
				// Bistako gelaxka sortu
				GelaxkaBista bistaGelaxka = new GelaxkaBista();
				// Ereduko gelaxka lortu
				Gelaxka ereduGelaxka = JokoKudeatzailea.getNireJK().getGelaxka(x, y);
				
				// Bistako JLabel-ak Ereduko Gelaxka behatuko du
				ereduGelaxka.addObserver(bistaGelaxka);
				
				// Panelera gehitu
				getPnlMatrizea().add(bistaGelaxka);
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// JokoarenPanela bakarrik JokoKudeatzailearen mezuak jasotzen ditu (Galdu/Irabazi)
		if (arg instanceof String) {
			String mezua = (String) arg;
			if (mezua.equals("GALDU")) {
				JOptionPane.showMessageDialog(this, "GALDU DUZU!", "Game Over", JOptionPane.ERROR_MESSAGE);
				this.dispose(); // Dena itxi eta garbitu
			} else if (mezua.equals("IRABAZI")) {
				JOptionPane.showMessageDialog(this, "ZORIONAK! IRABAZI DUZU!", "Garaipena", JOptionPane.INFORMATION_MESSAGE);
				this.dispose(); 
			}
		}
	}	

	private JPanel getPnlMatrizea() {
		if (pnlMatrizea == null) {
			pnlMatrizea = new JPanel();
			pnlMatrizea.setBackground(Color.BLACK);
		}
		return pnlMatrizea;
	}
}