package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Report;

public class ReportDao {

	// 싱글톤처리
	
	private ReportDao() {};
	private static ReportDao dao = new ReportDao();
	
	public static ReportDao getInstance() {
		return dao;
	}
	
	
	public int insertReport(Connection conn, Report report) throws SQLException {
		
		int resultCnt = 0;
		
		PreparedStatement pstmt = null;
		//넣기만할거니까 프레페어스테이트먼트면충분
		
		String sql = "insert into report values (REPORT_SEQ.nextval, ?,?,?)"; // DB 시퀀스생성해야함 
	
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, report.getSname());
			pstmt.setString(2, report.getSno());
			pstmt.setString(3, report.getreport());
			
			resultCnt = pstmt.executeUpdate();
			
			
		}finally {
			
			if(pstmt !=null) {
				
				pstmt.close();
			}
			
			
		}
		
		
		
		
		return resultCnt;
		
	}
	
	
	
	
	
	
	
	
}
