package nl.cerios.cerioscoop.domain;

public class Room {
	private int roomId;
	private String name;
	private int chairAmount;
	private int roomType;
	
	public Room(){
	}
	
	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getChairAmount() {
		return chairAmount;
	}

	public void setChairAmount(int chairAmount) {
		this.chairAmount = chairAmount;
	}

	public int getRoomType() {
		return roomType;
	}

	public void setRoomType(int roomType) {
		this.roomType = roomType;
	}
}
