package Eredua;

import java.util.ArrayList;
import java.util.List;

public class TiroGeziEstrategia implements TiroEstrategia{

	@Override
    public List<Tiroa> tiroEgin(int ontziX, int ontziY, int ontziAltuera) {
        List<Tiroa> berriak = new ArrayList<>();
        int tiroY = ontziY - (ontziAltuera / 2) - 1;
        berriak.add(new Tiroa(ontziX - 2, tiroY)); 
        berriak.add(new Tiroa(ontziX + 2, tiroY)); 
        return berriak;
    }
}
