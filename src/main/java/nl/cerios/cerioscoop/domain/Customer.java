package nl.cerios.cerioscoop.domain;

import java.sql.Date;
import java.sql.Time;

public class Customer extends User{
	private int customerId;
	
	public Customer(){
	}
	
	public Customer(int customerId, String firstName, String lastName, String username, String password,
			String email, Date createDate, Time createTime) {
		super(firstName, lastName, username, password, email, createDate, createTime);
		this.customerId = customerId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
}
