package com.emplodeptmntapp.test;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.emplodeptmntapp.model.Department;
import com.emplodeptmntapp.model.Employee;
import com.emplodeptmntapp.service.DeptEmpServImpl;
import com.emplodeptmntapp.service.DeptEmpService;




@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmpDeptTest {
	
	
	Department dep = new Department(1,"training","pune");
 	Department dep1 = new Department(2,"development","palakkad");
 	Department dep2 = new Department(3,"support","chhennai");
 	Employee emp = new Employee(23,"manoj","mano@gmail.com","1993-02-26",1,894315697,46953.56f,"tcs");
 	Employee emp1 = new Employee(25,"rakesh","rake@gmail.com","1991-03-26",1,894315697,32953.56f,"cts");
 	Employee emp2 = new Employee(26,"kiraan","ksh@gmail.com","1996-02-26",1,894315697,26953.56f,"ibm");
     Employee emp4 = new Employee (29,"Kumar","kum@gmail.com","1990-08-26",1,894315697,36953.56f,"infomatica");
   DeptEmpService empd = new DeptEmpServImpl();
    Connection con;
    //connection with mysql
   @Test
   public  void a_connectionCheck()
   {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		
			System.err.println("Driver Error");
		}  
		try {
			con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/deptstudapp","root","root");
			System.out.println("Connection successfull");
			assertNotNull("Connection success full", con);
		} catch (SQLException e) {
			
			System.err.println("Connection invalid");
		}  
   }
   //create department
   @Test
   public void b_createDept()
   {
	  a_connectionCheck();
	 
	  
	  assertNotNull("Department Created",empd.createDeptServ(dep));
	  assertNotNull("Department Created",empd.createDeptServ(dep2));
	  assertNotNull("Department Created",empd.createDeptServ(dep1));
	  try {
		con.close();
		System.out.println("Connection closed");
	} catch (SQLException e) {
		
		System.err.println("Connection issue");
	}
   }
   //create employee
   @Test
   public void c_createEmployee()
   {
	   a_connectionCheck();
	  assertNotNull("Creating employee", empd.createEmpServ(emp));
	  assertNotNull("Creating employee", empd.createEmpServ(emp1));
	  assertNotNull("Creating employee", empd.createEmpServ(emp2));
	  assertNotNull("Creating employee", empd.createEmpServ(emp));
	  assertNotNull("Creating employee", empd.createEmpServ(emp1));
	  assertNotNull("Creating employee",empd.createEmpServ(emp2));
	   
	  try {
			con.close();
			System.out.println("Connection closed");
		} catch (SQLException e) {
			
			System.err.println("Connection issue");
		}
   }
   //Get All Department Values
   @Test
   public void d_readDept()
   {
	   a_connectionCheck();
	  assertNotNull("Showing Department values" ,empd.readAllDeptServ());
	  try {
			con.close();
			System.out.println("Connection closed");
		} catch (SQLException e) {
			
			System.err.println("Connection issue");
		}
   }
   //update employee in department
   @Test
   public void e_updateEmp()
   {
	   a_connectionCheck();
	   
	   assertNotNull("Employee Updated", empd.updateEmpServ(emp4));
	   try {
			con.close();
			System.out.println("Connection closed");
		} catch (SQLException e) {
			
			System.err.println("Connection issue");
		}
   }
   //update department
   @Test
   public void f_updateDept()
   {
	   a_connectionCheck();
	  
	   assertNotNull("Department Updated", empd.updateDeptServ(dep1));
	   try {
			con.close();
			System.out.println("Connection closed");
		} catch (SQLException e) {
			
			System.err.println("Connection issue");
		}
	  
   }
   
  
   //get employees from department
   @Test
   public void g_readEmp()
   {
	   a_connectionCheck();
	   assertNotNull("Showing Employee",empd.readEmpFromDeptServ(dep2.getDeptId()));
	   try {
			con.close();
			System.out.println("Connection closed");
		} catch (SQLException e) {
			
			System.err.println("Connection issue");
		}
   }
   //delete employee in department
   @Test
   public void h_deleteEmp()
   {
	   a_connectionCheck();
	   assertNotNull("deleted Employee",empd.deleteEmpFromDeptServ(dep.getDeptId(), emp.getEmpId()));
	   try {
			con.close();
			System.out.println("Connection closed");
		} catch (SQLException e) {
			
			System.err.println("Connection issue");
		}
   }
   //delete department
   @Test
   public void i_deleteDept()
   {
	   a_connectionCheck();
	   assertNotNull("deleting Department",empd.delteDeptServ(dep2.getDeptId()));
	   try {
			con.close();
			System.out.println("Connection closed");
		} catch (SQLException e) {
			
			System.err.println("Connection issue");
		}
	  
   }
   
   @Test
   public void j_readEmp()
   {
	   a_connectionCheck();
	   assertNotNull("reading employee value Department",empd.readEmployeeServ(1));
	   try {
			con.close();
			System.out.println("Connection closed");
		} catch (SQLException e) {
			
			System.err.println("Connection issue");
		}
	  
   }
   
   @Test
   public void k_readDept()
   {
	   a_connectionCheck();
	   assertNotNull("reading department value Department",empd.showDeptServ(3));
	   try {
			con.close();
			System.out.println("Connection closed");
		} catch (SQLException e) {
			
			System.err.println("Connection issue");
		}
	  
   }

}
