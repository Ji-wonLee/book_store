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
	public String productList(@RequestParam(value="cPage", defaultValue="1") int cPage,
							  @RequestParam(value="numPerpage", defaultValue="20") int numPerpage, 
							  ModelMap model, HttpServletRequest req) {
		List<Category> categorylist = productSvc.categoryList();
		
		HttpSession session=req.getSession();
		String userId = (String) session.getAttribute("user_id");
		model.addAttribute("user_id", userId); // user_id�� �������� ������
		
		List<ProductDto> productlist = productSvc.productList(Map.of("cPage", cPage, "numPerpage", numPerpage));
		int totalData = productSvc.selectProductCount();
		// ��ü ����
		
		model.addAttribute("categorylist",categorylist);
		//ī�װ� ���
		model.addAttribute("cPage", cPage); // ���� ������
		model.addAttribute("totalData", totalData); // ��ü ��ü ��
		model.addAttribute("productlist", productlist);
		//��ü ���
		model.addAttribute("pageBar", pageFactory.getPageBar(totalData, cPage, numPerpage, "/sms/customermain?"));
		// pageBar�� ���
		return "product/productMain"; //����, ������ ��ġ �Է�
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String testSearch(@RequestParam(value="cPage", defaultValue="1") int cPage,
							 @RequestParam(value="numPerpage", defaultValue="20") int numPerpage,
			  				 @RequestParam(value="category_id") String category_id,
							 @RequestParam(value="searchtext") String searchtext,
							 ModelMap model) {
		SearchDto searchDto = new SearchDto(searchtext, category_id);
		int searchData = productSvc.productSearchListNum(searchDto);
		model.addAttribute("categorylist", productSvc.categoryList());
		model.addAttribute("cPage", cPage); // ���� ������
		model.addAttribute("totalData", searchData); // ��ü ��
		model.addAttribute("productlist", productSvc.productSearchList(searchDto, Map.of("cPage", cPage, "numPerpage", numPerpage)));
		model.addAttribute("pageBar", pageFactory.getPageBar(searchData, cPage, numPerpage, "/sms/search?category_id=" + category_id + "&searchtext=" + searchtext));
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