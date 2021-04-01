package com.java.addressbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.java.addressbook.vo.AddressBookVO;


public class AddressBookDaoImpl implements AddressBookDao {
	
	private Connection getConnection() throws SQLException{
		Connection conn = null;
		try {
			//드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dburl = "jdbc:oracle:thin:@192.168.123.110:1521:xe";
			conn = DriverManager.getConnection(dburl, "C##HYEJI", "1234");
		} catch(ClassNotFoundException e) {
			System.err.println("드라이버 로드 실패!");
		} 
		
		return conn;
	}

	@Override
	public List<AddressBookVO> getList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<AddressBookVO> list = new ArrayList<>();
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			String sql = "SELECT id,name,hp,tel FROM phone_book ORDER BY id";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				AddressBookVO vo = new AddressBookVO(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4));
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e ) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	@Override
	public List<AddressBookVO> search(String key) {
		List<AddressBookVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql = "SELECT id,name,hp,tel FROM phone_book WHERE name LIKE ? ORDER BY id";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+key+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				AddressBookVO vo = new AddressBookVO(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4));
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch(Exception e ) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	@Override
	public boolean insert(AddressBookVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO phone_book VALUES(seq_phone_book.NEXTVAL,?,?,?)";
		int insertedCount = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getHp());
			pstmt.setString(3, vo.getTel());
			
			insertedCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch(Exception e ) {
				e.printStackTrace();
			}
		}
		return insertedCount==1;
	}
	
}