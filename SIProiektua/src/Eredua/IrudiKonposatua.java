package Eredua;

import java.util.ArrayList;
import java.util.List;

public class IrudiKonposatua implements Grafikoa {
    private List<Grafikoa> osagaiak;

    public IrudiKonposatua() {
        this.osagaiak = new ArrayList<>();
    }

    public void gehituOsagaia(Grafikoa g) {
        this.osagaiak.add(g);
    }

    @Override
    public List<Puntu> getPixelek() {
        List<Puntu> pixelGuztiak = new ArrayList<>();
        for (Grafikoa g : osagaiak) {
            pixelGuztiak.addAll(g.getPixelek());
        }
        return pixelGuztiak;
    }
}