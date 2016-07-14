package nl.cerios.cerioscoop.domain;

import java.sql.Date;
import java.sql.Time;

public abstract class User {

	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String email;
	private Date createDate;
	private Time createTime;
	
	public User(){
	}
	public User(String firstName, String lastName, String username, String password,
			String email, Date createDate, Time createTime) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = username;
		this.password = password;
		this.email = email;
		this.createDate = createDate;
		this.createTime = createTime;
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
		return userName;
	}
	public void setUsername(String username) {
		userName = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Time getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Time createTime) {
		this.createTime = createTime;
	}
}
