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

/**
 * Servlet implementation class EditEmployee
 */
@WebServlet("/editemployee")
public class EditEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		int empId=Integer.parseInt(request.getParameter("empId"));
		System.out.println("employee id at edit employee is "+empId);
		int deptId = Integer.parseInt(request.getParameter("deptId"));
		DeptEmpService editEmpl = new DeptEmpServImpl();
		Employee emp = (Employee) editEmpl.readEmployeeServ(empId);
		Department df = editEmpl.showDeptServ(emp.getDeptEmpId());
		System.out.println("edit page value"+emp.getEmpName());
		HttpSession sed = request.getSession();
		List<Employee> listFromDept=editEmpl.readEmpFromDeptServ(deptId);
		sed.setAttribute("empp", emp);
		request.setAttribute("mainemps", "checktableedit");
		request.setAttribute("empl", emp);
		request.setAttribute("hom", "homep");
		request.setAttribute("addlin", "anemp");
		request.setAttribute("lis", sed.getAttribute("lisvaldept"));
		request.setAttribute("val", listFromDept);
		request.setAttribute("dfg", df);
		
			RequestDispatcher rd = request.getRequestDispatcher("home3.jsp");
			rd.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
