package nl.cerios.cerioscoop.domain;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;

import nl.cerios.cerioscoop.util.DateUtils;

public class PaymentTest {

	@Test
	public void testInstantiatePayment() throws ParseException {
		final Transaction payment = new Transaction();
		final DateUtils dateUtils = new DateUtils();
		
			payment.setPaymentId(100);		
			payment.setCustomerId(10);
			payment.setShowId(8);
			payment.setRoomId(2);
			payment.setChairId(5);
			payment.setAmount(10.55);
			payment.setPaymentDate(dateUtils.convertUtilDateToSqlDate(dateUtils.toDate(dateUtils.toDateFormat("09-06-2016"))));
			payment.setPaymentTime(dateUtils.convertUtilDateToSqlTime(dateUtils.toTime(dateUtils.toTimeFormat("20:00:00"))));
				
			Assert.assertNotNull(payment);
			Assert.assertEquals(100, payment.getPaymentId());
			Assert.assertEquals(10, payment.getCustomerId());
			Assert.assertEquals(8, payment.getShowId());
			Assert.assertEquals(2, payment.getRoomId());
			Assert.assertEquals(5, payment.getChairId());
			Assert.assertEquals(10.55, payment.getAmount(), 00.01);
			Assert.assertEquals("2016-09-06", payment.getPaymentDate().toString());  //The actual object is in java.sql.Date!
			Assert.assertEquals("20:00:00", payment.getPaymentTime().toString());
	}

}
