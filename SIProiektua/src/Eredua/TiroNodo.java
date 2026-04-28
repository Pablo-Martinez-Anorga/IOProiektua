package Eredua;

import java.util.ArrayList;
import java.util.List;

public class TiroNodo extends Entitatea {
    private List<Entitatea> osagaiak; // Tiroaren osagaiak (bilduma)

    public TiroNodo(int x, int y) {
        super(x, y);
        this.osagaiak = new ArrayList<>();
    }

    // Osagaiak (Tiro monopixelak) gehitzeko metodoa
    public void gehituOsagaia(Entitatea e) {
        this.osagaiak.add(e);
    }

    @Override
    public List<Entitatea> getPixelek() {
        List<Entitatea> pixelGuztiak = new ArrayList<>();
        for (Entitatea e : osagaiak) {
            // Nodoaren barruko elementu bakoitzari bere pixelak eskatzen dizkiogu
            pixelGuztiak.addAll(e.getPixelek());
        }
        return pixelGuztiak;
    }

    @Override
    public void mugitu() {
        // NODOA da mugitzen dena matrize globalean. 
        // Tiroak gorantz doaz, beraz Y ardatzari 1 kentzen diogu
        this.y -= 1; 
    }

    @Override
    public Egoera getEgoeraObject() {
        // Nodo osoak Tiro bat bezala jokatzen du
        return new TiroaEgoera();
    }
}