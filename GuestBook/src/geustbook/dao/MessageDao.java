package geustbook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import guestbook.model.Message;

public class MessageDao {

	
//	싱글톤처리 !!
	
	private MessageDao() {
		
	}
	
	static private MessageDao dao = new MessageDao();
	
	public static MessageDao getInstance() {
		return dao;
	}

	public int insertMessage(Connection conn, Message message) throws SQLException {
		
		int resultCnt = 0;
		
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "insert into guestbook_message values (message_id_seq.nextVal, ?,?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, message.getUname());
		pstmt.setString(2, message.getPw());
		pstmt.setString(3, message.getMessage());
		
		resultCnt = pstmt.executeUpdate();
		
		
		} finally {
			
//			pstmt 널포인터 없애기위해
			if(pstmt !=null) {
				pstmt.close();
			}
			
		
		
		
		
			
			
		}
		return resultCnt;
	}
	


public List<Message> selectMessageList(Connection conn, int startRow, int endRow) throws SQLException{
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	List<Message> list = new ArrayList();
	
	try {
		
		String sql = "select message_id, guest_name, passsword, message " + 
				"from( " + 
				"    select rownum rnum, message_id, guest_name, passsword, message " + 
				"    from (" + 
				"            select * from guestbook_message order by guestbook_message.message_id desc " + 
				"        ) where rownum <= ? " +  // endrow
				" ) where rnum >= ? "; // startrow
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, endRow);
		pstmt.setInt(2, startRow);
		
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			
			Message message = new Message(rs.getInt("message_id"), rs.getString("guest_name"), 
					rs.getString("passsword"), rs.getString("message"));
			list.add(message);
			
		}
		
	}finally {
		
		if(rs !=null) {
			rs.close();
		}
		
		if(pstmt != null) {
			pstmt.close();
		}
		
	}
	
	
	
	return list;
	
}

public int selectTotalCount(Connection conn) throws SQLException {
	
	
	int resultCnt = 0;
	
	Statement stmt = null; // 카운트 구하는것도 셀렉트니까 아래 ResultSet
	ResultSet rs = null;
	
	
	try {
		
		stmt = conn.createStatement();
		
		String sql = "select count(*) from guestbook_message"; // 카운트를구해오는 sql문 
		
		rs = stmt.executeQuery(sql); // 실행쿼리
		
		if(rs.next()) { // 정상적으로 행이 출력되나 if로한거임 원래는 while로 카운트 * 싹다
			resultCnt = rs.getInt(1);
		}
		
		
	} finally {
		if(rs !=null) {
			rs.close();
		}
		if(stmt !=null) {
			stmt.close();
		}
	}
	
	return resultCnt;
}

public Message selectMessage(Connection conn, int mid) throws SQLException {
	
	Message message = null;
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	try {
		
		String sql = "select * from guestbook_message where message_id=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, mid);
		
		rs = pstmt.executeQuery();
		
		if(rs.next()) {  
			message = new Message(rs.getInt("message_id"), rs.getString("guest_name"), 
					rs.getString("passsword"), rs.getString("message"));
				
		} 
		
		
	}finally {
		if(rs != null) {
			rs.close();
		}
		if(pstmt !=null) {
			pstmt.close();
		}
	}
	
	return message;
}

public int deleteMessage(Connection conn, int mid) throws SQLException {

	int resultCnt =0;
	
	PreparedStatement pstmt = null;
	
	
	String sql = "delete from guestbook_message where message_id=?";
	try {
	pstmt = conn.prepareStatement(sql);
	pstmt.setInt(1, mid);
	
	resultCnt = pstmt.executeUpdate();
	
	} finally {
		
		if(pstmt !=null) {
			pstmt.close();
		}
	}
	
	
	
	
	
	
	
	return resultCnt;
}





}