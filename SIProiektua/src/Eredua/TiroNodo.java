package Eredua;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TiroNodo extends Entitatea {
    private List<Entitatea> osagaiak = new ArrayList<>();// Tiroaren osagaiak (bilduma)

    public TiroNodo(int x, int y) {
        super(x, y);
    }

    // Osagaiak (Tiro monopixelak) gehitzeko metodoa
    public void gehituOsagaia(Entitatea e) {
        this.osagaiak.add(e);
    }
    /*
    @Override
	public List<Entitatea> getPixelek() {
		return osagaiak.stream().flatMap(e -> e.getPixelek().stream().map(p -> new Tiroa(e.getX() + p.getX(), e.getY() + p.getY()))).collect(Collectors.toList());
	}
	*/

    @Override
    public void mugitu() {
    	this.y--; // Nodoaren koordenatua eguneratu
        for (Entitatea pixel : osagaiak) {
            pixel.mugitu(); // Hostoari abisatu
        } 
    }

    @Override
    public Egoera getEgoeraObject() {
        // Nodo osoak Tiro bat bezala jokatzen du
        return new GelaxkaTiro();
    }
    
    public List<Entitatea> getPixelek() {
        return osagaiak; // Zuzenean hostoen zerrenda itzultzen du
    }
    
    @Override
    public void mugitu(String norabidea) {
        for (Entitatea pixel : osagaiak) {
            pixel.mugitu(norabidea);
        }
    }
}