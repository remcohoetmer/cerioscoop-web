package nl.cerios.cerioscoop.service;

import org.junit.Assert;
import org.junit.Test;

import nl.cerios.cerioscoop.domain.Customer;
import nl.cerios.cerioscoop.domain.Employee;

public class AuthenticateUserTest {

	
	
	@Test
	public void test() {
		final GeneralService generalService = new GeneralService();
		Employee testUser = null;
		Assert.assertEquals(false, generalService.authenticateUser(testUser));
		Customer testCustomer = new Customer();
		Assert.assertEquals(true, generalService.authenticateUser(testCustomer));
	}

}
