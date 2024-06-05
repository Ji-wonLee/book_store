package sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import sms.dto.Category;
import sms.dto.SearchDto;
import sms.service.ProductSvc;

@Controller
public class ProductListController {
	//사용자 메인화면 + 사용자 제품 전체 조회
	
	@Autowired
	private ProductSvc productSvc;
	
	@RequestMapping(value="/customermain", method=RequestMethod.GET) // 수정, 페이지 호출 입력
	public String productList(ModelMap model, HttpServletRequest req) {
		List<Category> categorylist = productSvc.categoryList();
		
		HttpSession session=req.getSession();
		String userId = (String) session.getAttribute("user_id");
		model.addAttribute("user_id", userId); // user_id를 세션으로 가져옴
		
		model.addAttribute("categorylist",categorylist);
		model.addAttribute("productlist", productSvc.productList());
		return "product/productMain"; //수정, 페이지 위치 입력
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String testSearch(@RequestParam(value="category_id") String category_id,
							 @RequestParam(value="searchtext") String searchtext,
							 ModelMap model) {
		SearchDto searchDto = new SearchDto(searchtext, category_id);
		model.addAttribute("categorylist", productSvc.categoryList());
		model.addAttribute("productlist", productSvc.productSearchList(searchDto));
		return "product/productMain";
	}
	
	public ProductSvc getProductSvc() {
		return productSvc;
	}

	public void setProductSvc(ProductSvc productSvc) {
		this.productSvc = productSvc;
	}
}