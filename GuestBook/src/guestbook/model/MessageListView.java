package guestbook.model;

import java.util.List;

public class MessageListView {
	
	
	//전체 게시물의 개수
	private int messageTotalCount;
	
	//현재 페이지 번호
	private int currentPageNumber;
	
	//메시지 리스트
	private List<Message> messageList;
	
	// 전체 페이지의 개수
	private int pageTotalCount;
	
	// 페이지 당 표현 게시물의 개수
	private int messageCountPerpage;
	
	// 게시물의 시작 행 [ StartRow ]
	private int startRow;
	
	// 게시물의 마지막 행
	private int endRow;

	public MessageListView(int messageTotalCount, int currentPageNumber, List<Message> messageList,
			int messageCountPerpage, int startRow, int endRow) {

		this.messageTotalCount = messageTotalCount;
		this.currentPageNumber = currentPageNumber;
		this.messageList = messageList;
		this.messageCountPerpage = messageCountPerpage;
		this.startRow = startRow;
		this.endRow = endRow;
		calTotalpageCount();
		
	}
	
	// new MessageListView(); -> 인스턴스생성부터 [ 변수생성,메소드생성 후 생성자 호출 ]
	
	// 페이지 토탈카운트 따로 처리
	private void calTotalpageCount() {
		
		if(messageTotalCount ==0) {
			pageTotalCount=0;
		}else {
			pageTotalCount = messageTotalCount/messageCountPerpage;
			if(messageTotalCount%messageCountPerpage>0) {
				pageTotalCount++;
				
			}
		}
		
		
	}

	public int getMessageTotalCount() {
		return messageTotalCount;
	}

	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	public List<Message> getMessageList() {
		return messageList;
	}

	public int getPageTotalCount() {
		return pageTotalCount;
	}

	public int getMessageCountPerpage() {
		return messageCountPerpage;
	}

	public int getStartRow() {
		return startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	@Override // toString 테스트용
	public String toString() {
		return "MessageListView [messageTotalCount=" + messageTotalCount + ", currentPageNumber=" + currentPageNumber
				+ ", messageList=" + messageList + ", pageTotalCount=" + pageTotalCount + ", messageCountPerpage="
				+ messageCountPerpage + ", startRow=" + startRow + ", endRow=" + endRow + "]";
	}
	
	
	public boolean isEmpty() {
		return messageTotalCount==0;
	}
	
	
	

}
