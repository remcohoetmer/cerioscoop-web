package nl.cerios.cerioscoop.domain;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;

import nl.cerios.cerioscoop.util.DateUtils;

public class EmployeeTest {

	@Test
	public void testInstantiateEmployee() throws ParseException {
		final Employee employee = new Employee();
		final DateUtils dateUtils = new DateUtils();
		
			employee.setEmployeeId(200);		
			employee.setFirstName("Rob");
			employee.setLastName("Bosman");
			employee.setUsername("RB&B");
			employee.setPassword("rob123");
			employee.setEmail("rob@bosman.com");
			employee.setCreateDate(dateUtils.convertUtilDateToSqlDate(dateUtils.toDate(dateUtils.toDateFormat("09-06-2016"))));
			employee.setCreateTime(dateUtils.convertUtilDateToSqlTime(dateUtils.toTime(dateUtils.toTimeFormat("20:00:00"))));
				
			Assert.assertNotNull(employee);
			Assert.assertEquals(200, employee.getEmployeeId());
			Assert.assertEquals("Rob", employee.getFirstName());
			Assert.assertEquals("Bosman", employee.getLastName());
			Assert.assertEquals("RB&B", employee.getUsername());
			Assert.assertEquals("rob123", employee.getPassword());
			Assert.assertEquals("rob@bosman.com", employee.getEmail());
			Assert.assertEquals("2016-09-06", employee.getCreateDate().toString());  //The actual object is in java.sql.Date!
			Assert.assertEquals("20:00:00", employee.getCreateTime().toString());
	}
}
