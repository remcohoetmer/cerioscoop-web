package nl.cerios.cerioscoop.service;

import org.junit.Assert;
import org.junit.Test;

import nl.cerios.cerioscoop.domain.Customer;
import nl.cerios.cerioscoop.domain.Employee;
import nl.cerios.cerioscoop.util.DateUtils;

public class AuthenticateUserTest {

	GeneralService generalService = new GeneralService();
	DateUtils dateUtils = new DateUtils();
	
	@Test
	public void test() {
		Employee testUser = null;
		Assert.assertEquals(false, generalService.authenticateUser(testUser));
		Customer testCustomer = new Customer();
		Assert.assertEquals(true, generalService.authenticateUser(testCustomer));
	}

}
