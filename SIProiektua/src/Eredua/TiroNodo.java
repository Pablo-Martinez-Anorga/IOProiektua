package Eredua;

import java.util.ArrayList;
import java.util.List;

public class TiroNodo extends Entitatea {
    private List<Entitatea> osagaiak = new ArrayList<>();

    public TiroNodo(int x, int y) {
        super(x, y);
    }

    public void gehituOsagaia(Entitatea e) {
        this.osagaiak.add(e);
    }

    public List<Entitatea> getPixelek() {
        return this.osagaiak;
    }

    @Override
    public Egoera getEgoeraObject() { return new GelaxkaTiro(); }

    @Override
    public void mugitu() {
        this.y--;
    }

    @Override
    public void mugitu(String norabidea) {
        for (Entitatea p : osagaiak) { p.mugitu(norabidea); }
    }
}