package sms.dto;

public class PaymentDto {
	private String cart_id;
	private String payment_id;
	private String user_id;
	private String receiver_name;
	private String receiver_address;
	private String payer_name;
	private String payer_account;
	private String newCartId;

	public PaymentDto() {}


	public PaymentDto(String cartId, String paymentId, String userId, String recipientName, String recipientAddress,
			String payerName, String payerAccount) {

		this.cart_id = cartId;
		this.payment_id = paymentId;
		this.user_id = userId;
		this.receiver_name = recipientName;
		this.receiver_address = recipientAddress;
		this.payer_name = payerName;
		this.payer_account = payerAccount;
	}

	public PaymentDto(String cartId, String paymentId) {
		this.cart_id = cartId;
		this.payment_id = paymentId;
	}


	public String getCart_id() {
		return cart_id;
	}


	public void setCart_id(String cart_id) {
		this.cart_id = cart_id;
	}


	public String getPayment_id() {
		return payment_id;
	}


	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public String getReceiver_name() {
		return receiver_name;
	}


	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}


	public String getReceiver_address() {
		return receiver_address;
	}


	public void setReceiver_address(String receiver_address) {
		this.receiver_address = receiver_address;
	}


	public String getPayer_name() {
		return payer_name;
	}


	public void setPayer_name(String payer_name) {
		this.payer_name = payer_name;
	}


	public String getPayer_account() {
		return payer_account;
	}


	public void setPayer_account(String payer_account) {
		this.payer_account = payer_account;
	}


	public String getNewCartId() {
		return newCartId;
	}


	public void setNewCartId(String newCartId) {
		this.newCartId = newCartId;
	}


}
