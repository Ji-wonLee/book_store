package sms.dto;

import java.util.Date;

public class CartDto {
	private String cart_id;			// 카트고유번호
	private String user_id;			// userId
	private int totalprice;			// 총 가격
	private Date cart_Date;			// 카트 생성일자 및 수정일자
	private String state;			// 카트 상태; 장바구니, 결제중, 결제완료
	private String product_id;		// 상품 id
	private int quantity;			// 수량	
	private int product_price;				// 단가
	private String product_name;	// 상품 이름
	private String newCartId;		//새 장바구니id

	private String product_imgurl; // 상품 이미지 URL
	


	public CartDto() {}


	public CartDto(String cart_id, String user_id, int totalprice, Date cart_Date, String state, String product_id,
			int quantity, int price, String product_name, String newCartId, String product_imgurl) {

		this.cart_id = cart_id;
		this.user_id = user_id;
		this.totalprice = totalprice;
		this.cart_Date = cart_Date;
		this.state = state;
		this.product_id = product_id;
		this.quantity = quantity;
		this.product_price = price;
		this.product_name = product_name;
		this.newCartId = newCartId;
		this.product_imgurl = product_imgurl;
		
	}


	 public CartDto(String cart_id, String user_id, String product_id, int quantity, int product_price, String product_name, String product_imgurl, Date cart_Date, String state, int totalprice) {
	        this.cart_id = cart_id;
	        this.user_id = user_id;
	        this.product_id = product_id;
	        this.quantity = quantity;
	        this.product_price = product_price;
	        this.product_name = product_name;
	        this.product_imgurl = product_imgurl;
	        this.cart_Date = cart_Date;
	        this.state = state;
	        this.totalprice = totalprice;
	    }

	// 새로운 생성자 추가
	public CartDto(String user_id) {
		this.user_id = user_id;
		this.state = "장바구니";
		this.cart_Date = new Date();
	}
	/**
	 * cartDetail
	 * @param cartId
	 * @param userId
	 * @param totalPrice
	 * @param cartDate
	 * @param state
	 * @param productId
	 * @param quantity
	 * @param price
	 */
	public CartDto(String cartId, String userId, int totalPrice, Date cartDate, String state, String productId,
			int quantity, int price, String productName) {
		this.cart_id = cartId;
		this.user_id = userId;
		this.totalprice = totalPrice;
		this.cart_Date = cartDate;
		this.state = state;
		this.product_id = productId;
		this.quantity = quantity;
		this.product_price = price;
		this.product_name = productName;
	}

	/**
	 * CartStatusUpdateDto 
	 * @param userId
	 * @param cartId
	 * @param state
	 */
	public CartDto(String userId, String cartId, String state) {
		this.user_id = userId;
		this.cart_id = cartId;
		this.state = state;
	}

	/**
	 * 카트 상태 업데이트 
	 * @param cart_id
	 * @param state
	 */
	public CartDto(String cart_id, String state) {
		this.cart_id = cart_id;
		this.state = state;
	}

	/**
	 * 재고 업데이트 
	 * @param product_id
	 * @param quantity
	 */
	public CartDto(String product_id, int quantity) {
		this.product_id = product_id;
		this.quantity = quantity;
	}
	//	/**
	//	 * 카트 상태 업뎅
	//	 * 결제중-> 장바구니. 
	//	 * @param cartId
	//	 * @param userId
	//	 */
	//	public CartDto(String cartId, String userId) {
	//		this.cart_id = cartId;
	//		this.user_id = userId;
	//	}

	/**
	 * update item 
	 * 장바구니 상품 추가에 사용
	 * SelectDao, Svc, Controller 확인!
	 */
	public CartDto(String cartId, String productId, int quantity,int price) {
		this.cart_id = cartId ;
		this.product_id = productId;
		this.quantity = quantity;
		this.product_price = price;
	}
	/**
	 * update item 
	 */
	public CartDto(String cartId, String productId, int quantity) {
		this.cart_id = cartId ;
		this.product_id = productId;
		this.quantity = quantity;
	}

	public CartDto(String newCartId, String user_id, String state, Date cart_Date) {
		this.cart_id = newCartId;
		this.user_id = user_id;
		this.state = state;
		this.cart_Date = cart_Date;
	}

	public String getCart_id() {
		return cart_id;
	}


	public void setCart_id(String cart_id) {
		this.cart_id = cart_id;
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public int getTotalprice() {
		return totalprice;
	}


	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}


	public Date getCart_Date() {
		return cart_Date;
	}


	public void setCart_Date(Date cart_Date) {
		this.cart_Date = cart_Date;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
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
		return product_price;
	}


	public void setPrice(int price) {
		this.product_price = price;
	}


	public String getProduct_name() {
		return product_name;
	}


	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}


	public String getNewCartId() {
		return newCartId;
	}


	public void setNewCartId(String newCartId) {
		this.newCartId = newCartId;
	}


	public String getProduct_imgurl() {
		return product_imgurl;
	}


	public void setProduct_imgurl(String product_imgurl) {
		this.product_imgurl = product_imgurl;
	}


	public int getProduct_price() {
		return product_price;
	}


	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}



}