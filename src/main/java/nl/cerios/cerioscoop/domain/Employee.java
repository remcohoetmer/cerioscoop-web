package nl.cerios.cerioscoop.domain;

import java.sql.Date;
import java.sql.Time;

public class Employee extends User{
	private int employeeId;
	
	public Employee() {
	}

	public Employee(int employeeId, String firstName, String lastName, String username, String password,
			String email, Date createDate, Time createTime) {
		super(firstName, lastName, username, password, email, createDate, createTime);
		this.employeeId = employeeId;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
}
