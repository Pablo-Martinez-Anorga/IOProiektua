package Eredua;

public class EtsaiaFaktoria {
    private static EtsaiaFaktoria nireFaktoria;

    private EtsaiaFaktoria() {}

    public static EtsaiaFaktoria getNireFaktoria() {
        if (nireFaktoria == null) nireFaktoria = new EtsaiaFaktoria();
        return nireFaktoria;
    }

    public Entitatea sortuEtsaia(int x, int y, String mota) {
        if (mota.equals("MULTIPIXEL")) {
            EtsaiNodo nodo = new EtsaiNodo(x, y);
            nodo.gehituOsagaia(new Etsaia(0, 0));  // Erdia
            nodo.gehituOsagaia(new Etsaia(-1, 0)); // Ezkerra
            nodo.gehituOsagaia(new Etsaia(1, 0));  // Eskuma
            return nodo;
        } else {
            return new Etsaia(x, y); // Monopixela
        }
    }
}
