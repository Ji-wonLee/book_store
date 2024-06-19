package sms.dto;

public class UsersDto {

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getGrade_no() {
		return grade_no;
	}

	public void setGrade_no(int grade_no) {
		this.grade_no = grade_no;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}
	
	
	public long getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(long phonenum) {
		this.phonenum = phonenum;
	}

	private String user_id;
	private String name;
	private String passwd;
	private String address;
	private long phonenum;
	private int grade_no;
	private String gname;
	
	
	public UsersDto() {}
	
	public UsersDto(String user_id, String name, int grade_no) {
		this.user_id = user_id;
		this.name = name;
		this.grade_no = grade_no;
	}
	
	public UsersDto(String user_id, String passwd) {
		this.user_id = user_id;
		this.passwd = passwd;
	}

	public UsersDto(String user_id, String name, String passwd, String address, long phonenum, int grade_no,
			String gname) {
		this.user_id = user_id;
		this.name = name;
		this.passwd = passwd;
		this.address = address;
		this.phonenum = phonenum;
		this.grade_no = grade_no;
		this.gname = gname;
	}

	@Override
	public String toString() {
		return user_id + ":" + name + ":" + passwd + ":" + address+":"+grade_no+":"+gname+":"+phonenum;
	}
}
