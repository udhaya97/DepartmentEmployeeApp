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

/**
 * Servlet implementation class EditDepartment
 */
@WebServlet("/editdepartment")
public class EditDepartment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditDepartment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int deptId= Integer.parseInt(request.getParameter("depId"));
		HttpSession cvb=request.getSession();
		DeptEmpService editDeptmnt = new DeptEmpServImpl();
		List<Department> allDept = editDeptmnt.readAllDeptServ();
		Department dt =editDeptmnt.showDeptServ(deptId);
		
		//request.setAttribute("deptvalid", "editdept");
		request.setAttribute("deptva",dt);
		request.setAttribute("hoser", "hseval");
		request.setAttribute("deptlv", allDept);
		cvb.setAttribute("sdt", deptId);
		RequestDispatcher rf = request.getRequestDispatcher("home3.jsp");
		rf.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
