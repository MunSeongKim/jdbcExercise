package com.cafe24.music.dao.test;

import java.util.List;

import com.cafe24.music.dao.ArtistDAO;
import com.cafe24.music.vo.ArtistVO;

public class ArtistDAOTest {

    public static void main( String[] args ) {
	getListTest();
	//insertTest();
    }

    private static void getListTest() {
	ArtistDAO dao = new ArtistDAO();

	List<ArtistVO> list = dao.getList();
	for ( ArtistVO vo : list ) {
	    System.out.println( vo );
	}
    }

    private static void insertTest() {
	ArtistDAO dao = new ArtistDAO();
	ArtistVO vo = new ArtistVO();

	vo.setName( "아이유" );
	dao.insert( vo );
    }

}
