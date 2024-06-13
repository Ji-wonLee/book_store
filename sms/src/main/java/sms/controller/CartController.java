package sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sms.dao.CartDao;
import sms.dto.CartDto;
import sms.dto.ProductDto;
import sms.service.CartService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class CartController {

	@Autowired
	CartService cartService; 
	


	 /**
     * 특정 사용자의 장바구니 항목을 조회합니다.
     * @param userId 사용자 ID (옵션)
     * @param model 모델 객체
     * @return 장바구니 항목 리스트를 보여주는 JSP 페이지
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listCartItems(HttpServletRequest req, ModelMap model) {
        HttpSession session = req.getSession();
        String user_id = (String) session.getAttribute("user_id");

        if (user_id == null || user_id.isEmpty()) {
            model.addAttribute("error", "로그인이 필요합니다.");
            return "login"; // 로그인 페이지로 리다이렉트
        }

        List<CartDto> cartItems = cartService.listCartItems(user_id);
        int totalPrice = 0;
        int totalQuantity = 0;

        for (CartDto item : cartItems) {
            totalPrice += item.getProduct_price() * item.getQuantity();
            totalQuantity += item.getQuantity();
        }

        if (cartItems.isEmpty()) {
            model.addAttribute("error", "장바구니에 항목이 없습니다.");
        } else {
            model.addAttribute("cartItems", cartItems);
            model.addAttribute("totalPrice", totalPrice);
            model.addAttribute("totalQuantity", totalQuantity);
        }

        model.addAttribute("user_id", user_id); // userId도 함께 모델에 추가
        return "cart/cart_itemList2"; // JSP 파일의 경로
    }


    

    // 장바구니 항목 삭제
    @RequestMapping(value = "/deleteItem", method = RequestMethod.POST)
    @ResponseBody
    public String deleteCartItem(@RequestParam("product_id") String productId, HttpServletRequest req) {
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("user_id");

        if (userId == null || userId.isEmpty()) {
            return "로그인이 필요합니다.";
        }

        CartDto cartDto = new CartDto();
        cartDto.setUser_id(userId);
        cartDto.setProduct_id(productId);

        cartService.deleteCartItem(cartDto);
        return "성공적으로 삭제되었습니다.";
    }

    // 장바구니 항목 수량 수정
    @RequestMapping(value = "/updateItemQuantity", method = RequestMethod.POST)
    @ResponseBody
    public String updateCartItemQuantity(@RequestParam("product_id") String productId,
                                         @RequestParam("quantity") int quantity,
                                         HttpServletRequest req) {
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("user_id");

        if (userId == null || userId.isEmpty()) {
            return "로그인이 필요합니다.";
        }

        CartDto cartDto = new CartDto();
        cartDto.setUser_id(userId);
        cartDto.setProduct_id(productId);
        cartDto.setQuantity(quantity);

        cartService.updateCartItemQuantity(cartDto);
        return "성공적으로 업데이트되었습니다.";
    }
    
    /**
     * 장바구니 상태를 업데이트합니다.
     * @param cartStatusUpdateDto 업데이트할 상태 정보
     * @param model 모델 객체
     * @return 장바구니 리스트 페이지로 리다이렉트
     */
    @RequestMapping(value = "/updateState", method = RequestMethod.POST)
    public String updateCartState(@ModelAttribute CartDto cartStatusUpdateDto, ModelMap model) {
        cartService.updateCartStateAndGeneratePaymentId(cartStatusUpdateDto.getCart_id(), cartStatusUpdateDto.getState());
        return "redirect:/cart";
    }

    
    
    
    @RequestMapping(value="/addItemtoCart", method=RequestMethod.GET)
    public String insertCart(@RequestParam("product_id") String product_id,
                             @RequestParam("number") int number,
                             @RequestParam("product_price") int product_price,
                             @RequestParam("product_name") String product_name,
                             @RequestParam("redirect") boolean redirect,
                             ModelMap model, HttpServletRequest req) {

        HttpSession session = req.getSession();
        String user_id = (String) session.getAttribute("user_id");
        System.out.println("Session user_id: " + user_id);

        if (user_id == null || user_id.isEmpty()) {
            model.addAttribute("error", "로그인이 필요합니다.");
            return "login"; // 로그인 페이지로 리다이렉트
        }

        String cart_id = cartService.findCartId(user_id);
        if (cart_id == null || cart_id.isEmpty()) {
            cart_id = cartService.createNewCart(new CartDto(user_id)); // 새로운 장바구니 생성
            session.setAttribute("cart_id", cart_id); // 세션에 cart_id 설정
        }

        CartDto cartDto = new CartDto(cart_id, product_id, number, product_price);
        int updateNum = cartService.addCart(cartDto);
        System.out.println(updateNum + "행 갱신");

        List<CartDto> cartItems = cartService.listCartItems(user_id);
        int totalPrice = 0;
        int totalQuantity = 0;

        for (CartDto item : cartItems) {
            totalPrice += item.getProduct_price() * item.getQuantity();
            totalQuantity += item.getQuantity();
        }

        if (cartItems.isEmpty()) {
            model.addAttribute("error", "장바구니에 항목이 없습니다.");
        } else {
            model.addAttribute("cartItems", cartItems);
            model.addAttribute("totalPrice", totalPrice);
            model.addAttribute("totalQuantity", totalQuantity);
        }

        if (redirect) {
            return "cart/cart_itemList2"; // Cart 화면으로 넘겨주세요.
        } else {
            String referer = req.getHeader("Referer");
            return "redirect:" + referer; // 이전 페이지로 리다이렉트
        }
    }


    
    @RequestMapping(value = "/revertState", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> revertCartState(@RequestBody Map<String, String> request) {
        String cartId = request.get("cart_id");
        CartDto cartDto = new CartDto(cartId, "장바구니");
        cartService.updateCartState(cartDto);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        return response;
    }

	public CartService getCartService() {
		return cartService;
	}

	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}


}