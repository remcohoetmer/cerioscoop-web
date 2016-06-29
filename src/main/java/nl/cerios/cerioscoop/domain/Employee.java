package nl.cerios.cerioscoop.domain;

import java.sql.Date;
import java.sql.Time;

public class Employee {
	private int employeeId;
	private String firstName;
	private String lastName;
	private String Username;
	private String Password;
	private String email;
	private Date employeeCreateDate;
	private Time employeeCreateTime;
	
	public Employee() {
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getEmployeeCreateDate() {
		return employeeCreateDate;
	}

	public void setEmployeeCreateDate(Date employeeCreateDate) {
		this.employeeCreateDate = employeeCreateDate;
	}

	public Time getEmployeeCreateTime() {
		return employeeCreateTime;
	}

	public void setEmployeeCreateTime(Time employeeCreateTime) {
		this.employeeCreateTime = employeeCreateTime;
	}

}
