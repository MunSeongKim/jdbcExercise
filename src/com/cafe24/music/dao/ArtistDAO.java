package com.cafe24.music.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cafe24.music.vo.ArtistVO;

public class ArtistDAO {
    public List<ArtistVO> getList() {
	List<ArtistVO> list = new ArrayList<ArtistVO>();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {
	    conn = getConnection();
	    String sql = "SELECT no, name FROM artist";
	    pstmt = conn.prepareStatement( sql );

	    rs = pstmt.executeQuery();
	    while( rs.next() ){
		ArtistVO vo = new ArtistVO();
		vo.setNo(rs.getLong(1));
		vo.setName(rs.getString(2));
		list.add(vo);
	    }
	} catch ( SQLException e ) {
	    e.printStackTrace();
	} finally {
	    try {
		if ( conn != null ) conn.close();
	    } catch ( SQLException e ) {
		e.printStackTrace();
	    }
	}
	
	return list;
    }
	
	
    public boolean insert( ArtistVO vo ) {
	boolean result = false;
	Connection conn = null;
	PreparedStatement pstmt = null;

	try {
	    conn = getConnection();
	    String sql = "insert into artist values(null, ?)";
	    pstmt = conn.prepareStatement( sql );

	    pstmt.setString( 1, vo.getName() );
	    int count = pstmt.executeUpdate();

	    result = (count == 1);
	} catch ( SQLException e ) {
	    e.printStackTrace();
	} finally {
	    try {
		if ( conn != null ) conn.close();
	    } catch ( SQLException e ) {
		e.printStackTrace();
	    }
	}

	return result;
    }

    private Connection getConnection() throws SQLException {
	Connection conn = null;

	try {
	    Class.forName( "com.mysql.jdbc.Driver" );
	    String url = "jdbc:mysql://localhost:3306/dev";
	    conn = DriverManager.getConnection( url, "dev", "dev" );
	} catch ( ClassNotFoundException e ) {
	    System.out.println( "Failed to load driver : " + e );
	}

	return conn;
    }
}
