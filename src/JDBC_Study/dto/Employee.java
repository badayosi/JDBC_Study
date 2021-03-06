package JDBC_Study.dto;

public class Employee {
	private int empNo;
	private String empName;
	private String title;
	private int manager;
	private int salary;
	private int dno;
	private String leaveOffic;

	public Employee(int empNo) {
		this.empNo = empNo;
	}

	public Employee(int empNo, String empName, String title, int manager, int salary, int dno, String leaveOffic) {
		this.empNo = empNo;
		this.empName = empName;
		this.title = title;
		this.manager = manager;
		this.salary = salary;
		this.dno = dno;
		this.leaveOffic = leaveOffic;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getManager() {
		return manager;
	}

	public void setManager(int manager) {
		this.manager = manager;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getDno() {
		return dno;
	}

	public void setDno(int dno) {
		this.dno = dno;
	}

	public String getLeaveOffic() {
		return leaveOffic;
	}

	public void setLeaveOffic(String leaveOffic) {
		this.leaveOffic = leaveOffic;
	}

	@Override
	public String toString() {
		return String.format("Employee [empNo=%s, empName=%s, title=%s, manager=%s, salary=%s, dno=%s, leaveOffic=%s]",
				empNo, empName, title, manager, salary, dno, leaveOffic);
	}
	
	public Object[] toArray(){
		return new Object[] {empNo, empName, title, manager, salary, dno, leaveOffic};
	}
}
