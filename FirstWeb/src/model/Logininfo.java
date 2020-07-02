package model;

public class Logininfo {

	
	private String uid;
	private String name;
	
	
	// 빈즈클래스에서는 생성자 필수
	public Logininfo() {
//		uid = "cool";
//		name = "손흥민";
		
	}


	public Logininfo(String uid, String name) {
		super();
		this.uid=uid;
		this.name=name;
	}


	public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "Logininfo [uid=" + uid + ", name=" + name + "]";
	}
	
	
	
}
