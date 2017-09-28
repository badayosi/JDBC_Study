package JDBC_Study;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JOptionPane;

import JDBC_Study.dao.DepartmentDao;
import JDBC_Study.dto.Department;
import JDBC_Study.dao.EmployeeDao;
import JDBC_Study.dto.Employee;
import JDBC_Study.jdbc.CompanyManagerUI;

public class TestMain {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompanyManagerUI frame = new CompanyManagerUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static void testEmployeeDao() {
		EmployeeDao dao = EmployeeDao.getInstance();
		// select
		showEmployeeList(dao);

		// insert
		Employee emp = new Employee(1007, "배수지", "과장", 4377, 4000000, 1, null);
		dao.insertEmployee(emp);
		showEmployeeList(dao);

		// update
		emp.setTitle("부장");
		dao.updateEmployee(emp);
		showEmployeeList(dao);

		// delete
		dao.deleteEmployee(emp);
		showEmployeeList(dao);

		// select where
		Employee searchEmp = dao.selectEmployeeByNo(new Employee(4377));
		JOptionPane.showMessageDialog(null, "찾은 결과 " + searchEmp);
	}

	private static void showEmployeeList(EmployeeDao dao) {
		// TODO Auto-generated method stub
		
	}

	private static void testDepartmentDao() {
		DepartmentDao dao = DepartmentDao.getInstanse();

		// select
		showDepartmentList(dao);

		// insert
		Department dept = new Department(6, "마케팅", 10);
		dao.insertDepartment(dept);
		showDepartmentList(dao);

		// update
		dept.setDeptName("빅데이터 마케팅");
		dao.updateDepartment(dept);
		showDepartmentList(dao);

		// delete
		dao.deleteDepartment(dept);
		showDepartmentList(dao);

		// select where
		Department searchDept = dao.selectDepartmentByNo(new Department(1));
		JOptionPane.showMessageDialog(null, "찾은 결과 " + searchDept);
	}

	private static void showDepartmentList(DepartmentDao dao) {
		System.out.println("======================================");
		List<Department> lists = dao.selectDepartmentByAll();
		for (Department dept : lists) {
			System.out.println(dept);
		}
	}	
}
