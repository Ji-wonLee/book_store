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

import sms.dao.CartDao;
import sms.dto.CartDto;
import sms.dto.ProductDto;
import sms.service.CartService;

import java.util.List;

@Controller
public class CartController {

	@Autowired
	CartService cartService; 

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public String testMethod(ModelMap model) {
	    model.addAttribute("message", "테스트 페이지");
	    return "test"; // test.jsp 파일이 있어야 합니다.
	}
//	/**
//     * 특정 사용자의 장바구니 항목을 조회합니다.
//     * @param userId 사용자 ID (옵션)
//     * @param model 모델 객체
//     * @return 장바구니 항목 리스트를 보여주는 JSP 페이지
//     */
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public String listCartItems(@RequestParam(name = "user_id", required = false) String user_id, ModelMap model) {
//        if (user_id == null || user_id.isEmpty()) {
//        	user_id = "guest"; // 비회원용 기본값 설정
//        }
//        List<CartDto> cartItems = cartService.listCartItems(user_id);
//        model.addAttribute("cartItems", cartItems);
//        model.addAttribute("userId", user_id); // userId도 함께 모델에 추가
//        return "cart/cart_itemList"; // JSP 파일의 경로
//    }
    /**
     * 특정 사용자의 장바구니 항목을 조회합니다.
     * @param userId 사용자 ID (옵션)
     * @param model 모델 객체
     * @return 장바구니 항목 리스트를 보여주는 JSP 페이지
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listCartItems(@RequestParam(name = "userId", required = false) String userId, ModelMap model) {
        if (userId == null || userId.isEmpty()) {
            userId = "guest"; // 비회원용 기본값 설정
        }
        List<CartDto> cartItems = cartService.listCartItems(userId);
        if (cartItems.isEmpty()) {
            model.addAttribute("error", "장바구니에 항목이 없습니다.");
        } else {
            model.addAttribute("cartItems", cartItems);
        }
        model.addAttribute("userId", userId); // userId도 함께 모델에 추가
        return "cart/cart_itemList"; // JSP 파일의 경로
    }
	/**
	 * 장바구니 내 상품의 수량을 업데이트하고 총액을 재계산합니다.
	 * @param cartItemUpdateDto 업데이트할 상품 정보
	 * @param model 모델 객체
	 * @return 장바구니 리스트 페이지로 리다이렉트
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateCartItemAndTotal(@ModelAttribute CartDto cartItemUpdateDto, ModelMap model) {
		cartService.updateCartItemAndTotal(cartItemUpdateDto);
		return "redirect:/cart";
	}

	/**
	 * 장바구니에 상품을 추가합니다.
	 * @param cartItemUpdateDto 추가할 상품 정보
	 * @param model 모델 객체
	 * @return 장바구니 리스트 페이지로 리다이렉트
	 */
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String addProductToCartDetails(@ModelAttribute CartDto cartItemUpdateDto, ModelMap model) {
		cartService.addProductToCartDetails(cartItemUpdateDto);
		return "redirect:/cart";
	}

	/**
	 * 장바구니 상태를 업데이트합니다.
	 * @param cartStatusUpdateDto 업데이트할 상태 정보
	 * @param model 모델 객체
	 * @return 장바구니 리스트 페이지로 리다이렉트
	 */
	@RequestMapping(value = "/updateState", method = RequestMethod.POST)
	public String updateCartState(@ModelAttribute CartDto cartStatusUpdateDto, ModelMap model) {
		cartService.updateCartState(cartStatusUpdateDto);
		return "redirect:/cart";
	}

	/**
	 * 상품 등록 폼을 보여줍니다.
	 * @param model 모델 객체
	 * @return 상품 등록 폼 JSP 페이지
	 */
	@RequestMapping(value = "/itemRegistration", method = RequestMethod.GET)
	public String showCartItemRegistrationForm(ModelMap model) {
		return "cart_item_registration";
	}
}