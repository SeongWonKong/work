package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;


@WebServlet("/visitorboard")
public class VisitorBoardServlet extends GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/studydb",
					"study",
					"study");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"select VNO,CONTENT,EMAIL,DATE" +
					" from VISITOR_BOARD" +
					" order by VNO ASC");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>글목록</title></head>");
			out.println("<body><h1>글목록</h1>");
			out.println("<p><a href='add'>새로운 글 쓰기</a></p>");
			while(rs.next()){
				out.println(
						rs.getInt("VNO") + "," +
						rs.getString("CONTENT") + "," +
						rs.getString("EMAIL") + "," +
						rs.getDate("DATE")+"<br>");
			}
			out.println("</body></html>");
		}
		catch(Exception e){
			throw new ServletException(e);
		}finally{
			try{if(rs!=null) rs.close();} catch(Exception e){}
			try{if(stmt!=null) stmt.close();} catch(Exception e){}
			try{if(conn!=null) conn.close();} catch(Exception e){}
		}
	}

}
