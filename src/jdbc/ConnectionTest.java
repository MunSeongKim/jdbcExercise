package jdbc;

import java.sql.*;

public class ConnectionTest {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			// 1. Loading Driver
			Class.forName("com.mysql.jdbc.Driver");
			//Class.forName("com.cafe24.jdbc.MyDriver");
			// 2. Connecting
			String url = "jdbc:mysql://localhost:3306/dev";
			conn = DriverManager.getConnection(url, "dev", "dev");
			//conn = DriverManager.getConnection(url, null);
			System.out.println("연결성공");
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("Failed to load driver : " + e);
		} catch (SQLException e) {
			System.out.println("Error: " + e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

}
