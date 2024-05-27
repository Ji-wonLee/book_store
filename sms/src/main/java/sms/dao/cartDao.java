package sms.dao;

import java.util.List;

import sms.dto.cartDto;

public interface cartDao {

	cartDto getCartPrePurchase(String userId);	
	void updateCartItemQuantity(String cartId, String productId, int quantity);
	void updateCartTotalPrice(String cartId);
	List<cartDto> getCompletedCarts(String userId);
	void createNewCart(String cartId, String userId);
	void updateCartState(String cartId, String state);

}
