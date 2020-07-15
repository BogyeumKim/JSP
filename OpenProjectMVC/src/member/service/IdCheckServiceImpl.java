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

public class IdCheckServiceImpl implements Service {

	MemberDao dao;
	
	
	@Override
	public String getViewPage(HttpServletRequest request, HttpServletResponse response) {
		
		// boolean check = false; // 해도됨
		String result ="N";
		
		String id = request.getParameter("uid");
		int resultCnt =0;
		
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			dao = MemberDao.getInstance();
			
			
			resultCnt = dao.selectById(conn, id);
			
			if(resultCnt < 1) { // ==0 으로 해도 똑같음
				result="Y";
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("idcheck", result);
		
		
		System.out.println(id);
		System.out.println(result);
		System.out.println(resultCnt);
		
		
		return "/WEB-INF/views/member/idCheck.jsp";
	}

	
	
}
