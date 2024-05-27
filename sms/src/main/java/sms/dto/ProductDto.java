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

	//���� ����
	@Override
	public String toString() {
		return "ProductDto [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", manufactureName=" + manufactureName + ", manufactureAddress=" + manufactureAddress
				+ ", categoryId=" + categoryId + ", categoryName=" + categoryName + ", imgurl=" + imgurl + ", page="
				+ page + ", state=" + state + ", quantity=" + quantity + "]";
	}
}