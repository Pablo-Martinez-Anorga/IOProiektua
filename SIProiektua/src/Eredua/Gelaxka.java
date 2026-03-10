package Eredua;

import java.util.Observable;

public class Gelaxka extends Observable {
    
    private int x;
    private int y;
    private Entitatea entitatea; // Lo que hay dentro de la celda. Si es null, está vacía.

    // Constructor
    public Gelaxka(int x, int y) {
        this.x = x;
        this.y = y;
        this.entitatea = null; // Por defecto, la celda empieza vacía
    }

    // Método para cambiar el contenido de la celda
    public void setEntitatea(Entitatea entitatea) {
        this.entitatea = entitatea;
        
        //Gelaxka aldatzean, bistari abisatu
        setChanged();
        notifyObservers();
    }

    // Getters básicos
    public Entitatea getEntitatea() {
        return this.entitatea;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
    
    // Método auxiliar útil para saber si la celda está vacía
    public boolean isHutsik() {
        return this.entitatea == null;
    }
    
    // Método para vaciar la celda
    public void hustu() {
        setEntitatea(null);
    }
}