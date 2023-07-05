package voting.system.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import voting.system.database.VotingBin;
import voting.system.domain.Register;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Register reg = new Register();
		VotingBin vb = new VotingBin();
		int count = 0;
		
		reg.setUsername(request.getParameter("username"));
		reg.setPassword(request.getParameter("password"));
		try {
			count=vb.loginCheck(reg);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if(count==1){
			Cookie loginCookie = new Cookie("username",reg.getUsername());
			loginCookie.setMaxAge(30*60);
			response.addCookie(loginCookie);
			response.sendRedirect("index.jsp");
		}
		else{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			PrintWriter out = response.getWriter();
			out.println("<script>alert(\"Please check your Username or Password!\");</script>");
			rd.include(request, response);
		}
	}

}
