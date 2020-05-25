package pl.edu.agh.internetshop;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


public class Order {
    private static final BigDecimal TAX_VALUE = BigDecimal.valueOf(1.23);
	private final UUID id;
    private final List<Product> products;
    private boolean paid;
    private Shipment shipment;
    private ShipmentMethod shipmentMethod;
    private PaymentMethod paymentMethod;
    private final BigDecimal discount;

    public Order(List<Product> products, BigDecimal discount) {
        this.products = Objects.requireNonNull(products, "product cannot be null");
        this.products.forEach((p)->Objects.requireNonNull(p,"product cannot be null"));
        id = UUID.randomUUID();
        paid = false;
        this.discount = discount;
    }

    public UUID getId() {
        return id;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public boolean isSent() {
        return shipment != null && shipment.isShipped();
    }

    public boolean isPaid() { return paid; }

    public Shipment getShipment() {
        return shipment;
    }

    public BigDecimal getPrice() {
        BigDecimal price = BigDecimal.valueOf(0.0);
        for (Product product: products) {
            price = price.add(product.getPrice());
        }
        return price;
    }

    public BigDecimal getPriceWithProductDiscount() {
        BigDecimal price = BigDecimal.valueOf(0.0);
        for (Product product: products) {
            price = price.add(product.getPriceWithDiscount());
        }
        return price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public BigDecimal getPriceWithDiscount() {
        return getPriceWithProductDiscount().subtract(getPriceWithProductDiscount().multiply(discount).setScale(Product.PRICE_PRECISION, Product.ROUND_STRATEGY));
    }

    public BigDecimal getPriceWithTaxes() {
        return getPriceWithDiscount().multiply(TAX_VALUE).setScale(Product.PRICE_PRECISION, Product.ROUND_STRATEGY);
    }

    public List<Product> getProducts() {
        return products;
    }

    public ShipmentMethod getShipmentMethod() {
        return shipmentMethod;
    }

    public void setShipmentMethod(ShipmentMethod shipmentMethod) {
        this.shipmentMethod = shipmentMethod;
    }

    public void send() {
        boolean sentSuccesful = getShipmentMethod().send(shipment, shipment.getSenderAddress(), shipment.getRecipientAddress());
        shipment.setShipped(sentSuccesful);
    }

    public void pay(MoneyTransfer moneyTransfer) {
        moneyTransfer.setCommitted(getPaymentMethod().commit(moneyTransfer));
        paid = moneyTransfer.isCommitted();
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }
}
