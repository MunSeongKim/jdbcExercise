package com.cafe24.hr.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.cafe24.hr.vo.EmployeesVO;

public class EmployeesDAO {
	private static final int LIST_COUNT = 100;
	
	public EmployeesVO get(Long no) {
		EmployeesVO vo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			String sql = "SELECT emp_no, date_format(birth_date, '%Y-%m-%d'), first_name, last_name, gender, date_format(hire_date, '%Y-%m-%d')"
					+ " FROM employees WHERE emp_no=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new EmployeesVO();
				vo.setEmpNo(rs.getLong(1));
				vo.setBirthDate(rs.getString(2));
				vo.setFirstName(rs.getString(3));
				vo.setLastName(rs.getString(4));
				vo.setGender(rs.getString(5));
				vo.setHireDate(rs.getString(6));
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return vo;
	}

	public boolean update(EmployeesVO vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql = "UPDATE employees SET birth_date=?, first_name=?, last_name=?, gender=?, hire_date=? WHERE emp_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getBirthDate());
			pstmt.setString(2, vo.getFirstName());
			pstmt.setString(3, vo.getLastName());
			pstmt.setString(4, vo.getGender());
			pstmt.setString(5, vo.getHireDate());
			pstmt.setLong(6, vo.getEmpNo());
						
			int count = pstmt.executeUpdate();
			result = (count == 1);

		} catch (SQLException e) {
			System.out.println("Error: " + e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public List<EmployeesVO> getList(int page) {
		List<EmployeesVO> list = new ArrayList<EmployeesVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			String sql = "SELECT emp_no, date_format(birth_date, '%Y-%m-%d'), first_name, last_name, gender, date_format(hire_date, '%Y-%m-%d')"
					+ " FROM employees" + " ORDER BY hire_date ASC" + " LIMIT ?, ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ((page - 1) * LIST_COUNT));
			pstmt.setInt(2, LIST_COUNT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				EmployeesVO vo = new EmployeesVO();
				vo.setEmpNo(rs.getLong(1));
				vo.setBirthDate(rs.getString(2));
				vo.setFirstName(rs.getString(3));
				vo.setLastName(rs.getString(4));
				vo.setGender(rs.getString(5));
				vo.setHireDate(rs.getString(6));
				list.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/employees";
			conn = DriverManager.getConnection(url, "hr", "hr");
		} catch (ClassNotFoundException e) {
			System.out.println("Failed to load driver : " + e);
		}

		return conn;
	}
}
