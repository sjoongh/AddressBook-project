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
	// 메소드에서 SQL을 넘겨줌, 조회할때만 사용, insert 같은 다른 dml사용시 오류 발생가능성
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
	String com = null;
	int deletedCount = 0;
	
	try {
		// 1. id시퀀스 값 자동 초기화 : delete로 값 삭제후 while문으로 count해서 전화번호부의 총 length구함, data 담음
		// 2. 시퀀스를 자동정렬 해주는 방법이 따로 없으므로 시퀀스 DROP후 재생성CREATE 코드를 입력(사실상 새로 만듬)
		// 3. INSERT로 전부 시퀀스에 넣어줌
		// 4. 삭제된 id값 제외 새로 생성
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
	// 맨앞은 ArrayList를 안쓰고 List만 써도 ArrayList 사용 가능
	List<PhoneBookVO> list = new ArrayList<>();
	// DB연결 객체
	Connection conn = null;
	// 객체가 생성될때 SQL문장을 넘겨줌, statement보다 우수
	PreparedStatement pstmt = null;
	// executeQuery로 명령하면 ResultSet이라는 객체로 돌려줌
	ResultSet rs = null;
	
	try {
		// 1. DB연결
		conn = getConnection();
		// SQL문장을 pstmt에 담음
		pstmt = conn.prepareStatement("SELECT name, hp, tel FROM phone_book WHERE name LIKE ?");
		pstmt.setString(1, "%"+keyword+"%");
		
		// ??????? 
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