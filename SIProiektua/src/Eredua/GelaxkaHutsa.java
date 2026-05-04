package Eredua;

public class GelaxkaHutsa implements Egoera {
    @Override
    public String getIzena() {
        return "HUTSA"; 
    }

    @Override
    public void egoeraAldatu(Gelaxka g) {
        g.setEgoera(this);
    }
}