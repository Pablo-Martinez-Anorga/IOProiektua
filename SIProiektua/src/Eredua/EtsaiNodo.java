package Eredua;

import java.util.ArrayList;
import java.util.List;

public class EtsaiNodo extends Entitatea {
    private List<Entitatea> osagaiak = new ArrayList<>();
    private int id;

    public EtsaiNodo(int x, int y) {
        super(x, y);
    }

    public void gehituOsagaia(Entitatea e) {
        this.osagaiak.add(e);
    }

    public List<Entitatea> getPixelek() {
        return this.osagaiak;
    }

    @Override
    public Egoera getEgoeraObject() {
        return new GelaxkaEtsai();
    }

    @Override
    public void mugitu() {
    	int random = (int)(Math.random() * 3);
    	String norabidea = "Behera"; 
    	if (random == 1) norabidea = "Ezkerrera";
    	else if (random == 2) norabidea = "Eskumara"; 

        // GAKOA: Norabide BERA konprobatu eta mugitu
        if (this.mugituDaiteke(norabidea)) {
            if (norabidea.equals("Eskumara")) this.x++;
            else if (norabidea.equals("Ezkerrera")) this.x--;
            else if (norabidea.equals("Behera")) this.y++;
        }
    }

    public boolean mugituDaiteke(String norabidea) {
    	for (Entitatea p : osagaiak) {
            // POSIZIO ERREALA = Nodoaren aingurua (this.x) + Pixelaren desplazamendua (p.getX)
            int pxReal = this.x + p.getX();
            int pyReal = this.y + p.getY();

            int hurrengoX = pxReal + (norabidea.equals("Eskumara") ? 1 : norabidea.equals("Ezkerrera") ? -1 : 0);
            int hurrengoY = pyReal + (norabidea.equals("Behera") ? 1 : 0);

            // Orain bai, 0 eta 99 artean konprobatu dezakegu
            if (hurrengoX < 0 || hurrengoX > 99 || hurrengoY > 59) return false;

            // Kolisioak (JokoKudeatzaileari posizio ERREALA galdetu behar diogu)
            int besteId = JokoKudeatzailea.getNireJK().getEtsaiaId(hurrengoX, hurrengoY);
            if (besteId != -1 && besteId != this.id) return false;
        }
        return true;
    }
    
    private boolean nirePixelaDa(int x, int y) {
        for (Entitatea p : osagaiak) {
            if (p.getX() == x && p.getY() == y) return true;
        }
        return false;
    }

    @Override
    public void mugitu(String norabidea) {
    	if (this.mugituDaiteke(norabidea)) {
            if (norabidea.equals("Eskumara")) this.x++;
            else if (norabidea.equals("Ezkerrera")) this.x--;
            else if (norabidea.equals("Behera")) this.y++;
        }
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return this.id;
    }
    
    public boolean duPixela(int x, int y) {
        for (Entitatea p : osagaiak) {
            if (p.getX() == x && p.getY() == y) {
                return true;
            }
        }
        return false;
    }
    }

