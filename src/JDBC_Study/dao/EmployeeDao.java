package JDBC_Study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import JDBC_Study.dto.Employee;
import JDBC_Study.jdbc.DBcon;
import JDBC_Study.jdbc.jdbcUtil;

public class EmployeeDao {
	private static final EmployeeDao instance = new EmployeeDao();

	private String sql;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Connection con;

	private EmployeeDao() {}

	public static EmployeeDao getInstance() {
		return instance;
	}

	public List<Employee> selectEmployeeByAll() {
		List<Employee> lists = new ArrayList<>();
		sql = "select empNo, empName, title, manager, salary, dno, leaveOffic from employee";
		con = DBcon.getInstance().getConn();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				int empNo = rs.getInt("empNo");
				String empName = rs.getString("empName");
				String title = rs.getString("title");
				int manager = rs.getInt("manager");
				int salary = rs.getInt("salary");
				int dno = rs.getInt("dno");
				String leaveOffic = rs.getString("leaveOffic");
				lists.add(new Employee(empNo, empName, title, manager, salary, dno, leaveOffic));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(pstmt);
		}
		return lists;
	}

	public Employee selectEmployeeByNo(Employee emp){
		Employee employee = null;
		sql = "select * from employee where empno=?";
		con = DBcon.getInstance().getConn();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, emp.getEmpNo());
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				int empNo = rs.getInt("empNo");
				String empName = rs.getString("empName");
				String title = rs.getString("title");
				int manager = rs.getInt("manager");
				int salary = rs.getInt("salary");
				int dno = rs.getInt("dno");
				String leaveOffic = rs.getString("leaveOffic");
				return new Employee(empNo, empName, title, manager, salary, dno, leaveOffic);
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employee;
	}
	
	public void insertEmployee(Employee emp){
		sql = "insert into employee values(?,?,?,?,?,?,?)";
		con = DBcon.getInstance().getConn();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, emp.getEmpNo());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setString(3, emp.getTitle());
			pstmt.setInt(4, emp.getManager());
			pstmt.setInt(5, emp.getSalary());
			pstmt.setInt(6, emp.getDno());
			pstmt.setString(7, emp.getLeaveOffic());
			
			int res = pstmt.executeUpdate();
			if(res<0){
				System.out.println("삽입 실패");
				return;
			}
			JOptionPane.showMessageDialog(null, emp + " 추가되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(pstmt);
		}
	}
	
	public void updateEmployee(Employee emp){
		sql = "update employee set title=? where empno=?";
		con = DBcon.getInstance().getConn();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, emp.getTitle());
			pstmt.setInt(2, emp.getEmpNo());
			
			int res = pstmt.executeUpdate();
			if(res<0){
				System.out.println("수정 실패");
				return;
			}
			JOptionPane.showMessageDialog(null, emp + " 수정되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(pstmt);
		}
	}
	
	public void deleteEmployee(Employee emp){
		sql = "delete from employee where empno=?";
		con = DBcon.getInstance().getConn();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, emp.getEmpNo());
			
			int res = pstmt.executeUpdate();
			if(res<0){
				System.out.println("삭제 실패");
				return;
			}
			JOptionPane.showMessageDialog(null, "삭제되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(pstmt);
		}
	}
}















