package Eredua;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EtsaiNodo extends Entitatea {
    private List<Entitatea> osagaiak;

    public EtsaiNodo(int x, int y) {
        super(x, y);
        this.osagaiak = new ArrayList<>();
    }

    public void gehituOsagaia(Entitatea e) {
        this.osagaiak.add(e);
    }

    @Override
	public List<Entitatea> getPixelek() {
		return osagaiak.stream().flatMap(e -> e.getPixelek().stream().map(p -> new Etsaia(e.getX() + p.getX(), e.getY() + p.getY()))).collect(Collectors.toList());
	}
    @Override
	public void mugitu() {
		int norabidea = (int)(Math.random() * 3); // 0 ezker, 1 eskuin, 2 behera
		boolean ezkerreraAhalDa = true;
		boolean eskumaraAhalDa = true;

		// Paretekin talkak pixel bakoitzarekin egiaztatu
		for (Entitatea p : this.getPixelek()) {
			if (this.x + p.getX() - 1 < 0) ezkerreraAhalDa = false;
			if (this.x + p.getX() + 1 >= 100) eskumaraAhalDa = false;
		}

		int xBerria = this.x;
		int yBerria = this.y;

		if (norabidea == 0 && ezkerreraAhalDa) {
			xBerria = this.x - 1;
		} else if (norabidea == 1 && eskumaraAhalDa) {
			xBerria = this.x + 1;
		} else {
			yBerria = this.y + 1;
		}

		if (Eredua.JokoKudeatzailea.getNireJK().posizioaLibreDa(xBerria, yBerria, this)) {
			this.x = xBerria;
			this.y = yBerria;
		}
	}

    @Override
    public Egoera getEgoeraObject() {
        return new EtsaiaEgoera();
    }
}
