package jdbc;

import java.sql.*;

public class InsertTest {

	public static void main(String[] args) {
		insert("장군이", "둘리", "개", "M", "2010-05-05", null);

	}
	
	public static void insert(String name, String owner, String species, String gender, String birth, String death) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// 1. Loading Driver
			Class.forName("com.mysql.jdbc.Driver");
			// 2. Connecting
			String url = "jdbc:mysql://localhost:3306/dev";
			conn = DriverManager.getConnection(url, "dev", "dev");
			
			// 3. Ready for SQL query
			String sql = "INSERT INTO pet VALUES (?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			// 4. Data binding
			pstmt.setString(1, name);
			pstmt.setString(2, owner);
			pstmt.setString(3, species);
			pstmt.setString(4, gender);
			pstmt.setString(5, birth);
			pstmt.setString(6, death);
			
			// 5. Execute to query
			int count = pstmt.executeUpdate();
			
			// 6. Processing to result
			if( count == 0 ) {
				System.out.println("Failed to INSERT QUERY");
			}
			System.out.println("INSERT success");
		
		} catch (ClassNotFoundException e) {
			System.out.println("Failed to load driver : " + e);
		} catch (SQLException e) {
			System.out.println("Error: " + e);
		} finally {
			// Clean-Up
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
