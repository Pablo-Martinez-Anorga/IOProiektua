package Eredua;

public class GelaxkaEspaziontzi implements Egoera {
    @Override
    public String getIzena() { return "ESPAZIONTZIA"; }
    @Override
    public void egoeraAldatu(Gelaxka g) { g.setEgoera(this); }
}