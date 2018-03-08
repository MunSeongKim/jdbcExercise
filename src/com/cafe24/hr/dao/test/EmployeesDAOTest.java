package com.cafe24.hr.dao.test;

import java.util.List;

import com.cafe24.hr.dao.EmployeesDAO;
import com.cafe24.hr.vo.EmployeesVO;

public class EmployeesDAOTest {

	public static void main(String[] args) {
		getTest(110022L);
		//updateTest();
		//getListTest();
	}

	public static void getListTest() {
		EmployeesDAO dao = new EmployeesDAO();
		List<EmployeesVO> list = dao.getList(1);
		for (EmployeesVO vo : list) {
			System.out.println(vo);
		}
	}

	public static void updateTest() {
		EmployeesDAO dao = new EmployeesDAO();
		EmployeesVO vo = new EmployeesVO();
		
		vo.setEmpNo(110022L);
		vo.setBirthDate( "2018-03-08" );
		vo.setFirstName("마");
		vo.setLastName("이콜");
		vo.setGender("M");
		vo.setHireDate("2018-03-08");
		dao.update(vo);
	}
	
	public static void getTest(Long no) {
		EmployeesDAO dao = new EmployeesDAO();
		System.out.println(dao.get(no));
	}
	
	
}
