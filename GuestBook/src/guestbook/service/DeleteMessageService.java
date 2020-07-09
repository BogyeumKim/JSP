package guestbook.service;

import java.sql.Connection;
import java.sql.SQLException;

import geustbook.dao.MessageDao;
import guestbook.jdbc.ConnectionProvider;
import guestbook.model.Message;

public class DeleteMessageService {

	//싱글톤 처리
	
	private DeleteMessageService() {}
	private static DeleteMessageService service = new DeleteMessageService();
	
	public static DeleteMessageService getInstance() {
		return service;
	}
	
	
	// DAO 처리
	
	MessageDao dao;
	
	public int deleteMessage(int mid, String pw) { // 비밀번호확인후 삭제시 1보내는 처리
		int resultCnt =0;
		
		Connection conn = null;
		Message message = null;
		
		// 1. mid 의 메세지 존재 하는지 확인
		// 2. 메세지가 존재 하면 MID 객체의 PW와 사용자가 입력한 PW 비교
		// 3. 비교의 결과가 같다면 MID의 메세지를 삭제
		
		try {
			conn = ConnectionProvider.getConnection();
			dao = MessageDao.getInstance();
			
			message = dao.selectMessage(conn, mid);
			
			//예외처리시켜서 강제로 발생
			if(message == null) {
				resultCnt = -1;
				throw new Exception("삭제 할 메세지가 존재하지 않습니다.");
			}
			
			if(!message.getPw().equals(pw)) {// 사용자 pw와 같지않을때 비교해야함
				
				resultCnt = -2;
				throw new Exception("비밀번호가 일치하지 않습니다");
			}
			
			
			//위에 if문에 안걸리면 삭제처리 시작
			resultCnt= dao.deleteMessage(conn,mid);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
			
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
			}
		}
		
		return resultCnt; // 기대하는 결과값은 1 만약 위에 처리가안된다면 -1 또는 -2로 리턴될거임
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
