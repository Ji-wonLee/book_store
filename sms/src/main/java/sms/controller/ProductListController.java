package sms.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import sms.dto.Category;
import sms.dto.ProductDto;
import sms.dto.SearchDto;
import sms.factory.PageFactory;
import sms.service.ProductSvc;

@Controller
public class ProductListController {
	//����� ����ȭ�� + ����� ��ǰ ��ü ��ȸ
	
	@Autowired
	private PageFactory pageFactory;
	@Autowired
	private ProductSvc productSvc;
	
	@RequestMapping(value="/customermain", method=RequestMethod.GET) // ����, ������ ȣ�� �Է�
	public String productList(@RequestParam(value="currentPage", defaultValue="1") int currentPage,
							  @RequestParam(value="numPerpage", defaultValue="20") int numPerpage, 
							  ModelMap model, HttpServletRequest req) {
		List<Category> categorylist = productSvc.categoryList();
		
		HttpSession session=req.getSession();
		String userId = (String) session.getAttribute("user_id");
		model.addAttribute("user_id", userId); // user_id�� �������� ������
		
		List<ProductDto> productList = productSvc.productList(Map.of("currentPage", currentPage, "numPerpage", numPerpage));
		int totalData = productSvc.selectProductCount();
		// ��ü ����
		System.out.println(totalData + " : " + currentPage + " : " + numPerpage + " : ");
		model.addAttribute("categorylist",categorylist);
		System.out.println(pageFactory);
		pageFactory.getPageBar(totalData, currentPage, numPerpage, "/sms/customermain");
		// ī�װ� ���
		model.addAttribute("productList", productList);
		//��ü ���
		model.addAttribute("pageBar", pageFactory.getPageBar(totalData, currentPage, numPerpage, "/sms/customermain"));
		// pageBar�� ���
		return "product/productMain"; //����, ������ ��ġ �Է�
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

	public PageFactory getPageFactory() {
		return pageFactory;
	}

	public void setPageFactory(PageFactory pageFactory) {
		this.pageFactory = pageFactory;
	}
}