package jdbc;

import java.sql.*;

public class SelectTest {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 1. Loading Driver
			Class.forName("com.mysql.jdbc.Driver");
			// 2. Connecting
			String url = "jdbc:mysql://localhost:3306/dev";
			conn = DriverManager.getConnection(url, "dev", "dev");
			// 3. Create a Statement object
			stmt = conn.createStatement();

			// 4. Execute SQL
			String sql = "select name," + "       owner," + "       species," + "       gender,"
					+ "       date_format(birth, '%Y-%m-%d')" + "  from pet";
			rs = stmt.executeQuery(sql);
			// 5. 결과 처리
			while (rs.next()) {
				String name = rs.getString(1);
				String owner = rs.getString(2);
				String spcies = rs.getString(3);
				String gender = rs.getString(4);
				String birth = rs.getString(5);
				System.out.println(name + ":" + owner + ":" + spcies + ":" + gender + ":" + birth);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Failed to load driver : " + e);
		} catch (SQLException e) {
			System.out.println("Error: " + e);
		} finally {
			// Clean-Up
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
