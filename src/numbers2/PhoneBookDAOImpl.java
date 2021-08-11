package numbers2;

import java.sql.*;
import java.util.*;

public class PhoneBookDAOImpl implements PhoneBookDAO {
	
	private Connection getConnection() throws SQLException {
	Connection conn = null;
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
		conn = DriverManager.getConnection(dburl, "C##BITUSER", "USER");
	} catch (ClassNotFoundException e) {
		System.err.println("드라이버 로드 실패!");
	}
	return conn;
}

@Override
public List<PhoneBookVO> getList() {
	List<PhoneBookVO> list = new ArrayList<>();
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	try {
		conn = getConnection();
		stmt = conn.createStatement();
		
		rs = stmt.executeQuery("SELECT name, hp, tel FROM phone_book");
		
		while (rs.next()) {
			String name = rs.getString(1);
			String hp = rs.getString(2);
			String tel = rs.getString(3);
			
			PhoneBookVO vo = new PhoneBookVO(name, hp, tel);
			
			list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			} finally {
				try {
					rs.close();
					stmt.close();
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
					}
				}
		return list;
		}

@Override
public boolean insert(PhoneBookVO vo) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	int insertedCount = 0;
	
	try {
		conn = getConnection();
		pstmt = conn.prepareStatement("INSERT INTO phone_book VALUES(seq_book_id.NEXTVAL, ?, ?, ?)");
		
		pstmt.setString(1, vo.getName());
		pstmt.setString(2, vo.getPhonenum());
		pstmt.setString(3, vo.getHomenum());
		
		insertedCount = pstmt.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	return 1 == insertedCount;
}

@Override
public boolean delete(Long id) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	int num = 0;
	int deletedCount = 0;
	
	try {
		conn = getConnection();
		
		pstmt = conn.prepareStatement("DELETE FROM phone_book WHERE id = ?");
		pstmt.setLong(1, id);
		
		deletedCount = pstmt.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	return 1 == deletedCount;
}

@Override
public List<PhoneBookVO> find(String keyword) {
	List<PhoneBookVO> list = new ArrayList<>();
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {
		conn = getConnection();
		pstmt = conn.prepareStatement("SELECT name, hp, tel FROM phone_book WHERE name LIKE ?");
		pstmt.setString(1, "%"+keyword+"%");
		
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			String name = rs.getString(1);
			String hp = rs.getString(2);
			String tel = rs.getString(3);
			
			PhoneBookVO vo = new PhoneBookVO(name, hp, tel);
			list.add(vo);
		}
	} catch (SQLException e) {
		e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	return list;
	}
}