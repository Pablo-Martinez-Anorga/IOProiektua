package Eredua;

public class GelaxkaTiro implements Egoera {
    @Override
    public String getIzena() { return "TIROA"; }
    @Override
    public void egoeraAldatu(Gelaxka g) { g.setEgoera(this);} 
}