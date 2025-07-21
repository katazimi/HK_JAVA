package hk.edu_20250708.day05;

public class D4_Member {
	public String name;
	private String id;
	private String password;
	public int age;

	public D4_Member() {
		this("홍길동", "hong");
		this.password = "12345";
	}

	public D4_Member(String name, String id) {
		this.name = name;
		this.id = id;
	}

	public String getID() {
		return this.id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



}
