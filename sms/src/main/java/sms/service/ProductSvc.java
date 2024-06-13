package sms.service;

import java.util.List;
import java.util.Map;

import sms.dto.Category;
import sms.dto.ProductDto;
import sms.dto.SearchDto;

public interface ProductSvc {
	List<Category> categoryList();
	// �˻��� ���� ī�װ� ����Ʈ
	
	int selectProductCount();
	// ��ǰ�� ���� ���
	
	List<ProductDto> productList(Map<String, Object> param);
	// ����ڿ��� �����Ǵ� ��ü List�� �޾ƿ�

	ProductDto productInfo(String ProductId);
	// id������ ��ü�� �޾ƿ�, �� ��ȸ���� ���
	
	List<ProductDto> productSearchList(SearchDto searchDto, Map<String, Object> param);
	int productSearchListNum(SearchDto searchDto);
	// �з��� ���ڸ� �Է� �޾Ƽ� ��ü ����Ʈ�� ã�ƿ�, ���ڸ� ID, ��ü ��� ��
}