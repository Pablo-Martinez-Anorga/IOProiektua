package Eredua;

import java.util.ArrayList;
import java.util.List;

public class Etsaia extends Entitatea {
	private int id;
	
	//Eraikitzailea
	public Etsaia(int x, int y, int id) {
        super(x,y);
        this.id = id;
    }
	
	@Override
	public Egoera getEgoeraObject() {
	    return new GelaxkaEtsai();
	}
	
	public int getId() { return this.id; }
    public void setId(int id) { this.id = id; }
	
	@Override
	public void mugitu(String norabidea) {
		if (norabidea.equals("Ezkerrera")) this.x--;
        else if (norabidea.equals("Eskumara")) this.x++;
        else if (norabidea.equals("Behera")) this.y++;
	}
	
	@Override
	public boolean mugituDaiteke(String norabidea) {
	    if (norabidea.equals("Ezkerrera")) return this.x > 0;
	    if (norabidea.equals("Eskumara")) return this.x < 99;
	    if (norabidea.equals("Behera")) return this.y < 59;
	    return true;
	} 
}