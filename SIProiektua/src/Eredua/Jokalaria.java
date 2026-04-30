package Eredua;

public class Jokalaria {
    private String izena;
    private int puntuazioa;

    public Jokalaria(String izena) {
        this.izena = izena;
        this.puntuazioa = 0;
    }

    public String getIzena() { return izena; }
    public int getPuntuazioa() { return puntuazioa; }
    public void gehituPuntuak(int puntuak) { this.puntuazioa += puntuak; }
}