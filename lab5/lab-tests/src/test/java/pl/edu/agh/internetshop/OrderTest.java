package pl.edu.agh.internetshop;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static pl.edu.agh.internetshop.util.CustomAssertions.assertBigDecimalCompareValue;

public class OrderTest {

    private static final BigDecimal DISCOUNT = BigDecimal.valueOf(0);

    private Order getOrderWithMockedProduct() {
		Product product = mock(Product.class);
		return new Order(Collections.singletonList(product), DISCOUNT);
	}

	@Test
	public void testGetProductThroughOrder() {
		// given
		Product expectedProduct = mock(Product.class);
		Order order = new Order(Collections.singletonList(expectedProduct), DISCOUNT);

		// when
		List<Product> actualProducts = order.getProducts();

		// then
		assertSame(expectedProduct, actualProducts.get(0));
	}

	@Test
	public void testSetShipment() throws Exception {
		// given
		Order order = getOrderWithMockedProduct();
		Shipment expectedShipment = mock(Shipment.class);

		// when
		order.setShipment(expectedShipment);

		// then
		assertSame(expectedShipment, order.getShipment());
	}

	@Test
	public void testShipmentWithoutSetting() throws Exception {
		// given
		Order order = getOrderWithMockedProduct();

		// when

		// then
		assertNull(order.getShipment());
	}

	@Test
	public void testGetPrice() throws Exception {
		// given
		BigDecimal expectedProductPrice = BigDecimal.valueOf(1000);
		Product product = mock(Product.class);
		given(product.getPrice()).willReturn(expectedProductPrice);
		Order order = new Order(Collections.singletonList(product), DISCOUNT);

		// when
		BigDecimal actualProductPrice = order.getPrice();

		// then
		assertBigDecimalCompareValue(expectedProductPrice, actualProductPrice);
	}

	private Order getOrderWithCertainProductPrice(double productPriceValue) {
		BigDecimal productPrice = BigDecimal.valueOf(productPriceValue);
		Product product = mock(Product.class);
		given(product.getPrice()).willReturn(productPrice);
		return new Order(Collections.singletonList(product), DISCOUNT);
	}

	@Test
	public void testPriceWithTaxesWithoutRoundUp() {
		// given
		Product product = mock(Product.class);

		// when
		Order order = new Order(Collections.singletonList(product), DISCOUNT);
		given(product.getPriceWithDiscount()).willReturn(BigDecimal.valueOf(2)); // 2 PLN

		// then
		assertBigDecimalCompareValue(order.getPriceWithTaxes(), BigDecimal.valueOf(2.46)); // 2.46 PLN
	}

	@Test
	public void testPriceWithTaxesWithRoundDown() {
		// given
		Product product = mock(Product.class);

		// when
		Order order = new Order(Collections.singletonList(product), DISCOUNT);
		given(product.getPriceWithDiscount()).willReturn(BigDecimal.valueOf(0.01)); // 0.01 PLN

		// then
		assertBigDecimalCompareValue(order.getPriceWithTaxes(), BigDecimal.valueOf(0.01)); // 0.01 PLN
	}

	@Test
	public void testPriceWithTaxesWithRoundUp() {
		// given
		BigDecimal productPrice = BigDecimal.valueOf(0.03); // 0.03 PLN
		Product product = mock(Product.class);
		given(product.getPrice()).willReturn(productPrice);
		given(product.getPriceWithDiscount()).willReturn(productPrice);

		// when
		Order order = new Order(Collections.singletonList(product), DISCOUNT);

		// then
		assertBigDecimalCompareValue(order.getPriceWithTaxes(), BigDecimal.valueOf(0.04)); // 0.04 PLN
	}

	@Test
	public void testSetShipmentMethod() {
		// given
		Order order = getOrderWithMockedProduct();
		ShipmentMethod surface = mock(SurfaceMailBus.class);

		// when
		order.setShipmentMethod(surface);

		// then
		assertSame(surface, order.getShipmentMethod());
	}

	@Test
	public void testSending() {
		// given
		Order order = getOrderWithMockedProduct();
		SurfaceMailBus surface = mock(SurfaceMailBus.class);
		Shipment shipment = mock(Shipment.class);
		given(shipment.isShipped()).willReturn(true);

		// when
		order.setShipmentMethod(surface);
		order.setShipment(shipment);
		order.send();

		// then
		assertTrue(order.isSent());
	}

	@Test
	public void testIsSentWithoutSending() {
		// given
		Order order = getOrderWithMockedProduct();
		Shipment shipment = mock(Shipment.class);
		given(shipment.isShipped()).willReturn(true);

		// when

		// then
		assertFalse(order.isSent());
	}

	@Test
	public void testWhetherIdExists() throws Exception {
		// given
		Order order = getOrderWithMockedProduct();

		// when

		// then
		assertNotNull(order.getId());
	}

	@Test
	public void testSetPaymentMethod() throws Exception {
		// given
		Order order = getOrderWithMockedProduct();
		PaymentMethod paymentMethod = mock(MoneyTransferPaymentTransaction.class);

		// when
		order.setPaymentMethod(paymentMethod);

		// then
		assertSame(paymentMethod, order.getPaymentMethod());
	}

	@Test
	public void testPaying() throws Exception {
		// given
		Order order = getOrderWithMockedProduct();
		PaymentMethod paymentMethod = mock(MoneyTransferPaymentTransaction.class);
		given(paymentMethod.commit(any(MoneyTransfer.class))).willReturn(true);
		MoneyTransfer moneyTransfer = mock(MoneyTransfer.class);
		given(moneyTransfer.isCommitted()).willReturn(true);

		// when
		order.setPaymentMethod(paymentMethod);
		order.pay(moneyTransfer);

		// then
		assertTrue(order.isPaid());
	}

	@Test
	public void testIsPaidWithoutPaying() throws Exception {
		// given
		Order order = getOrderWithMockedProduct();

		// when

		// then
		assertFalse(order.isPaid());
	}

	@Test
	public void productsListIsNull() {
		// when then
		assertThrows(NullPointerException.class, () -> new Order(null, DISCOUNT));
	}

	@Test
	public void listProductIsNull() {
		// given
		List<Product> products = Arrays.asList(mock(Product.class), null);

		// when then
		assertThrows(NullPointerException.class, () -> new Order(products, DISCOUNT));
	}

	@Test
	public void getMultipleProductFromOrder() {
		// given
		Product expectedProduct = mock(Product.class);
		Product expectedProduct1 = mock(Product.class);

		// when
		Order order = new Order(Arrays.asList(expectedProduct, expectedProduct1), DISCOUNT);

		// then
		assertSame(expectedProduct, order.getProducts().get(0));
		assertSame(expectedProduct1, order.getProducts().get(1));
		assertEquals(order.getProducts().size(), order.getProducts().size());
	}

	@Test
	public void getPriceWithMultiplyProducts() {
		// given
		Product product = mock(Product.class);
		Product product1 = mock(Product.class);

		BigDecimal expectedProductPrice = BigDecimal.valueOf(1500);

		given(product.getPrice()).willReturn(BigDecimal.valueOf(1000));
		given(product1.getPrice()).willReturn(BigDecimal.valueOf(500));

		// when
		Order order = new Order(Arrays.asList(product, product1), DISCOUNT);

		// then
		assertBigDecimalCompareValue(expectedProductPrice, order.getPrice());
	}

	@Test
	public void getDiscount() {
    	// given

		// when
		Order order = getOrderWithMockedProduct();

		// then
		assertBigDecimalCompareValue(DISCOUNT, order.getDiscount());
	}

	@Test
	public void getPriceWithProductDiscount() {
		// given
		Product product = mock(Product.class);
		Product product1 = mock(Product.class);

		BigDecimal expectedOrderPrice = BigDecimal.valueOf(1.7);

		given(product.getPriceWithDiscount()).willReturn(BigDecimal.valueOf(0.9));
		given(product1.getPriceWithDiscount()).willReturn(BigDecimal.valueOf(0.8));

		// when
		Order order = new Order(Arrays.asList(product, product1), DISCOUNT);

		// then
		assertBigDecimalCompareValue(expectedOrderPrice, order.getPriceWithProductDiscount());
	}

	@Test
	public void getPriceWithDiscount() {
		// given
		Product product = mock(Product.class);
		Product product1 = mock(Product.class);

		BigDecimal expectedOrderPrice = BigDecimal.valueOf(1.53);

		given(product.getPriceWithDiscount()).willReturn(BigDecimal.valueOf(0.9));
		given(product1.getPriceWithDiscount()).willReturn(BigDecimal.valueOf(0.8));

		// when
		Order order = new Order(Arrays.asList(product, product1), BigDecimal.valueOf(0.1));

		// then
		assertBigDecimalCompareValue(expectedOrderPrice, order.getPriceWithDiscount());
	}

	@Test
	public void getPriceWithTaxes() {
		Product product = mock(Product.class);
		Product product1 = mock(Product.class);

		BigDecimal expectedOrderPrice = BigDecimal.valueOf(1.88);

		given(product.getPriceWithDiscount()).willReturn(BigDecimal.valueOf(0.9));
		given(product1.getPriceWithDiscount()).willReturn(BigDecimal.valueOf(0.8));

		// when
		Order order = new Order(Arrays.asList(product, product1), BigDecimal.valueOf(0.1));

		// then
		assertBigDecimalCompareValue(expectedOrderPrice, order.getPriceWithTaxes());
	}
}
