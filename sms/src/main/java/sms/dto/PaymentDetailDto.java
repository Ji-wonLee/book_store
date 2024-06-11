package sms.dto;

public class PaymentDetailDto {
    private String payment_id;
    private String product_id;
    private int quantity;
    private int price;

    public PaymentDetailDto(String payment_id, String product_id, int quantity, int price) {
        this.payment_id = payment_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.price = price;
    }

    // getter�� setter��...

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
}
