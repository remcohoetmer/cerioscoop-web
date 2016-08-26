package nl.cerios.cerioscoop.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.cerios.cerioscoop.domain.Room;
import nl.cerios.cerioscoop.service.EmployeeService;

/**
 * Servlet implementation class AddRoomServlet
 */
@WebServlet("/AddRoomServlet")
public class AddRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private EmployeeService employeeService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Room room = new Room();
		
		if ("Submit".equals(request.getParameter("submitit"))) {
			room.setName(request.getParameter("name"));
			room.setChairAmount(Integer.parseInt(request.getParameter("chair_amount")));
			room.setRoomType(Integer.parseInt(request.getParameter("room_type")));
			
			
			employeeService.addRoom(room);
		
		}
		request.getRequestDispatcher("/jsp/employee/add-room.jsp").
        forward(request,response);
	}
	
}