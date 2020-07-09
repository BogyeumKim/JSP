package guestbook.service;

import java.sql.Connection;
import java.sql.SQLException;

import geustbook.dao.MessageDao;
import guestbook.jdbc.ConnectionProvider;
import guestbook.model.Message;

public class WriteMessageService {

	private WriteMessageService() {}
	
	private static WriteMessageService service = new WriteMessageService();
	
	public static WriteMessageService getInstance() {
		return service;
	}
	
	
	
	
	MessageDao dao = null;
	
	public int writeMessage(Message message) {
		int result =0;
		dao = MessageDao.getInstance();
		
		// Dao의 메서드에 전달할 Connection
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			result = dao.insertMessage(conn, message); // -> result 이부분이 0 또는 1 
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace(); // SQL예외 이외의 예외들 처리하는거임
		}
		
		
		
		
		return result; // 0또는 1이 messageWrite로 
	}
}
