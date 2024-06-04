package sms.dao;

import java.util.List;

import sms.dto.Category;
import sms.dto.ProductDto;
import sms.dto.SearchDto;

public interface ProductDao {
	List<Category> categoryList();
	// �˻��� ���� ī�װ� ����Ʈ
	
	List<ProductDto> productList();
	// ����ڿ��� �����Ǵ� ��ü List�� �޾ƿ�
	
	ProductDto productInfo(String product_id);
	// id������ ��ü�� �޾ƿ�, �� ��ȸ���� ���
	
	List<ProductDto> productSearchWithText(String searchText);
	// �Է�â�� ���ڸ� �˻��ؼ� ��ü list�� ã�ƿ�, �ش� ���ڸ� ��üID �Ǵ� ��ü ��� ��
	
	List<ProductDto> productSearchWithCategory(String categoryId);
	// �з��� �˻��Ͽ� ��ü list�� ã�ƿ�
	
	List<ProductDto> productSearchDual(SearchDto searchDto);
	// �з��� ���ڸ� �Է� �޾Ƽ� ��ü ����Ʈ�� ã�ƿ�, ���ڸ� ID, ��ü ��� ��
} 