package service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.ReportDao;
import jdbc.ConnectionProvider;
import model.Report;

public class ReportRegService {

		//싱글톤 처리
	
	private ReportRegService() {};
	private static ReportRegService service = new ReportRegService();
	
	public static ReportRegService getInstance() {
		
		return service;
	};
	
	
	ReportDao dao;
	
	public int regReport(HttpServletRequest request) { // request가져오는건 업로드.jsp에서 메소드 리퀘스트가져오기위해
		
		int resultCnt = 0;
		
		
		String sname= "";
		String sno = "";
		String filepath = "";
		
		
		Connection conn = null;
		
		
		try {
			
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		if(isMultipart){
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			/* 이름,학번,과제파일은 한번에묶을 List */
			List<FileItem> items;
			
				items = upload.parseRequest(request);
			
			Iterator<FileItem> ite = items.iterator();
			
			while(ite.hasNext()){
				
				FileItem item = ite.next();
				
				// isFormField() : text value를 가지는 input 확인
				if(item.isFormField()){ // type=file 이외의 input
				
					//파라미터 이름
					String paramName = item.getFieldName();
				
					// 파라미터 값
					String paramValue;
					
						paramValue = item.getString("utf-8");
					 
					
					//테스트 출력용
					//System.out.println(paramName+ " = " + paramValue);
					
					
					if(paramName.equals("sname")) {
						
						sname = paramValue;
					}else if(paramName.equals("sno")) {
						sno = paramValue;
					}
					
					
					
					
					
					
				}else{ // type = file 
						// 파라미터 이름
						String paramName = item.getFieldName();
				
						// 파일 이름 
						String fileName = item.getName();
						
						// 파일의 사이즈
						long file_size = item.getSize();
						
						// 파일의 타입
						String contentType = item.getContentType();
						
						// 임시 메모리에 저장 여부
						boolean isInMemory = item.isInMemory();
						
						System.out.println("파일 이름 : "+fileName);
						System.out.println("파일 사이즈 : "+file_size);
						System.out.println("파일 타입 : "+contentType);
						
						// 서버 내부의 경로
						//String uri = "/file";
						
						String uri = request.getSession().getServletContext().getInitParameter("uploadPath"); // web.xml 경로설정가능
						System.out.println(uri);
						//시스템의 실제(절대) 경로
						String realPath = request.getSession().getServletContext().getRealPath(uri);
						//System.out.println(realPath);
						
						String newFileName = System.nanoTime()+"_"+fileName;
						System.out.println(realPath);
						
						// 서버의 저장소에 실제 저장 
						File saveFile = new File(realPath,newFileName);
						
							item.write(saveFile);
						 
						System.out.println("저장 완료");
					
						
						filepath = uri +"/"+newFileName;
					
					
				}// else 끝
			}// while 끝 
			
			// 데이터베이스 저장
			
			Report report = new Report();
			report.setSname(sname);
			report.setSno(sno);
			report.setreport(filepath);
			
			conn = ConnectionProvider.getConnection();
			
			dao = ReportDao.getInstance();
			
			resultCnt =dao.insertReport(conn, report);
			request.setAttribute("report", report); // 원래이렇게안하는데 테스트용으로한다함
		};
		
		
	} catch (FileUploadException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
		finally {
			
			if(conn !=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		
	}
		
		
		
		return resultCnt;
		
	}
}
