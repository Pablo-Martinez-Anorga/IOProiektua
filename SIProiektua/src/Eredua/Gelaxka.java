package Eredua;

import java.util.Observable;

public class Gelaxka extends Observable {
    
    private int x;
    private int y;
    private Entitatea entitatea; 

    public Gelaxka(int x, int y) {
        this.x = x;
        this.y = y;
        this.entitatea = null; 
    }

    public void setEntitatea(Entitatea entitatea) {
        this.entitatea = entitatea;
        setChanged();
        notifyObservers();
    }

 
    public String getEdukiaMota() {
        if (this.entitatea == null) return "HUTSA";
        if (this.entitatea instanceof Espaziontzia) return "ESPAZIONTZIA";
        if (this.entitatea instanceof Etsaia) return "ETSAIA";
        if (this.entitatea instanceof Tiroa) return "TIROA";
        return "HUTSA";
    }

    public Entitatea getEntitatea() { return this.entitatea; }
    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public boolean isHutsik() { return this.entitatea == null; }
    
    public void hustu() {
        setEntitatea(null);
    }
}