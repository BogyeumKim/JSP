package model;

public class Emp {

	private int empno;
	private String ename;
	private int sal;
	private String job;
	
	
	
	
	public Emp(int empno, String ename, int sal, String job) {
	
		this.empno = empno;
		this.ename = ename;
		this.sal = sal;
		this.job = job;
	}

	public Emp(){
		
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Override
	public String toString() {
		return "Emp [ename=" + ename + ", empno=" + empno + ", sal=" + sal + ", job=" + job + "]";
	}
	
	
	
	
}
