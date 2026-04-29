package Bista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Eredua.Partida;
import Kontrolatzailea.BotoiKontroladorea;

public class HasieraLeihoa extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnGreen;
	private JButton btnBlue;
	private JButton btnRed;
	
	// Elementu berriak jokalariarentzat
	private JTextField txtIzena; 

	public HasieraLeihoa() {
		setTitle("Space Invaders - Hasiera");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400); 
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		JPanel panelErdian = new JPanel();
		panelErdian.setLayout(new GridLayout(3, 1)); 
		
		// 1. Tituluaren panela
		JPanel tituluPanela = new JPanel();
		JLabel lblNewLabel = new JLabel("SPACE INVADERS");
		lblNewLabel.setFont(new Font("Monospaced", Font.BOLD, 50));
		tituluPanela.add(lblNewLabel);
		panelErdian.add(tituluPanela); 
		
		// 2. Jokalariaren datuak eta Ranking-a 
		JPanel datuPanela = new JPanel();
		datuPanela.setLayout(new GridLayout(1, 2)); 
		
		// 2.A Ezkerrean: Izena sartzeko koadroa
		JPanel izenPanela = new JPanel();
		izenPanela.add(new JLabel("Sartu zure izena: "));
		txtIzena = new JTextField(12); 
		izenPanela.add(txtIzena);
		datuPanela.add(izenPanela);
		
		// 2.B Eskuinean: Top jokalarien testua
		// Partidari eskatzen dio String-a 
		JTextArea txtTop = new JTextArea("--- TOP JOKALARIAK ---\n" + Partida.getNirePartida().getTopJokalariak());
		txtTop.setEditable(false);
		txtTop.setBackground(this.getBackground());
		datuPanela.add(txtTop);
		
		panelErdian.add(datuPanela);
		
		// 3. Botoien panela
		JPanel botoiPanela = new JPanel();
		btnGreen = new JButton("GREEN");
		btnGreen.setActionCommand("G");
		botoiPanela.add(btnGreen);
		
		btnBlue = new JButton("BLUE");
		btnBlue.setActionCommand("B");
		botoiPanela.add(btnBlue);
		
		btnRed = new JButton("RED");
		btnRed.setActionCommand("R");
		botoiPanela.add(btnRed);
		
		panelErdian.add(botoiPanela); 
		
		contentPane.add(panelErdian, BorderLayout.CENTER);
		
		this.setFocusable(true);
		Partida.getNirePartida().addObserver(this);
	}

	public void setKontrolatzailea(BotoiKontroladorea bk) {
		btnGreen.addActionListener(bk);
		btnBlue.addActionListener(bk);
		btnRed.addActionListener(bk);
		
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_G) btnGreen.doClick();
				if (e.getKeyCode() == KeyEvent.VK_B) btnBlue.doClick();
				if (e.getKeyCode() == KeyEvent.VK_R) btnRed.doClick();
			}
		});
	}
	
	// Kontrolatzaileak sartutako izena irakurtzeko
	public String getSartutakoIzena() {
		return txtIzena.getText();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String) {
			String mezua = (String) arg;
			if (mezua.equals("HASI")) {
				this.dispose(); 
			}
		}
	}
}