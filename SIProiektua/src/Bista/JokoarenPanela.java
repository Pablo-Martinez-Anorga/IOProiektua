package Bista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Eredua.JokoKudeatzailea;
import Kontrolatzailea.TeklatuKontroladorea;

import java.awt.GridLayout;

public class JokoarenPanela extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel palMatrizea;
	private JPanel[][] laukiak;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JokoarenPanela frame = new JokoarenPanela();
					frame.setVisible(true);
					//Jokoa hasieratu
					JokoKudeatzailea.getNireJK().hasiJokoa();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JokoarenPanela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		//Observer konektatu
		JokoKudeatzailea.getNireJK().addObserver(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPalMatrizea(), BorderLayout.CENTER);
		//Matrizea sortu
		matrizeaSortu(JokoKudeatzailea.getNireJK());
		//Teklatua konektatu
		TeklatuKontroladorea teklatua = new TeklatuKontroladorea();
		this.addKeyListener(teklatua);
		this.setFocusable(true); //Teklatua irakurtzeko
		this.requestFocusInWindow();
	}
	//Matrizea sortu
	private void matrizeaSortu(JokoKudeatzailea nireJK) {
		getPalMatrizea().setLayout(new GridLayout(60, 100, 0, 0));	
		laukiak = new JPanel[100][60];
		int[][] mat = nireJK.getMatrizea();
		
		//Ezkerretik eskumara eta goitik behera 
		for (int y=0; y < 60; y++){
			for (int x = 0; x < 100; x++) {
				JPanel laukia = new JPanel();
				//Hasierako koloreak jarri
				if (mat != null) {
					if (mat[x][y] == 1) {
						laukia.setBackground(Color.GREEN); // Ontzia
					} else if (mat[x][y] == 2) {
						laukia.setBackground(Color.RED);   // Etsaia
					} else if (mat[x][y] == 3) {
						laukia.setBackground(Color.YELLOW); // Tiroa
					} else {
						laukia.setBackground(Color.BLACK); // Espazio hutsa
					}
				} else {
					laukia.setBackground(Color.BLACK);
				}
				laukiak[x][y] = laukia;
				getPalMatrizea().add(laukia);
			}
		}
	}

	//Matrizea eguneratu
	@Override
	public void update(Observable o, Object arg) {
		int[][] mat = JokoKudeatzailea.getNireJK().getMatrizea();
		if (mat == null) return;
		
		for (int y = 0; y < 60; y++) {
			for (int x = 0; x < 100; x++) {
				if (mat[x][y] == 1) {
					laukiak[x][y].setBackground(Color.GREEN);
				} else if (mat[x][y] == 2) {
					laukiak[x][y].setBackground(Color.RED);
				} else if (mat[x][y] == 3) {
					laukiak[x][y].setBackground(Color.YELLOW);
				} else {
					laukiak[x][y].setBackground(Color.BLACK);
				}
			}
		}	
	}

	private JPanel getPalMatrizea() {
		if (palMatrizea == null) {
			palMatrizea = new JPanel();
			palMatrizea.setBackground(Color.BLACK);
		}
		return palMatrizea;
	}
}
