package Bista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Eredua.Partida;
import Kontrolatzailea.BotoiKontroladorea;

public class HasieraLeihoa extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnGreen, btnBlue, btnRed, btnExit;
	private JTextField txtIzena; 

	public HasieraLeihoa() {
		setTitle("Space Invaders - Hasiera");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500); 
		setLocationRelativeTo(null); 
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK); 
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.setLayout(new BorderLayout(0, 20));
		setContentPane(contentPane);
		
		// 1. Titulua
		JPanel tituluPanela = new JPanel();
		tituluPanela.setBackground(Color.BLACK);
		JLabel lblIzenburua = new JLabel("SPACE INVADERS");
		lblIzenburua.setForeground(Color.GREEN);
		lblIzenburua.setFont(new Font(Font.MONOSPACED, Font.BOLD, 55));
		tituluPanela.add(lblIzenburua);
		contentPane.add(tituluPanela, BorderLayout.NORTH);
		
		
		JPanel panelErdian = new JPanel();
		panelErdian.setBackground(Color.BLACK);
		panelErdian.setLayout(new GridLayout(2, 1, 0, 20)); 
		
		// 2. Izena eta Ranking-a 
		JPanel datuPanela = new JPanel();
		datuPanela.setBackground(Color.BLACK);
		datuPanela.setLayout(new GridLayout(1, 2, 30, 0)); 
		
		// 2.1 Izenaren Panela
		JPanel izenPanela = new JPanel();
		izenPanela.setBackground(Color.BLACK);
		izenPanela.setLayout(new BoxLayout(izenPanela, BoxLayout.Y_AXIS));
		
		JLabel lblIzena = new JLabel("SARTU ZURE IZENA:");
		lblIzena.setForeground(Color.WHITE);
		lblIzena.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
		lblIzena.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		txtIzena = new JTextField(12);
		txtIzena.setMaximumSize(new Dimension(250, 40)); 
		txtIzena.setBackground(Color.DARK_GRAY);
		txtIzena.setForeground(Color.GREEN); 
		txtIzena.setCaretColor(Color.WHITE); 
		txtIzena.setFont(new Font(Font.MONOSPACED, Font.BOLD, 22));
		txtIzena.setHorizontalAlignment(JTextField.CENTER);
		txtIzena.setBorder(new LineBorder(Color.GREEN, 2)); 
		txtIzena.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		izenPanela.add(Box.createVerticalGlue()); 
		izenPanela.add(lblIzena);
		izenPanela.add(Box.createVerticalStrut(10)); 
		izenPanela.add(txtIzena);
		izenPanela.add(Box.createVerticalGlue());
		datuPanela.add(izenPanela);
		
		// 2.2 Ranking Panela
		JTextArea txtTop = new JTextArea("--- TOP JOKALARIAK ---\n\n" + Partida.getNirePartida().getTopJokalariak());
		txtTop.setEditable(false);
		txtTop.setBackground(Color.BLACK);
		txtTop.setForeground(Color.YELLOW); 
		txtTop.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
		txtTop.setBorder(new LineBorder(Color.YELLOW, 2)); 
		txtTop.setMargin(new Insets(10, 10, 10, 10)); 
		datuPanela.add(txtTop);
		
		panelErdian.add(datuPanela);
		
		// 3. Instrukzioak
		JPanel instrukzioPanela = new JPanel();
        instrukzioPanela.setBackground(Color.BLACK);
        instrukzioPanela.setLayout(new BoxLayout(instrukzioPanela, BoxLayout.Y_AXIS));
        
        JLabel lblMugitu = new JLabel("Press <Arrows> to move");
        lblMugitu.setForeground(Color.WHITE);
        lblMugitu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
        lblMugitu.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblTiro = new JLabel("Press <Space> to shoot");
        lblTiro.setForeground(Color.WHITE);
        lblTiro.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
        lblTiro.setAlignmentX(Component.CENTER_ALIGNMENT);

        instrukzioPanela.add(Box.createVerticalGlue());
        instrukzioPanela.add(lblMugitu);
        instrukzioPanela.add(Box.createVerticalStrut(10));
        instrukzioPanela.add(lblTiro);
        instrukzioPanela.add(Box.createVerticalGlue());
        
        panelErdian.add(instrukzioPanela);
		contentPane.add(panelErdian, BorderLayout.CENTER);
		
		// 4. Botoiak 
		JPanel botoiPanela = new JPanel();
		botoiPanela.setBackground(Color.BLACK);
		
		btnGreen = sortuBotoiRetro("GREEN", Color.GREEN);
		btnGreen.setActionCommand("G"); 
		
		btnBlue = sortuBotoiRetro("BLUE", new Color(0, 150, 255)); 
		btnBlue.setActionCommand("B"); 
		
		btnRed = sortuBotoiRetro("RED", Color.RED);
		btnRed.setActionCommand("R"); 
		
		btnExit = sortuBotoiRetro("IRTEN", Color.GRAY);
		btnExit.setActionCommand("EXIT");
		
		botoiPanela.add(btnGreen);
		botoiPanela.add(btnBlue);
		botoiPanela.add(btnRed);
		botoiPanela.add(btnExit);
		
		contentPane.add(botoiPanela, BorderLayout.SOUTH); 
		
		this.setFocusable(true);
		Partida.getNirePartida().addObserver(this);
	}

	// --- METODO LAGUNTZAILEA BOTOIAK ESTILIZATZEKO ---
	private JButton sortuBotoiRetro(String testua, Color kolorea) {
        JButton botoia = new JButton(testua);
        botoia.setFont(new Font(Font.MONOSPACED, Font.BOLD, 22));
        botoia.setForeground(kolorea);
        botoia.setBackground(Color.DARK_GRAY);
        botoia.setFocusPainted(false); 
        botoia.setBorder(new LineBorder(kolorea, 3)); 
        botoia.setPreferredSize(new Dimension(130, 50));
        botoia.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        return botoia;
    }

	public void setKontrolatzailea(BotoiKontroladorea bk) {
		btnGreen.addActionListener(bk);
		btnBlue.addActionListener(bk);
		btnRed.addActionListener(bk);
		btnExit.addActionListener(bk);
		
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_G) btnGreen.doClick();
				if (e.getKeyCode() == KeyEvent.VK_B) btnBlue.doClick();
				if (e.getKeyCode() == KeyEvent.VK_R) btnRed.doClick();
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) btnExit.doClick();
			}
		});
	}
	
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