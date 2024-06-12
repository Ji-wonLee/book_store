package sms.dto;

public class PaymentDetailDto {
    private String payment_id;
    private String product_id;
    private int quantity;
    private int price;
    private String cart_id;

    
    
    public PaymentDetailDto(String payment_id, String product_id, int quantity, int price, String cart_id) {
		
		this.payment_id = payment_id;
		this.product_id = product_id;
		this.quantity = quantity;
		this.price = price;
		this.cart_id = cart_id;
	}

	public PaymentDetailDto(String payment_id, String product_id, int quantity, int price) {
        this.payment_id = payment_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.price = price;
    }

    // getter¿Í setterµé...

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

	public String getCart_id() {
		return cart_id;
	}

	public void setCart_id(String cart_id) {
		this.cart_id = cart_id;
	}
    
}