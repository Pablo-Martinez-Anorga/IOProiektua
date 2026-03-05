package Bista;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import Kontrolatzailea.BotoiKontroladorea;

public class HasieraPanela extends JFrame {

    private JButton btnG, btnB, btnR;

    public HasieraPanela() {
        // Leihoaren ezarpenak
        setTitle("Space Invaders - Hasiera");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setLayout(new BorderLayout());

        // Titulua (E11)
        JLabel lblTitulua = new JLabel("SPACE INVADERS", SwingConstants.CENTER);
        lblTitulua.setFont(new Font("Arial", Font.BOLD, 30));
        add(lblTitulua, BorderLayout.NORTH);

        // Botoien panela (E12)
        JPanel pnlBotoiak = new JPanel();
        pnlBotoiak.setLayout(new FlowLayout());

        btnG = new JButton("GREEN (G)");
        btnG.setActionCommand("G");
        
        btnB = new JButton("BLUE (B)");
        btnB.setActionCommand("B");
        
        btnR = new JButton("RED (R)");
        btnR.setActionCommand("R");

        pnlBotoiak.add(btnG);
        pnlBotoiak.add(btnB);
        pnlBotoiak.add(btnR);
        add(pnlBotoiak, BorderLayout.CENTER);

        // Azalpen txiki bat
        JLabel lblInfo = new JLabel("Aukeratu kolorea saguarekin edo teklatuarekin", SwingConstants.CENTER);
        add(lblInfo, BorderLayout.SOUTH);
        
        // Teklatua entzuteko (G, B, R letrak)
        this.setFocusable(true);
    }

    // Kontrolatzailea botoiei eta teklatuari lotzeko metodoa
    public void setKontrolatzailea(BotoiKontroladorea bk) {
        // Saguaren klikak
        btnG.addActionListener(bk);
        btnB.addActionListener(bk);
        btnR.addActionListener(bk);

        // Teklatuaren sakatzeak (E12 - G, B, R letrak)
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_G) btnG.doClick();
                if (e.getKeyCode() == KeyEvent.VK_B) btnB.doClick();
                if (e.getKeyCode() == KeyEvent.VK_R) btnR.doClick();
            }
        });
    }
}