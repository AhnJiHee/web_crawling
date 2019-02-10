package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginSession extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. HttpSession 생성
		//브라우저를 열고 최초로 요청했을 때 - httpsession 객체 생성
		//브라우저를 연 상태에서 최초가 아닌 요청을 했을 때 - 이미 생성된 httpsession을 리턴 받는 것
		HttpSession session = request.getSession();
		System.out.println(session.isNew());
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String html = "";
		
		if(session.getAttribute("sessionid")!=null &&
				((String)session.getAttribute("sessionid")).equals(request.getParameter("id")) ) {
			html = "<h1>이미 로그인 하셨습니다.</h1>";
		}
		else {
			session.setAttribute("sessionid", request.getParameter("id"));
			session.setAttribute("sessionpw", request.getParameter("pw"));
			html = "<h1>id를 세션에 저장했습니다.</h1>";
		}
		
		out.println(html);
		
		out.println("<a href = 'logoutsession'> 로그아웃하기</a>");
		out.println
		("<a href = '/boardproject2/logindb'> 디비 확인</a>");
		/*//2. 입력 id를 전송 받아서 session 내부에 저장
		session.setAttribute("sessionid", request.getParameter("id"));
		//sessionid 라는 이름으로 id를 session에 저장하겠다는 뜻
*/		
	}

}
