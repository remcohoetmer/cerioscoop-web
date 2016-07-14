package nl.cerios.cerioscoop.domain;

import java.sql.Date;
import java.sql.Time;

public class Payment {
	private int paymentId;
	private int customerId;
	private int showId;
	private int roomId;
	private int chairId;
	private double amount;
	private Date paymentDate;
	private Time paymentTime;

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getShowId() {
		return showId;
	}

	public void setShowId(int showId) {
		this.showId = showId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getChairId() {
		return chairId;
	}

	public void setChairId(int chairId) {
		this.chairId = chairId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Time getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Time paymentTime) {
		this.paymentTime = paymentTime;
	}

}
