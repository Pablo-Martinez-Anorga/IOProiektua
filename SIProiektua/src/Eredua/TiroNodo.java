package Eredua;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
		return osagaiak.stream().flatMap(e -> e.getPixelek().stream().map(p -> new Tiroa(e.getX() + p.getX(), e.getY() + p.getY()))).collect(Collectors.toList());
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