package nl.cerios.cerioscoop.domain;

import java.sql.Date;
import java.sql.Time;

public class Customer {
	private int customerId;
	private String firstName;
	private String lastName;
	private String Username;
	private String Password;
	private String email;
	private Date customerCreateDate;
	private Time customerCreateTime;
	
	public Customer() {
	}

	public Customer(int customerId, String firstName, String lastName, String username, String password,
			String email, Date createDate, Time createTime) {
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.Username = username;
		this.Password = password;
		this.email = email;
		this.customerCreateDate = createDate;
		this.customerCreateTime = createTime;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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

	public Date getCustomerCreateDate() {
		return customerCreateDate;
	}

	public void setCustomerCreateDate(Date customerCreateDate) {
		this.customerCreateDate = customerCreateDate;
	}

	public Time getCustomerCreateTime() {
		return customerCreateTime;
	}

	public void setCustomerCreateTime(Time customerCreateTime) {
		this.customerCreateTime = customerCreateTime;
	}

}
