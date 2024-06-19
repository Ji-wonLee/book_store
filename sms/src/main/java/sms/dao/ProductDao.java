package sms.dao;

import java.util.List;
import java.util.Map;

import sms.dto.Category;
import sms.dto.ProductDto;
import sms.dto.SearchDto;

public interface ProductDao {
	List<Category> categoryList();
	// �˻��� ���� ī�װ� ����Ʈ
	
	int selectProductCount();
	// ��ü ����
	
	List<ProductDto> productList(Map<String, Object> param);
	// ����ڿ��� �����Ǵ� ��ü List�� �޾ƿ�
	
	ProductDto productInfo(String ProductId);
	// id������ ��ü�� �޾ƿ�, �� ��ȸ���� ���
	
	List<ProductDto> productSearchWithText(String searchText, Map<String, Object> param);
	int productSearchWithTextNum(String searchText);
	// �Է�â�� ���ڸ� �˻��ؼ� ��ü list�� ã�ƿ�, �ش� ���ڸ� ��üID �Ǵ� ��ü ��� ��
	
	List<ProductDto> productSearchWithCategory(String categoryId, Map<String, Object> param);
	int productSearchWithCategoryNum(String categoryId);
	// �з��� �˻��Ͽ� ��ü list�� ã�ƿ�
	
	List<ProductDto> productSearchDual(SearchDto searchDto, Map<String, Object> param);
	int productSearchDualNum(SearchDto searchDto);
	// �з��� ���ڸ� �Է� �޾Ƽ� ��ü ����Ʈ�� ã�ƿ�, ���ڸ� ID, ��ü ��� ��
} 