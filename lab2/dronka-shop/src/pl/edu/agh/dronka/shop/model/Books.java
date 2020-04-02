package pl.edu.agh.dronka.shop.model;

public class Books extends Item {

    private int pageNumber;

    private boolean hardcover;

    public Books(String name, Category category, int price, int quantity) {
        super(name, category, price, quantity);
    }

    public Books() {
    }

    public void setPageNumber(int pageNumber) { this.pageNumber = pageNumber; }

    public int getPageNumber() { return this.pageNumber; }

    public void setHardcover(boolean hardcover) { this.hardcover = hardcover; }

    public boolean isHardcover() { return this.hardcover; }
}
