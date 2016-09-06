package nl.cerios.cerioscoop.domain;

public class Transaction {
	private int transactionId;
	private int customerId;
	private int showId;
	private int roomId;
	private double totalPrice;
	private int reservedChairs;
	private int bankAccount;

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
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

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getReservedChairs() {
		return reservedChairs;
	}

	public void setReservedChairs(int reservedChairs) {
		this.reservedChairs = reservedChairs;
	}

	public int getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(int bankAccount) {
		this.bankAccount = bankAccount;
	}

}
