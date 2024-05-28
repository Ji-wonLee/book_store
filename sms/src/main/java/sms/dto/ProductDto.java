package sms.dto;

// ������ ��ȸ���� ����ϴ� DTO
// ��� ��ȸ, �԰�, ��� ��ȸ���� ����� ����
// pull request ���� �߰��� ������ �ۼ��� �ּ���

public class ProductDto {
	private String id; // ��ǰ id
	private String name; // ��ǰ��
	private String description; // �󼼼���
	private String price; // �ܰ� ����
	private String manufactureName; // ȸ���
	private String manufactureAddress; // ȸ�� �ּ�
	private String categoryId; // �з� id
	private String categoryName; // �з� �̸�
	private String imgurl; // ǥ�� url
	private int page; // �� ������ �� 
	private String state; // ����
	private int quantity; // ����
	
	public ProductDto() {}

	// ��ü ������ ��ȸ
	public ProductDto(String id, String name, String description, String price, String manufactureName,
			String manufactureAddress, String categoryId, String categoryName, String imgurl, int page, String state,
			int quantity) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.manufactureName = manufactureName;
		this.manufactureAddress = manufactureAddress;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.imgurl = imgurl;
		this.page = page;
		this.state = state;
		this.quantity = quantity;
	}
	
	//������ ��ü ��ȸ
	public ProductDto(String id, String name, String price, String manufactureName, String manufactureAddress,
			String categoryId, String categoryName, int page, String state, int quantity) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.manufactureName = manufactureName;
		this.manufactureAddress = manufactureAddress;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.page = page;
		this.state = state;
		this.quantity = quantity;
	}

	//����� ��ü ��ȸ
	public ProductDto(String name, String price, String categoryName, int page, String state) {
		this.name = name;
		this.price = price;
		this.categoryName = categoryName;
		this.page = page;
		this.state = state;
	}

	//����� �� ��ȸ
	public ProductDto(String name, String description, String price, String manufactureName, String categoryName,
			String imgurl, int page, String state) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.manufactureName = manufactureName;
		this.categoryName = categoryName;
		this.imgurl = imgurl;
		this.page = page;
		this.state = state;
	}
	
	//test_������
	public ProductDto(String id, String name, String price, String manufactureName, String categoryName, String imgurl,
			String state) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.manufactureName = manufactureName;
		this.categoryName = categoryName;
		this.imgurl = imgurl;
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getManufactureName() {
		return manufactureName;
	}

	public void setManufactureName(String manufactureName) {
		this.manufactureName = manufactureName;
	}

	public String getManufactureAddress() {
		return manufactureAddress;
	}

	public void setManufactureAddress(String manufactureAddress) {
		this.manufactureAddress = manufactureAddress;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
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

	//���� ����
	@Override
	public String toString() {
		return "ProductDto [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", manufactureName=" + manufactureName + ", manufactureAddress=" + manufactureAddress
				+ ", categoryId=" + categoryId + ", categoryName=" + categoryName + ", imgurl=" + imgurl + ", page="
				+ page + ", state=" + state + ", quantity=" + quantity + "]";
	}
}
