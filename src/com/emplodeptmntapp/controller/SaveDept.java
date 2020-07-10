package com.emplodeptmntapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.emplodeptmntapp.dao.DeptEmpDao;
import com.emplodeptmntapp.dao.DeptEmpDaoImpl;
import com.emplodeptmntapp.model.Department;
import com.emplodeptmntapp.service.DeptEmpServImpl;
import com.emplodeptmntapp.service.DeptEmpService;

/**
 * Servlet implementation class SaveDept
 */
@WebServlet("/savedept")
public class SaveDept extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String deptName=request.getParameter("deptName");
		String deptLoc=request.getParameter("deptLoc");
		Department dt = new Department();
		
		dt.setDeptId(0);
		dt.setDeptName(deptName);
		dt.setDeptLoc(deptLoc);
		DeptEmpService savDept = new DeptEmpServImpl();
		
		savDept.createDeptServ(dt);
		HttpSession sem = request.getSession();
		sem.setAttribute("submitDoneDept","done");
		response.sendRedirect("homeserv");
		
	}

}
