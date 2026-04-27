package Eredua;

import java.util.List;

public class Puntu implements Grafikoa {
	//Hostoa
    private int dx;
    private int dy;

    public Puntu(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() { return dx; }
    public int getDy() { return dy; }

    @Override
    public void betePixelekin(List<Puntu> pixelGuztiak) {
        pixelGuztiak.add(this);
    }
}