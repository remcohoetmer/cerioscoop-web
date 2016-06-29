package nl.cerios.cerioscoop.service;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

@Stateless
public class CustomerServices {
	
	@Resource(name = "jdbc/cerioscoop")			//Content Dependency Injection techniek
	private DataSource dataSource;
	

}
