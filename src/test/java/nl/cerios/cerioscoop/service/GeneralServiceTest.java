package nl.cerios.cerioscoop.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import nl.cerios.cerioscoop.domain.Customer;
import nl.cerios.cerioscoop.util.DateUtils;

public class GeneralServiceTest {

	@Test
	public void testAuthenticateCustomer() throws ParseException {
		final GeneralService generalService = new GeneralService();
		final DateUtils dateUtils = new DateUtils();
		
		//Customers
			final Customer customerOne = new Customer();
			customerOne.setFirstName("Ron");
			customerOne.setLastName("Sanders");
			customerOne.setUsername("RS");
			customerOne.setPassword("RS123");
			customerOne.setEmail("ron@sanders.com");
			customerOne.setCreateDate(dateUtils.convertUtilDateToSqlDate(dateUtils.toDate(dateUtils.toDateFormat("09-06-2016"))));
			customerOne.setCreateTime(dateUtils.convertUtilDateToSqlTime(dateUtils.toTime(dateUtils.toTimeFormat("20:00:00"))));
			
			final Customer customerTwo = new Customer();
			customerTwo.setFirstName("Marcel");
			customerTwo.setLastName("Groothuis");
			customerTwo.setUsername("MG");
			customerTwo.setPassword("MG123");
			customerTwo.setEmail("marcel@groothuis.com");
			customerTwo.setCreateDate(dateUtils.convertUtilDateToSqlDate(dateUtils.toDate(dateUtils.toDateFormat("09-06-2016"))));
			customerTwo.setCreateTime(dateUtils.convertUtilDateToSqlTime(dateUtils.toTime(dateUtils.toTimeFormat("20:00:00"))));
			
			final Customer customerThree = new Customer();
			customerThree.setFirstName("Rutger");
			customerThree.setLastName("van Velzen");
			customerThree.setUsername("RVV");
			customerThree.setPassword("RVV123");
			customerThree.setEmail("rutger@vanvelzen.com");
			customerThree.setCreateDate(dateUtils.convertUtilDateToSqlDate(dateUtils.toDate(dateUtils.toDateFormat("09-06-2016"))));
			customerThree.setCreateTime(dateUtils.convertUtilDateToSqlTime(dateUtils.toTime(dateUtils.toTimeFormat("20:00:00"))));
		
		//The no-customer test-user
			final Customer noCustomer = new Customer();
			noCustomer.setFirstName("Bob");
			noCustomer.setLastName("van Zeist");
			noCustomer.setUsername("BZ");
			noCustomer.setPassword("BZ123");
			noCustomer.setEmail("bob@vanzeist.com");
			noCustomer.setCreateDate(dateUtils.convertUtilDateToSqlDate(dateUtils.toDate(dateUtils.toDateFormat("09-06-2016"))));
			noCustomer.setCreateTime(dateUtils.convertUtilDateToSqlTime(dateUtils.toTime(dateUtils.toTimeFormat("20:00:00"))));
			
		//Putting all customers in a list
			final List<Customer> dbCustomers = new ArrayList<>();
			dbCustomers.add(0, customerOne);
			dbCustomers.add(1, customerTwo);
			dbCustomers.add(2, customerThree);
			
		
		//Authentication control 
			Assert.assertEquals(customerOne ,generalService.authenticateCustomer(customerOne, dbCustomers));
			Assert.assertEquals(customerTwo ,generalService.authenticateCustomer(customerTwo, dbCustomers));
			Assert.assertEquals(customerThree ,generalService.authenticateCustomer(customerThree, dbCustomers));
			Assert.assertNotEquals(noCustomer ,generalService.authenticateCustomer(noCustomer, dbCustomers));
	}
}
