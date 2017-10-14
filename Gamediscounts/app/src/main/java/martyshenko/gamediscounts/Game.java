package martyshenko.gamediscounts;

public class Game {

    public final String title;

    public final String priceCut;



    public Game(String title, String priceCut) {
        this.title = title;
        this.priceCut = priceCut;

    }

    public String getTitle() {
        return title;
    }

    public String getPriceCut() {
        return priceCut;
    }
}
