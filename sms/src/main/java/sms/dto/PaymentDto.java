package sms.dto;

import java.util.Date;

public class PaymentDto {
	private String cart_id;
	private String payment_id;
	private String user_id;
	private String receiver_name;
	private String receiver_address;
	private String payer_name;
	private String payer_account;
	private String newCartId;
	private int quantity;
	private Date receive_date;

	public PaymentDto() {}




	
	public PaymentDto(String cart_id, String payment_id, String user_id, String receiver_name, String receiver_address,
			String payer_name, String payer_account, String newCartId, int quantity) {
	
		this.cart_id = cart_id;
		this.payment_id = payment_id;
		this.user_id = user_id;
		this.receiver_name = receiver_name;
		this.receiver_address = receiver_address;
		this.payer_name = payer_name;
		this.payer_account = payer_account;
		this.newCartId = newCartId;
		this.quantity = quantity;
	}




	/**
	 * 
	 *  INSERT INTO payment (payment_id, user_id, receiver_name, receiver_address,
	    payer_name, payer_account, cart_id, receive_date)
	    VALUES (#{payment_id}, #{user_id}, #{receiver_name}, #{receiver_address},
	    #{payer_name}, #{payer_account}, #{cart_id}, SYSDATE)
	 */
	public PaymentDto(String payment_id, String user_id, String receiver_name, String receiver_address,
			String payer_name, String payer_account, String cart_id, Date  receive_date ) {
		
		this.cart_id = cart_id;
		this.payment_id = payment_id;
		this.user_id = user_id;
		this.receiver_name = receiver_name;
		this.receiver_address = receiver_address;
		this.payer_name = payer_name;
		this.payer_account = payer_account;
		this.receive_date = receive_date;
	
	}
	
//	public PaymentDto(String cart_id, String payment_id, String user_id, String receiver_name, String receiver_address,
//			String payer_name, String payer_account) {
//		
//		this.cart_id = cart_id;
//		this.payment_id = payment_id;
//		this.user_id = user_id;
//		this.receiver_name = receiver_name;
//		this.receiver_address = receiver_address;
//		this.payer_name = payer_name;
//		this.payer_account = payer_account;
//	
//	}
	public PaymentDto(String cart_id, String payment_id, String user_id, String receiver_name, String receiver_address,
			String payer_name, String payer_account, String newCartId) {
		
		this.cart_id = cart_id;
		this.payment_id = payment_id;
		this.user_id = user_id;
		this.receiver_name = receiver_name;
		this.receiver_address = receiver_address;
		this.payer_name = payer_name;
		this.payer_account = payer_account;
		this.newCartId = newCartId;
	}





	public PaymentDto(String user_id, String receiver_name, String receiver_address) {
		this.user_id = user_id;
		this.receiver_name = receiver_name;
		this.receiver_address = receiver_address;
	}
	
	
	public PaymentDto(String user_id, String receiver_name, String receiver_address, String payer_name, String payer_account) {
		this.user_id = user_id;
		this.receiver_name = receiver_name;
		this.receiver_address = receiver_address;
		this.payer_name = payer_name;
		this.payer_account = payer_account;
	}

	public PaymentDto(String cartId, String paymentId) {
		this.cart_id = cartId;
		this.payment_id = paymentId;
	}

	/**
	 * PayInner¿¡¼­ ¾µ °Í.
	 * @param user_id
	 * @param receiver_name
	 * @param receiver_address
	 * @param payer_name
	 * @param payer_account
	 * @param cart_id
	 */
	public PaymentDto(String user_id, String receiver_name, String receiver_address, String payer_name, String payer_account, String cart_id) {
		this.user_id = user_id;
		this.receiver_name = receiver_name;
		this.receiver_address = receiver_address;
		this.payer_name = payer_name;
		this.payer_account = payer_account;
		this.cart_id = cart_id;
	}
    public PaymentDto(String payment_id, String user_id, String receiver_name, String receiver_address, String payer_name, String payer_account, String cart_id) {
        this.payment_id = payment_id;
        this.user_id = user_id;
        this.receiver_name = receiver_name;
        this.receiver_address = receiver_address;
        this.payer_name = payer_name;
        this.payer_account = payer_account;
        this.cart_id = cart_id;
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