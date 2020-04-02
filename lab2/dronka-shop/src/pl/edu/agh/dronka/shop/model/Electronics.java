package pl.edu.agh.dronka.shop.model;

public class Electronics extends Item {

    private boolean mobile;

    private boolean warranty;

    public Electronics(String name, Category category, int price, int quantity) {
        super(name, category, price, quantity);
    }

    public Electronics() {
    }

    public void setMobile(boolean mobile) { this.mobile = mobile; }

    public boolean isMobile() { return this.mobile; }

    public void setWarranty(boolean warranty) { this.warranty = warranty; }

    public boolean isWarranty() { return this.warranty; }
}
