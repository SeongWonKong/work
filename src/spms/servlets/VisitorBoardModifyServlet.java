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

@WebServlet("/modify")
public class VisitorBoardModifyServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		
		try{
			ServletContext sc = this.getServletContext();
						
			VisitorBoardDao visitorBoardDao = (VisitorBoardDao)sc.getAttribute("visitorBoardDao");
			
			request.setAttribute("board", visitorBoardDao.getBoard(request.getParameter("vno")));
			
			response.setContentType("text/html; charset=UTF-8");
			RequestDispatcher rd = request.getRequestDispatcher("/board/VisitorBoardModify.jsp");
			rd.include(request, response);
			

		}catch(Exception e){
			throw new ServletException(e);
		}
		
		

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		String pw1, pw2;
		try{
			
			ServletContext sc = this.getServletContext();		
			VisitorBoardDao visitorBoardDao = (VisitorBoardDao)sc.getAttribute("visitorBoardDao");
			
			pw1 = visitorBoardDao.getPW(request.getParameter("vno"));
			
			SecurityUtil securityUtil = new SecurityUtil();
			pw2 = securityUtil.encryptSHA256(request.getParameter("password"));			
			
			if(pw1.equals(pw2)){
				visitorBoardDao.updateBoard(request.getParameter("vno"),request.getParameter("content"));
				System.out.println("password correct!");
			}else{
				System.out.println("wrong password!");
			}
			
			response.sendRedirect("visitorboard");
			
		}catch(Exception e){
			throw new ServletException(e);
		}
	}
}
