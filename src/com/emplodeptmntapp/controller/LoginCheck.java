package com.emplodeptmntapp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/usercheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String uname=request.getParameter("userName");
		String pass = request.getParameter("password");
		
		if(uname.equalsIgnoreCase("admin") && pass.equalsIgnoreCase("admin"))
		{
			
			HttpSession sess = request.getSession();
			sess.setAttribute("unam", "admin");
			
			response.sendRedirect("homeserv");
		}else
		{
			String message = "UserName and Password didnt match";
			request.getSession().setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			
		}
		
	}

}
