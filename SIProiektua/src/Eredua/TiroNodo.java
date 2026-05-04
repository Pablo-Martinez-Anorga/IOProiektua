package Eredua;

import java.util.ArrayList;
import java.util.List;

public class TiroNodo extends Entitatea {
	protected List<Entitatea> osagaiak = new ArrayList<>();

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
        for (Entitatea p : osagaiak) {
            p.mugitu();
        }
    }

    @Override
    public void mugitu(String norabidea) {
    	if (norabidea.equals("Gora")) this.y--;
        else if (norabidea.equals("Behera")) this.y++;
        for (Entitatea p : osagaiak) {
            p.mugitu(norabidea);
        }
    }
}