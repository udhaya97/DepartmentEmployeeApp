package com.emplodeptmntapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
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

@WebServlet("/regDept")
public class RegDept extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public RegDept() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sed = request.getSession();
		DeptEmpService regDpt = new DeptEmpServImpl();
		List<Department> ldepty = regDpt.readAllDeptServ();
		request.setAttribute("adddept", "regdept");
		request.setAttribute("deptlv", ldepty);
		request.setAttribute("hoser", "hseval");
		RequestDispatcher rdf = request.getRequestDispatcher("home3.jsp");
		rdf.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
