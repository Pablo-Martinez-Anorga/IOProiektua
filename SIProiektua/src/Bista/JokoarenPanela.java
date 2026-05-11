package Bista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import Eredua.Partida;
import Kontrolatzailea.BotoiKontroladorea;
import Kontrolatzailea.TeklatuKontroladorea;

public class JokoarenPanela extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnlMatrizea;
	private JLabel lblPuntuak; 

	public JokoarenPanela() {
		setTitle("Space Invaders");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		// --- GOIKO PANELA (Puntuak eta botoia) ---
		JPanel goikoPanela = new JPanel();
		goikoPanela.setLayout(new BorderLayout());
		
		lblPuntuak = new JLabel("Puntuazioa: 0");
		lblPuntuak.setFont(new Font("Monospaced", Font.BOLD, 20));
		goikoPanela.add(lblPuntuak, BorderLayout.WEST);
		
		JButton btnMenura = new JButton("Jokoa Utzi (Menura)");
		btnMenura.setFocusable(false); // Teklatuaren fokua ez galtzeko
		btnMenura.addActionListener(e -> {
			Partida.getNirePartida().amaituJokoa(false); 
		});
		goikoPanela.add(btnMenura, BorderLayout.EAST);
		
		contentPane.add(goikoPanela, BorderLayout.NORTH);
		// -----------------------------------------
		
		contentPane.add(getPnlMatrizea(), BorderLayout.CENTER);
		
		Partida.getNirePartida().addObserver(this);
		this.addKeyListener(new TeklatuKontroladorea());
		this.setFocusable(true);
		this.requestFocusInWindow();
	}

	private JPanel getPnlMatrizea() {
		if (pnlMatrizea == null) {
			pnlMatrizea = new JPanel();
			pnlMatrizea.setLayout(new GridLayout(60, 100, 0, 0));
			pnlMatrizea.setBackground(Color.BLACK);
		}
		return pnlMatrizea;
	}

	private void matrizeaSortu() {
		getPnlMatrizea().removeAll();
		for (int y = 0; y < 60; y++) {
			for (int x = 0; x < 100; x++) {
				GelaxkaBista bistaGelaxka = new GelaxkaBista();
				Partida.getNirePartida().getGelaxka(x, y).addObserver(bistaGelaxka);
				getPnlMatrizea().add(bistaGelaxka);
			}
		}
		getPnlMatrizea().revalidate();
		getPnlMatrizea().repaint();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String) {
			String mezua = (String) arg;
			
			if (mezua.equals("HASI")) {
				matrizeaSortu(); 
				this.setVisible(true);
				this.requestFocusInWindow();
			} else if (mezua.equals("PUNTUAK_ALDATU")) {
				lblPuntuak.setText("Puntuazioa: " + Partida.getNirePartida().getUnekoPuntuazioa());
			} else if (mezua.equals("GALDU") || mezua.equals("IRABAZI")) {
				SwingUtilities.invokeLater(() -> {
					String texto = mezua.equals("GALDU") ? "GALDU DUZU!" : "ZORIONAK! IRABAZI DUZU!";
					JOptionPane.showMessageDialog(this, texto, "Partida Amaiera", JOptionPane.INFORMATION_MESSAGE);
					this.dispose(); 
					menuraItzuli(); 
				});
			}
		}
	}
	
	private void menuraItzuli() {
		HasieraLeihoa hl = new HasieraLeihoa();
		BotoiKontroladorea bk = new BotoiKontroladorea(hl);
		hl.setKontrolatzailea(bk);
		hl.setVisible(true);
	}
}