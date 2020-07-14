package member.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import jdbc.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;
import service.Service;

public class MemberRegServiceImpl implements Service {

	MemberDao dao;
	
	
	@Override
	public String getViewPage(HttpServletRequest request, HttpServletResponse response) {
		
		
		// 파일 업로드( 사진 ) -> 특정위치에 저장
		// 사용자 데이터를 받기 ( uid, upw, uname ,uphoto)
		
		//입력 됐나 안됐나 확인용
		int resultCnt = 0;
		
		// 데이터 베이스에 입력할 데이터 변수
		String uid = null;
		String upw = null;
		String uname = null;
		String uphoto = null;
		
		
		
		
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
					
					
					if(paramName.equals("uid")) {
						
						uid = paramValue;
					}else if(paramName.equals("upw")) {
						upw = paramValue;
					}else if(paramName.equals("uname")) {
						uname = paramValue;
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
						String uri = "/upload/users";
						
						
						//String uri = request.getSession().getServletContext().getInitParameter("uploadPath"); // web.xml 경로설정가능
						//System.out.println(uri);
						//시스템의 실제(절대) 경로
						
						
						String realPath = request.getSession().getServletContext().getRealPath(uri);
						//System.out.println(realPath);
						String newFileName = System.nanoTime()+"_"+item.getName();
						
						//System.out.println(realPath);
						
						// 서버의 저장소에 실제 저장 
						File saveFile = new File(realPath,newFileName);
						
							item.write(saveFile);
						 
						System.out.println("저장 완료");
					
						
						uphoto = uri +"/"+newFileName; // 저장해야하는경로
					
					
				}// else 끝
			}// while 끝 
			
			// 데이터베이스 저장
			Member member = new Member();
			member.setUid(uid);
			member.setUpw(upw);
			member.setUname(uname);
			member.setUphoto(uphoto);
			
			conn = ConnectionProvider.getConnection(); // 예외발생가능함[null들어올수있음]
			
			dao = MemberDao.getInstance();
			
			resultCnt =dao.insertMember(conn, member); // 위에 예외발생하면 얘는 0이들어옴 
			request.setAttribute("member", member); // 원래이렇게안하는데 테스트용으로한다함
			request.setAttribute("result", resultCnt);
			
			
			
		};
		
		
	} catch (FileUploadException e) {
		e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}catch(SQLException e) {
		e.printStackTrace();
	}catch (Exception e) {
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
		
		
		
		
		return "/WEB-INF/views/member/reg.jsp";
	}

}
