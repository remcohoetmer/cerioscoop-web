package nl.cerios.cerioscoop.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.cerios.cerioscoop.domain.Employee;
import nl.cerios.cerioscoop.service.EmployeeService;
import nl.cerios.cerioscoop.util.DateUtils;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final DateUtils dateUtils = new DateUtils(); 
	
	@EJB
	private EmployeeService employeeService;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Employee employee = new Employee();
				
		if ("Submit".equals(request.getParameter("submitit"))) {		
			employee.setFirstName(request.getParameter("firstname"));
			employee.setLastName(request.getParameter("lastname"));
			employee.setUsername(request.getParameter("username"));
			employee.setPassword(request.getParameter("password"));
			employee.setEmail(request.getParameter("email"));
			employee.setEmployeeCreateDate(dateUtils.getCurrentSqlDate());
			employee.setEmployeeCreateTime(dateUtils.getCurrentSqlTime());
			employeeService.newEmployee(employee);
		}
		request.getRequestDispatcher("/jsp/new-employee.jsp").
        forward(request,response);
	}

}
