package nl.cerios.cerioscoop.domain;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;

import nl.cerios.cerioscoop.util.DateUtils;

public class CustomerTest {

	@Test
	public void testInstantiateCustomer() throws ParseException {
		final Customer customer = new Customer();
		final DateUtils dateUtils = new DateUtils();
		
			customer.setCustomerId(100);		
			customer.setFirstName("Rob");
			customer.setLastName("Bosman");
			customer.setUsername("RB&B");
			customer.setPassword("rob123");
			customer.setEmail("rob@bosman.com");
			customer.setCreateDate(dateUtils.convertUtilDateToSqlDate(dateUtils.toDate(dateUtils.toDateFormat("09-06-2016"))));
			customer.setCreateTime(dateUtils.convertUtilDateToSqlTime(dateUtils.toTime(dateUtils.toTimeFormat("20:00:00"))));
				
			Assert.assertNotNull(customer);
			Assert.assertEquals(100, customer.getCustomerId());
			Assert.assertEquals("Rob", customer.getFirstName());
			Assert.assertEquals("Bosman", customer.getLastName());
			Assert.assertEquals("RB&B", customer.getUsername());
			Assert.assertEquals("rob123", customer.getPassword());
			Assert.assertEquals("rob@bosman.com", customer.getEmail());
			Assert.assertEquals("2016-09-06", customer.getCreateDate().toString());  //The actual object is in java.sql.Date!
			Assert.assertEquals("20:00:00", customer.getCreateTime().toString());
	}
}


