package sms.dto;

// ������ ��ȸ���� ����ϴ� DTO
// ��� ��ȸ, �԰�, ��� ��ȸ���� ����� ����
// pull request ���� �߰��� ������ �ۼ��� �ּ���

public class ProductDto {
	private String product_id; // ��ǰ id
	private String product_name; // ��ǰ��
	private String description; // �󼼼���
	private String product_price; // �ܰ� ����
	private String manufacture_name; // ȸ���
	private String manufacture_address; // ȸ�� �ּ�
	private String category_id; // �з� id
	private String category_name; // �з� �̸�
	private String product_imgurl; // ǥ�� url
	private int product_page; // �� ������ �� 
	private String state; // ����
	private int quantity; // ����
	
	public ProductDto() {}

	// ��ü ������ ��ȸ
	public ProductDto(String id, String name, String description, String price, String manufactureName,
			String manufactureAddress, String categoryId, String categoryName, String imgurl, int page, String state,
			int quantity) {
		product_id  = id;
		product_name = name;
		this.description = description;
		product_price = price;
		manufacture_name = manufactureName;
		manufacture_address = manufactureAddress;
		category_id = categoryId;
		category_name = categoryName;
		product_imgurl = imgurl;
		product_page = page;
		this.state = state;
		this.quantity = quantity;
	}
	
	//������ ��ü ��ȸ
	public ProductDto(String id, String name, String price, String manufactureName, String manufactureAddress,
			String categoryId, String categoryName, int page, String state, int quantity) {
		product_id = id;
		product_name = name;
		product_price = price;
		manufacture_name = manufactureName;
		manufacture_address = manufactureAddress;
		category_id = categoryId;
		category_name = categoryName;
		product_page = page;
		this.state = state;
		this.quantity = quantity;
	}

	//����� ��ü ��ȸ
	public ProductDto(String name, String price, String categoryName, int page, String state) {
		product_name = name;
		product_price = price;
		category_name = categoryName;
		product_page = page;
		this.state = state;
	}

	//����� �� ��ȸ
	public ProductDto(String name, String description, String price, String manufactureName, String categoryName,
			String imgurl, int page, String state) {
			product_name = name;
		this.description = description;
		product_price = price;
		manufacture_name = manufactureName;
		category_name = categoryName;
		product_imgurl = imgurl;
		product_page = page;
		this.state = state;
	}
	
	//test_������
	public ProductDto(String id, String name, String price, String manufactureName, String categoryName, String imgurl,
			String state) {
		product_id = id;
		product_name = name;
		product_price = price;
		manufacture_name = manufactureName;
		category_name = categoryName;
		product_imgurl = imgurl;
		this.state = state;
	}
	

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProduct_price() {
		return product_price;
	}

	public void setProduct_price(String product_price) {
		this.product_price = product_price;
	}
	
	public String getManufacture_name() {
		return manufacture_name;
	}

	public void setManufacture_name(String manufacture_name) {
		this.manufacture_name = manufacture_name;
	}

	public String getManufacture_address() {
		return manufacture_address;
	}

	public void setManufacture_address(String manufacture_address) {
		this.manufacture_address = manufacture_address;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getProduct_imgurl() {
		return product_imgurl;
	}

	public void setProduct_imgurl(String product_imgurl) {
		this.product_imgurl = product_imgurl;
	}

	public int getProduct_page() {
		return product_page;
	}

	public void setProduct_page(int product_page) {
		this.product_page = product_page;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
