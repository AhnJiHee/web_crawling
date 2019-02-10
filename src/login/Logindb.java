package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logindb extends HttpServlet {

       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청
		request.setCharacterEncoding("utf-8");
		String idval = request.getParameter("id");
		String pw = request.getParameter("pw");
		String result = "";
		
		HttpSession session = request.getSession();
		System.out.println(session.isNew());
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String html = "";
		
		/*HttpSession session = request.getSession();
		String idval = (String)session.getAttribute("sessionid");
		String pw = (String)session.getAttribute("sessionpw");*/
		//로그인 처리
		//member 테이블에 id와 pw를 조사.
//////////////////////////////////////////////////////////////////////////////
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr"); 
			
			System.out.println("데이터베이스 연결 성공");
			
			
			String sql = "select id from member where id = ?";
			
			
			PreparedStatement pt = con.prepareStatement(sql);
			
			pt.setString(1, idval);
			
			ResultSet rs = pt.executeQuery();
			
			String sql2 = "select password from member where password = ? and id = ?";
			PreparedStatement pt2 = con.prepareStatement(sql2);
			
			pt2.setString(1, pw);
			pt2.setString(2, idval);
			
			ResultSet rs2 = pt2.executeQuery();
			
			if(rs.next()==false) {
				result = ("<h1>회원이 아닙니다.</h1><br>" 
			+ "<h3><a href = 'loginform.jsp'>로그인</h3>");
			}
			else {
				
				if(rs2.next()==false) {
					result = "비밀번호를 확인하세요.";
				}
				else {
					result = "정상 로그인 입니다.";
					if(session.getAttribute("sessionid")!=null &&
							((String)session.getAttribute("sessionid")).equals(request.getParameter("id")) ) {
						html = "<h1>이미 로그인 하셨습니다.</h1>"
								+ "<h3><a href = 'boardlist.jsp'>게시판 이동</a></h3>";
						
					}
					else {
						session.setAttribute("sessionid", request.getParameter("id"));
						session.setAttribute("sessionpw", request.getParameter("pw"));
						html = "<h1>id를 세션에 저장했습니다.</h1>"
								+ "<h3><a href = 'boardlist.jsp'>게시판 이동</a></h3>";
					}
					
				}
				
			}	
			out.println(html);
			con.close();
			System.out.println("연결해제성공");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
//		HttpSession session = request.getSession();
//		System.out.println(session.isNew());
//		
//		response.setContentType("text/html;charset=utf-8");
//		PrintWriter out = response.getWriter();
//		String html = "";
//		
//		if(session.getAttribute("sessionid")!=null &&
//				((String)session.getAttribute("sessionid")).equals(request.getParameter("id")) ) {
//			html = "<h1>이미 로그인 하셨습니다.</h1>";
//		}
//		else {
//			session.setAttribute("sessionid", request.getParameter("id"));
//			html = "<h1>id를 세션에 저장했습니다.</h1>";
//		}
//		
//		out.println(html);
//		
//		out.println("<a href = 'mypagesession'> 마이페이지로 이동하기</a>");
//		out.println("<a href = 'logoutsession'> 로그아웃하기</a>");
//		out.println
//		("<a href = 'http://localhost:8081/servlettest/JSP/boardwrite_session.jsp'> 글 쓰러 이동하기</a>");
//		//2. 입력 id를 전송 받아서 session 내부에 저장
//		session.setAttribute("sessionid", request.getParameter("id"));
//		//sessionid 라는 이름으로 id를 session에 저장하겠다는 뜻
		
		
		
		//응답
		response.setContentType("text/html;charset = utf-8");
		
		out = response.getWriter();
		out.println(result);
		
	}

}
