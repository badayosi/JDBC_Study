package JDBC_Study.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class jdbcUtil {
	public static void close(ResultSet rs){
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement pstmt){
		try {
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
