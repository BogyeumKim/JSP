package member.model;

import java.sql.Date;

public class Member {

	
	// 우선 사용자에게 받아야할것들 
	
	private int idx;
	private String uid;
	private String upw;
	private String uname;
	private String uphoto;
	private Date regdate;  //util이 아니라 sql로 받기 
	
	public Member(int idx, String uid, String upw, String uname, String uphoto, Date regdate) {
		this.idx = idx;
		this.uid = uid;
		this.upw = upw;
		this.uname = uname;
		this.uphoto = uphoto;
		this.regdate = regdate;
	}

	
	public Member() {
	}


	public int getIdx() {
		return idx;
	}


	public void setIdx(int idx) {
		this.idx = idx;
	}


	public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}


	public String getUpw() {
		return upw;
	}


	public void setUpw(String upw) {
		this.upw = upw;
	}


	public String getUname() {
		return uname;
	}


	public void setUname(String uname) {
		this.uname = uname;
	}


	public String getUphoto() {
		return uphoto;
	}


	public void setUphoto(String uphoto) {
		this.uphoto = uphoto;
	}


	public Date getRegdate() {
		return regdate;
	}


	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	// getRegdate 1970년 나오는걸 현재로 처리하기위한것 
	// java.sql.Date -> java.util.Date
	public java.util.Date getTodate(){ // ${member.toDate}
		return new java.util.Date(regdate.getTime());
		
	}

	

	@Override
	public String toString() {
		return "Member [idx=" + idx + ", uid=" + uid + ", upw=" + upw + ", uname=" + uname + ", uphoto=" + uphoto
				+ ", regdate=" + regdate + "]";
	}
	
	
	
	
	
	
}
