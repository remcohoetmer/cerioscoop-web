package nl.cerios.cerioscoop.service;

import org.junit.Assert;
import org.junit.Test;

import nl.cerios.cerioscoop.domain.Customer;
import nl.cerios.cerioscoop.domain.Employee;
import nl.cerios.cerioscoop.util.DateUtils;

public class AuthenticateUserTest {

	final GeneralService generalService = new GeneralService();
	final DateUtils dateUtils = new DateUtils();
	
	@Test
	public void testAuthenticateUser() {
		
		Employee testUser = null;
		Assert.assertEquals(false, generalService.authenticateUser(testUser));
		Customer testCustomer = new Customer(1, "Marcel", "Groothuis", "Manollo7G", "secret", "mjg@cerios.nl", dateUtils.getCurrentSqlDate(), dateUtils.getCurrentSqlTime());
		Assert.assertEquals(true, generalService.authenticateUser(testCustomer));
	}

}
