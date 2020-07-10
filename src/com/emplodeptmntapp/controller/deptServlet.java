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
import com.emplodeptmntapp.model.Employee;
import com.emplodeptmntapp.service.DeptEmpServImpl;
import com.emplodeptmntapp.service.DeptEmpService;

@WebServlet("/home")
public class deptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public deptServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DeptEmpService findDept = new DeptEmpServImpl();
		List<Department> ldept = findDept.readAllDeptServ();
		int deptId =ldept.get(0).getDeptId();		
		response.sendRedirect("listEmp?deptId="+deptId);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
