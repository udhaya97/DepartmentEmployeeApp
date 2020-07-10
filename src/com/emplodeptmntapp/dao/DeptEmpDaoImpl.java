package com.emplodeptmntapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.emplodeptmntapp.model.Department;
import com.emplodeptmntapp.model.Employee;


public class DeptEmpDaoImpl implements DeptEmpDao{

	
	static Connection con;
	//connection with mysql
	static void getConnectionMySql()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		}  
		try {
			con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/deptstudapp","root","root");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}  
	}
	
	public static boolean tableExist(Connection conn, String tableName) throws SQLException {
	    boolean tExists = false;
	    try (ResultSet rs = conn.getMetaData().getTables(null, null, tableName, null)) {
	        while (rs.next()) { 
	            String tName = rs.getString("TABLE_NAME");
	            if (tName != null && tName.equals(tableName)) {
	                tExists = true;
	                break;}
	                	
	                	
	                	
	            
	        }
	    }
	    return tExists;
	}
	public boolean createDept(Department dept) {
		getConnectionMySql();
		try {
			if(tableExist(con, "department"))
			System.out.println("table found");
			String query ="insert into department values (?,?,?)";
			try {
				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1, dept.getDeptId());
				ps.setString(2, dept.getDeptName());
				ps.setString(3, dept.getDeptLoc());
				ps.executeUpdate();
				System.out.println("Department created");
				//con.close();
			} catch (SQLException e) {
				System.err.println("Duplicate primary key ! change dept_id ");
			}
		} catch (SQLException e1) {
			 
			String cre ="create table department (dept_id int primary key auto_increment,dept_name varchar(20),dept_loc varchar(20))";
        	Statement smt;
			try {
				smt = con.createStatement();
				smt.executeUpdate(cre);
				
				String query ="insert into department values (?,?,?)";
				try {
					PreparedStatement ps = con.prepareStatement(query);
					ps.setInt(1, dept.getDeptId());
					ps.setString(2, dept.getDeptName());
					ps.setString(3, dept.getDeptLoc());
					ps.executeUpdate();
					System.out.println("Department created");
					
				} catch (SQLException e) {
					System.err.println("Duplicate primary key ! change dept_id ");
				}
				//con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	System.out.println("New Table Created");
			//System.out.println("table not found");
		}
		
		

	return true;
	}

	public boolean updateDept(Department dpt) {
		getConnectionMySql();
		
		
		String query ="update department set dept_name=?,dept_loc=? where dept_id=?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1, dpt.getDeptName());
			ps.setString(2, dpt.getDeptLoc());
			ps.setInt(3, dpt.getDeptId());
			ps.executeUpdate();
			System.out.println("Department Updated");
			//con.close();
		} catch (SQLException e) {
		
			System.err.println("data not found");
			
		}

		return true;}

	public List<Department> readAllDept() {
		
		
		getConnectionMySql();
		List<Department> ldept =new ArrayList<>();
		String query ="select * from department";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+" "+rs.getString(2));
				int id =rs.getInt(1);
				String name = rs.getString(2);
				String loc =rs.getString(3);
				Department dtd = new Department(id,name,loc);
				ldept.add(dtd);
				
			}
			System.out.println("All Data From Department");
			//con.close();
			
		} catch (SQLException e) {
			System.err.println("Data may not present");		}

		return ldept;}

	public boolean delteDept(int deptId) {
		
		getConnectionMySql();
		String query ="delete from department where dept_id=?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, deptId);
			ps.executeUpdate();
			
			System.out.println("Data deleted from department");
			//con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("data may not present");
		}
		return true;}

	public boolean createEmp(Employee employee) {
		
		getConnectionMySql();
		
		try {
			if(tableExist(con, "employee"))
			{
				String query ="insert into employee values (?,?,?,?,?,?,?,?)";
				try {
					PreparedStatement ps = con.prepareStatement(query);
					ps.setInt(1, employee.getEmpId());
					ps.setString(2, employee.getEmpName());
					ps.setString(3, employee.getMailId());
					ps.setString(4, employee.getDateOfBirth());
					ps.setInt(5, employee.getDeptEmpId());
					ps.setLong(6, employee.getMobileNo());
					ps.setFloat(7, employee.getSalary());
					ps.setString(8, employee.getCompanyName());
					ps.executeUpdate();
					System.out.println("Employee created");
					//con.close();
				} catch (SQLException e) {
					System.err.println("dept_id may not be available in data base ");
				}
			}
		} catch (SQLException e1) {
			String cre ="create table employee (emp_id int primary key auto_increment,emp_name varchar(20),mail_id varchar(20),dob varchar(20),"
					+ "dept_emp_fk int,mobile_no bigint,salary float,company_name varchar(20),foreign key(dept_emp_fk) references department(dept_id) on delete cascade)";
        	Statement smt;
			try {
				smt = con.createStatement();
				smt.executeUpdate(cre);
				//con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return true;}

	public boolean updateEmp(Employee employee) {
		getConnectionMySql();
		String query ="update employee set emp_name=?,mail_id=?,dob=?,mobile_no=?,salary=?,company_name=?,dept_emp_fk=? where emp_id=?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, employee.getEmpName());
			ps.setString(2, employee.getMailId());
			ps.setString(3, employee.getDateOfBirth());
			ps.setLong(4, employee.getMobileNo());
			ps.setFloat(5, employee.getSalary());
			ps.setString(6, employee.getCompanyName());
			ps.setInt(7, employee.getDeptEmpId());
			ps.setInt(8, employee.getEmpId());
			ps.executeUpdate();
			//con.close();
			System.out.println("Employee table updated");
			
		} catch (SQLException e) {
		
			
			System.err.println("Data not present");
		}

		return true;}

	public List<Employee> readEmpFromDept(int deptId) {
		getConnectionMySql();
		
		List<Employee> lemp = new ArrayList<>();
		String query ="select * from employee where dept_emp_fk=?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, deptId);
			ResultSet rs = ps.executeQuery();
			
				
					
						
							while(rs.next())
							{
								if(rs.getInt(1)!=0){
						System.out.println("values from db"+rs.getInt(1)+" "+rs.getString(2));
						int empId = rs.getInt(1);
						String empName= rs.getString(2);
						String mailId=rs.getString(3);
						String dob =rs.getString(4);
						int empDeptId=rs.getInt(5);
						long mob=rs.getLong(6);
						float sal = rs.getFloat(7);
						String company = rs.getString(8);
			
						Employee emp = new Employee(empId,empName,mailId,dob,empDeptId,mob,sal,company);
						
						lemp.add(emp);
						
						//con.close();		
						}
								else
								{
									System.out.println("Data not available in database");
								}
						}
					
				
				
			
			
		} catch (SQLException e) {
			System.err.println("data not available");
		}
	return lemp;}

	public boolean deleteEmpFromDept(int deptId,int empId) {
		getConnectionMySql();
		String query ="delete from employee where dept_emp_fk=? and emp_id=?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, deptId);
			ps.setInt(2, empId);
			ps.executeUpdate();
			System.out.println("Employee Deleted");
			//con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("No value found");
		}

	return true;}
	@Override
	public Employee readEmployee(int empId) {
		
		getConnectionMySql();
		Employee emp = new Employee();
		String query ="select * from employee where emp_id=?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, empId);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				emp.setEmpId(rs.getInt(1));
				emp.setEmpName(rs.getString(2));
				emp.setMailId(rs.getString(3));
				emp.setDateOfBirth(rs.getString(4));
				emp.setDeptEmpId(rs.getInt(5));
				emp.setMobileNo(rs.getLong(6));
				emp.setSalary(rs.getFloat(7));
				emp.setCompanyName(rs.getString(8));
			}
			//con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("No value found");
		}

	
		
		return emp;
	}
	@Override
	public Department showDept(int id) {
		getConnectionMySql();
		Department dept = new Department();
		String query ="select * from department where dept_id=?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				dept.setDeptId(rs.getInt(1));
				dept.setDeptName(rs.getString(2));
				dept.setDeptLoc(rs.getString(3));
			}//con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("No value found");
		}

	
		
		
		return dept;
	}
	
	
	

}
