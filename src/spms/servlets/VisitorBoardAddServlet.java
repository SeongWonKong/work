package spms.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import security.sha256.SecurityUtil;
import spms.dao.VisitorBoardDao;

@WebServlet("/add")
public class VisitorBoardAddServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		response.setContentType("text/html; charset=UTF-8");
		
		RequestDispatcher rd = request.getRequestDispatcher("/board/VisitorBoardAdd.jsp");
		rd.include(request, response);

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		
		try{
			EmailValidator ev = new EmailValidator(); //email 유효성 체크(서버)
			if(ev.validate(request.getParameter("email"))){
				ServletContext sc = this.getServletContext();
				SecurityUtil securityUtil = new SecurityUtil();
				String pswd = securityUtil.encryptSHA256(request.getParameter("password"));//비밀번호 암호화
	
				VisitorBoardDao visitorBoardDao = (VisitorBoardDao)sc.getAttribute("visitorBoardDao");
				
				visitorBoardDao.insertDB(request.getParameter("email"),pswd,request.getParameter("content"));
			}else{
				System.out.println("wrong email");
			}
			
			response.sendRedirect("visitorboard");
			
		}catch(Exception e){
			throw new ServletException(e);
		}
	}
}
