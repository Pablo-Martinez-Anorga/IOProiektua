package Bista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Eredua.Entitatea;
import Eredua.Espaziontzia;
import Eredua.Etsaia;
import Eredua.JokoKudeatzailea;
import Eredua.Tableroa;
import Eredua.Tiroa;
import Kontrolatzailea.TeklatuKontroladorea;

import java.awt.GridLayout;

public class JokoarenPanela extends JFrame implements Observer {

	//Atributuak
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnlMatrizea;
	private JPanel[][] laukiak;

	//Eraikitzailea
	public JokoarenPanela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnlMatrizea(), BorderLayout.CENTER);
		//Matrizea sortu
		matrizeaSortu();
		//Observer konektatu 
		JokoKudeatzailea.getNireJK().getTableroa().addObserver(this);
		//Teklatua konektatu
		TeklatuKontroladorea teklatua = new TeklatuKontroladorea();
		this.addKeyListener(teklatua);
		this.setFocusable(true); //Teklatua irakurtzeko
		this.requestFocusInWindow();
	}
	
	//Metodoak
	//Matrizea sortu
	private void matrizeaSortu() {
		getPnlMatrizea().setLayout(new GridLayout(60, 100, 0, 0));	
		laukiak = new JPanel[100][60];
		Tableroa t = JokoKudeatzailea.getNireJK().getTableroa();
		
		//Ezkerretik eskumara eta goitik behera 
		for (int y=0; y < 60; y++){
			for (int x = 0; x < 100; x++) {
				JPanel laukia = new JPanel();
				Entitatea e = t.getEntitatea(x, y);
				koloreaJarri(laukia, e);
				laukiak[x][y] = laukia;
				getPnlMatrizea().add(laukia);
			}
		}
	}

	private void koloreaJarri(JPanel laukia, Entitatea e) {
		if (e instanceof Espaziontzia) {
			laukia.setBackground(JokoKudeatzailea.getNireJK().getOntziKolorea());
		} else if (e instanceof Etsaia) {
			laukia.setBackground(Color.RED);
		} else if (e instanceof Tiroa) {
			laukia.setBackground(Color.YELLOW);
		} else {
			laukia.setBackground(Color.BLACK);
		}
		
	}
	
	//Matrizea eguneratu
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Tableroa) {
	        Tableroa t = (Tableroa) o;
			for (int y = 0; y < 60; y++) {
				for (int x = 0; x < 100; x++) {
					Entitatea e = t.getEntitatea(x, y);
					koloreaJarri(laukiak[x][y], e);
				}
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
