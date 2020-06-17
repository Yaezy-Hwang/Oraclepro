package com.javaex.phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneDao {

	//필드
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "phonedb";
	private String pw = "phonedb";
	
	// 0. import java.sql.*;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	//생성자
	public PhoneDao() {};
	
	//메소드
	//SQL 준비
	private void getConnect() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println();
		} catch(ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch(SQLException e) {
		    System.out.println("error:" + e);
		}
	}
	
	//자원정리
	private void close() {
		try {
	        if (rs != null) {
	            rs.close();
	        }                
	        if (pstmt != null) {
	            pstmt.close();
	        }
	        if (conn != null) {
	            conn.close();
	        }
	    } catch (SQLException e) {
	        System.out.println("error:" + e);
	    }
	}
	
	//select - 전체보기
	public List<PersonVo> select() {
		//리스트 준비
    	List<PersonVo> pList = new ArrayList<>();
    	getConnect();
    	
		try {
		    // 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += "SELECT person_id,";
			query += "       name,";
			query += "       hp,";
			query += "       company";
			query += " FROM person";
			
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
		    
		    // 4.결과처리
		    while(rs.next()) {
		    	int personId = rs.getInt("person_id");
		    	String name = rs.getString("name");
		    	String hp = rs.getString("hp");
		    	String company = rs.getString("company");
		    	
		    	//리스트에 추가
		    	PersonVo personVo = new PersonVo(personId, name, hp, company);
		    	pList.add(personVo);
		    }
	
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
		
		close();
		return pList;
	}
	
	//insert
	public int insert(String name, String hp, String company) {

		getConnect();
		int count = 0;
		
		try {
		    // 3. SQL문 준비 / 바인딩 / 실행
			String query = "insert into person values(seq_person_id.nextval, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, name);
			pstmt.setString(2, hp);
			pstmt.setString(3, company);
			
			count = pstmt.executeUpdate();
			
		    // 4.결과처리

		} catch(SQLException e) {
		    System.out.println("error:" + e);
		}
		
		close();
		return count;
	}
	
	//update
	public int update(int personId, String name, String hp, String company) {
		getConnect();
		int count =0;
		try {
		    // 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " update person";
			query += " set name = ?,";
			query += " 	   hp = ?,";
			query += " 	   company = ?";
			query += " WHERE person_id = ?";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(4, personId);
			pstmt.setString(1, name);
			pstmt.setString(2, hp);
			pstmt.setString(3, company);
			
			count = pstmt.executeUpdate();//쿼리문 실행
			
		    // 4.결과처리
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 
		close();
		return count;
	}
	
	//delete
	public int delete(int personId) {
		getConnect();
		int count = 0;
		
		try {
		    // 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " delete from person";
			query += " where person_id = ?";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, personId);
			count = pstmt.executeUpdate();
		    
	    // 4.결과처리
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
		close();
		return count;
	}
	
	
}
