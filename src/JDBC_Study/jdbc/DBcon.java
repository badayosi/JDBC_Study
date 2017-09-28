package JDBC_Study.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBcon {
	private String user="root";
	private String pass="rootroot";
	private String url="jdbc:mysql://localhost:3306/mysql_study01";
	
	//싱글턴 패턴 사용
	private static Connection conn;
	private static DBcon instance = new DBcon();
	
	public static DBcon getInstance() {
		return instance;
	}

	private DBcon() {
		try {
			conn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			System.err.printf("%s - %s\n",e.getErrorCode(),e.getMessage());
			e.printStackTrace();
		}
	}

	public Connection getConn() {
		return conn;
	}
	
	public void connClose(){
		if(conn != null){
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
