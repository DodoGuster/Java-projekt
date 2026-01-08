package app.entities;

public enum City {
    ZAGREB("Zagreb", "10000"),
    OSIJEK("Osijek", "31000");

    private final String naziv;
    private final String postanskiBroj;

    City(String naziv, String postanskiBroj) {
        this.naziv = naziv;
        this.postanskiBroj = postanskiBroj;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getPostanskiBroj() {
        return postanskiBroj;
    }

    @Override
    public String toString() {
        return naziv;
    }
}
