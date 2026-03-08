package Bista;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Kontrolatzailea.BotoiKontroladorea;

public class HasieraLeihoa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	// GAKOA: Botoiak hemen kanpoan egon behar dira atributu gisa!
	private JButton btnGreen;
	private JButton btnBlue;
	private JButton btnRed;

	public HasieraLeihoa() {
		setTitle("Space Invaders - Hasiera");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SPACE INVADERS");
		lblNewLabel.setBounds(40, 11, 356, 57);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
		contentPane.add(lblNewLabel);
		
		// KONTUZ: Hemen ez dugu "JButton btnGreen" jartzen, "btnGreen" bakarrik (lehen definitu dugulako)
		btnGreen = new JButton("GREEN");
		btnGreen.setActionCommand("G");
		btnGreen.setBounds(40, 157, 98, 33);
		contentPane.add(btnGreen);
		
		btnBlue = new JButton("BLUE");
		btnBlue.setActionCommand("B");
		btnBlue.setBounds(173, 157, 98, 33);
		contentPane.add(btnBlue);
		
		btnRed = new JButton("RED");
		btnRed.setActionCommand("R");
		btnRed.setBounds(306, 157, 98, 33);
		contentPane.add(btnRed);
		
		// Teklatuak funtziona dezan
		this.setFocusable(true);
	}

	public void setKontrolatzailea(BotoiKontroladorea bk) {
		// Orain bai, botoiak "ikusgarriak" dira metodo honentzat
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
}