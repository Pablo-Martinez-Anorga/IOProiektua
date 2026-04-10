package Eredua;

import java.util.ArrayList;
import java.util.List;

public class Puntu implements Grafikoa {
    private int dx;
    private int dy;

    public Puntu(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() { return dx; }
    public int getDy() { return dy; }

    @Override
    public List<Puntu> getPixelek() {
        List<Puntu> nirePixela = new ArrayList<>();
        nirePixela.add(this);
        return nirePixela;
    }
}