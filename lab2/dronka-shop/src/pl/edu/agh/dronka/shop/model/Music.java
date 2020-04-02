package pl.edu.agh.dronka.shop.model;

public class Music extends Item {

    private MusicalGenre musicalGenre;

    private boolean videoAttached;

    public Music(String name, Category category, int price, int quantity) {
        super(name, category, price, quantity);
    }

    public Music() {
    }

    public void setMusicalGenre(MusicalGenre musicalGenre) { this.musicalGenre = musicalGenre; }

    public MusicalGenre getMusicalGenre() { return this.musicalGenre; }

    public void setVideoAttached(boolean videoAttached) { this.videoAttached = videoAttached; }

    public boolean isVideoAttached() { return this.videoAttached; }
}
