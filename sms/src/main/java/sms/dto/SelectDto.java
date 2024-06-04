// Select ��ü���� ���߿� Cart�� ���ļ� ����(Dto, Dao, Mapper, Svc, Controller)

package sms.dto;

public class SelectDto {
	
	private String product_id; //��ǰ ��ȣ
	private int number; // ����
	private String state; // ��ǰ����
	private int price;				// �ܰ�
	private String product_name; //��ǰ��
	
	public SelectDto() {}
	
	public SelectDto(String product_id, int number, String state, int price, String product_name) {
		this.product_id = product_id;
		this.number = number;
		this.state = state;
		this.price = price;
		this.product_name = product_name;
	} // ��ü �Ӽ��� �޾ƿ��� ��ü
		
	public SelectDto(String product_id, int price, String product_name) {
		this.product_id = product_id;
		this.price = price;
		this.product_name = product_name;
	} // Cart�� �����ִ� ��ü

	public SelectDto(String product_id, int number) {
		this.product_id = product_id;
		this.number = number;
	} // info ���� �޾ƿ��� ��ü

	public SelectDto(String product_id, int number, int price, String product_name) {
		this.product_id = product_id;
		this.number = number;
		this.price = price;
		this.product_name = product_name;
	} // Cart�� ����Ǵ� ��ü **����

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
}