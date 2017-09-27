package JDBC_Study;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Connection;

class Student{
	private String id;
	private String name;
	private String dept;
	
	public Student(String id, String name, String dept) {
		this.id = id;
		this.name = name;
		this.dept = dept;
	}
	
	public Student(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return String.format("student [id=%s, name=%s, dept=%s]", id, name, dept);
	}
}

public class DBConnection {

	private static Connection con;

	public static void main(String[] args) {
		
		// 참고 : SQL에서 인덱스를 참조할 때는 0부터 시작하는 것이 아니라 1부터 시작합니다
		
		//일반적으로 url, id, pw를 다른 변수로 두어 관리해준다
		String url = "jdbc:mysql://localhost:3306/sampledb?autoReconnect=true&useSSL=false";
		String user = "user_sampledb";
		String pw = "rootroot";
		
		try {
			//연결되면 자동으로 DriverManager로 등록되어 관리된다
			//MYSQL의 경우 디폴트로 3306 Port가 열린다
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("JDBC Driver를 로딩하였습니다");
			
			con = DriverManager.getConnection(url, user, pw);
			System.out.println("DB 연결 성공");
			
			System.out.println("-- 최초 테이블 --");
			showList(con);
			insertStudent(con, "1234567", "김개똥", "정보보안");
			System.out.println("-- 1차 삽입 후 테이블 --");
			showList(con);
			
			Student updateStd = new Student("1234567", "김개똥", "정보보안");
			updateStudent(con, updateStd);
			showList(con);
			
			Student delStd = new Student("1234567");
			deleteStudent(con, delStd);
			showList(con);
			
			/*
			// 잘 쓰이지 않는 방법이지만 참고하자!
			
			// 처음 레코드로 이동
			boolean res = rs.first();
			System.out.println(res ? "첫 레코드로 이동" : "데이터없음");
			
			// getString()의 ColumnIndex는 0이 아닌 1부터 시작한다
			// getString()은 Index로 접근하거나 Column의 이름을 통해서 접근할 수 있다
			String id = rs.getString(1);
			String name = rs.getString("name");
			String dept = rs.getString(3);
			System.out.printf("id : %s / name : %s / dept : %s \n", id, name, dept);
			
			// 마지막 레코드로 이동
			res = rs.last();
			System.out.println(res ? "마지막 레코드로 이동" : "데이터없음");
			id = rs.getString(1);
			name = rs.getString("name");
			dept = rs.getString(3);
			System.out.printf("id : %s / name : %s / dept : %s \n", id, name, dept);
			
			// 첫번째 레코드로 이동
			rs.absolute(1);
			while(rs.next()){
				id = rs.getString(1);
				name = rs.getString("name");
				dept = rs.getString(3);
				System.out.printf("id : %s / name : %s / dept : %s \n", id, name, dept);
			}
			*/
			
			/*List<Student> stdList = getStudents(con);
			for(Student s : stdList){
				System.out.println(s);
			}*/
			
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver가 존재하지 않습니다.");
		} catch (SQLException e) {
			System.err.println("DB 연결에 실패하였습니다");
			System.err.printf("Err Code : %s \nErr Message : %s \n", e.getErrorCode(), e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static void deleteStudent(Connection con2, Student delStd) {
		String sql = "delete from student where id='1234567";
		
	}

	private static List<Student> getStudents(Connection con2) throws SQLException {
		ArrayList<Student> lists = new ArrayList<>();
		String sql = "select id, name, dept from student";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()){
			lists.add(new Student(rs.getString(1),rs.getString("name"),rs.getString(3)));
		}
		return null;
	}

	private static void updateStudent(Connection con, Student updateStd) throws SQLException {
		String sql = "update student set dept=?, name=? where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, updateStd.getDept());
		pstmt.setString(2, updateStd.getName());
		pstmt.setString(3, updateStd.getId());
		
		int res = pstmt.executeUpdate();
		System.out.println(res + "개 수정");
		
		pstmt.close();
	}

	// SQL 명령문장을 만들기 위한 메소드
	private static void insertStudent(Connection con, String id, String name, String dept) throws SQLException {
		String sql = "insert into student values(?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, name);
		pstmt.setString(3, dept);
				
		//System.out.println(pstmt);
		int res = pstmt.executeUpdate();
		System.out.println(res + "개 삽입");
		
		pstmt.close();
	}

	// 레코드를 전체 출력하기 위한 메소드
	private static void showList(Connection con) throws SQLException {
		// con.createStatement() : SQL문장을 만들겠다는 의미
		// con.prepareStatement() 을 더 추천한다 SQL인젝션 어택을 대비
		Statement stmt = con.createStatement();
		String sql = "select id, name, dept from student";
					
		// stmt.executeUpdate() : 성공한 개수를 int로 반환받음
		// stmt.executeQuery() : ResultSet으로 반환받음
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()){
			String id = rs.getString(1);
			String name = rs.getString("name");
			String dept = rs.getString(3);
			System.out.printf("id : %s / name : %s / dept : %s \n", id, name, dept);
		}
		
		rs.close();
		stmt.close();
	}
}
