package Eredua;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EtsaiNodo extends Entitatea {
	protected List<Entitatea> osagaiak = new ArrayList<>();

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
    	List<String> norabideak = new ArrayList<>(Arrays.asList("Ezkerrera", "Eskumara", "Behera"));
        Collections.shuffle(norabideak);
        
        for (String norabide : norabideak) {
            if (mugituDaiteke(norabide)) {
                this.mugitu(norabide);
                break; // Behin mugituta, bukatu
            }
        }
    }
    
    public boolean mugituDaiteke(String norabidea) {
    	int hurrengoX = this.x;
        int hurrengoY = this.y;
        
        if (norabidea.equals("Ezkerrera")) hurrengoX--;
        else if (norabidea.equals("Eskumara")) hurrengoX++;
        else if (norabidea.equals("Behera")) hurrengoY++;
        else return false; 
        
        return JokoKudeatzailea.getNireJK().posizioaLibreDa(hurrengoX, hurrengoY, this);
    }
    
    @Override
    public void mugitu(String norabidea) {
    	if (mugituDaiteke(norabidea)) {
            if (norabidea.equals("Ezkerrera")) this.x--;
            else if (norabidea.equals("Eskumara")) this.x++;
            else if (norabidea.equals("Behera")) this.y++;
            
            for (Entitatea p : osagaiak) {
                p.mugitu(norabidea); 
            }
        }
    }
}

