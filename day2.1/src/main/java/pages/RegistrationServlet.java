package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.UserDaoImpl;
import pojos.User;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private UserDaoImpl userDao;
  
  public void init() throws ServletException {
		try {
			// create dao instance
			userDao = new UserDaoImpl();
		} catch (Exception e) {
			// re throw the exc to the caller (WC) , so that WC DOES NOT continue with the
			// rest of the servlet life cycle
			throw new ServletException("err in init of " + getClass(), e);
		}
	}
  
  public void destroy() {
		try {
			// clean up dao
			userDao.cleanUp();
		} catch (Exception e) {
			System.out.println("err in destroy of " + getClass() + " " + e);
		}
	}
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html");
    try(PrintWriter pw=response.getWriter()) {
    String firstName = request.getParameter("firstName");
    String lastName = request.getParameter("lastName");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String dob = request.getParameter("dob");
    
    Date newDate=Date.valueOf(dob);
    

    
    	boolean user = userDao.addUser(firstName,lastName,email,password,newDate);
    	if(user) {
    		response.sendRedirect("login.html");
    	}else {
//    		System.out.println("in user");
    		response.sendRedirect("registration.html");
    	}
      
    } catch (Exception e) {
		throw new ServletException("err in do- " + getClass(), e);
	}
  }
}
