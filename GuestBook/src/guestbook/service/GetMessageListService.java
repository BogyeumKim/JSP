package guestbook.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import geustbook.dao.MessageDao;
import guestbook.jdbc.ConnectionProvider;
import guestbook.model.Message;
import guestbook.model.MessageListView;

public class GetMessageListService {

	//싱글톤처리필요
	
	private GetMessageListService() {}
	
	private static GetMessageListService service = new GetMessageListService();
	
	public static GetMessageListService getInstance() {
		return service;
	}
	
	// DAO 처리 필요
	
	private MessageDao dao;
	
	// 한 페이지에 표현할 메시지의 개수
	private final int MESSAGE_COUNT_PER_PAGE = 3;
	
	public MessageListView getMesaageList (int pageNumber) {
	
		//페이지 번호 -> 시작 행, 끝 행
		// dao -> List 요청
		
		Connection conn=null;
		
		
		MessageListView messageListView = null;
		
		
		try {
			conn = ConnectionProvider.getConnection();
			dao = MessageDao.getInstance();
			
			// 페이지의 전체 메시지 구하기
			List<Message> messageList = null; 
			
			// 전체 메시지의 개수 구하기
			int messageTotalCount = dao.selectTotalCount(conn); // 전체 메시지개수는 db에 있는걸요청해야하니까 dao에서 처리 
			
			int startRow = 0;
			int endRow = 0;
			
			if(messageTotalCount>0) { // 게시물의개수가 0보다크면 dao 요청처리
				
				// 시작 행, 마지막 행
				startRow =(pageNumber-1)*MESSAGE_COUNT_PER_PAGE +1;
				endRow = startRow + MESSAGE_COUNT_PER_PAGE -1;
				
				messageList = dao.selectMessageList(conn, startRow, endRow);
				
			}else {
				pageNumber = 0;
				messageList = Collections.emptyList(); //  43줄 null을 new해서 바로생성해도됨 여기서 똑같이 생성하는거임
			}
			
			
			messageListView = new MessageListView(messageTotalCount, pageNumber, messageList, MESSAGE_COUNT_PER_PAGE, startRow, endRow);
			
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch ( Exception e) {
			e.printStackTrace(); // 에러시에 메시지 찾아야하니까 항상 필수
		} finally {
			
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return messageListView;
	}
	
}
