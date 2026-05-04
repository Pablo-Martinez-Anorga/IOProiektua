package Eredua;

public class GelaxkaEtsai implements Egoera {
    @Override
    public String getIzena() { return "ETSAIA"; }
    @Override
    public void egoeraAldatu(Gelaxka g) { g.setEgoera(this); }
}