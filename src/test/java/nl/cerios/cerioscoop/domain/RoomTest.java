package nl.cerios.cerioscoop.domain;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;

public class RoomTest {

	@Test
	public void testInstantiateRoom() throws ParseException {
		final Room room = new Room();
		
			room.setRoomId(1);		
			room.setName("Yellow room");
			room.setChairAmount(50);
			room.setRoomType(2);
				
			Assert.assertNotNull(room);
			Assert.assertEquals(1, room.getRoomId());
			Assert.assertEquals("Yellow room", room.getName());
			Assert.assertEquals(2, room.getRoomType());
			
	}

}
