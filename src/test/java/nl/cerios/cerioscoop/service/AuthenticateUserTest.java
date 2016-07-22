package nl.cerios.cerioscoop.service;

import org.junit.Assert;
import org.junit.Test;

import nl.cerios.cerioscoop.domain.Customer;
import nl.cerios.cerioscoop.domain.Employee;
import nl.cerios.cerioscoop.util.DateUtils;

public class AuthenticateUserTest {
	
	
	@Test
	public void testAuthenticateUser() {
		final GeneralService generalService = new GeneralService();
		final DateUtils dateUtils = new DateUtils();
		final Employee testUser = null;
		Assert.assertEquals(false, generalService.authenticateUser(testUser));
		final Customer testCustomer = new Customer(1, "Marcel", "Groothuis", "Manollo7G", "secret", "mjg@cerios.nl", dateUtils.getCurrentSqlDate(), dateUtils.getCurrentSqlTime());
		Assert.assertEquals(true, generalService.authenticateUser(testCustomer));
	}

}
