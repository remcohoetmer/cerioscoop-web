package nl.cerios.cerioscoop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

@Stateless
public class CustomerService {

	@Resource(name = "jdbc/cerioscoop")
	private DataSource dataSource;

	public void updateChairAmount(int chairsSold, int chairAmount, int showingId) {
		
		String updateSQL = "UPDATE show_table SET chairs_sold = chairs_sold +? WHERE show_id = ?";
		
		try {
			final Connection connection = dataSource.getConnection();
			final PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);

			preparedStatement.setInt(1, (chairsSold));
			preparedStatement.setInt(2, showingId);
			preparedStatement.executeUpdate();

			System.out.println("Chairs_sold is updated.");
		} catch (final SQLException e) {
			throw new ServiceException("Something went wrong while updating the chairamount.", e);
		}
	}
	
	
	public int getChairsSoldAmount(){
		
		// TODO: wil je hier niet weten hoeveel stoelen per voorstelling ipv het totaal?
		String SQL = "SELECT chairs_sold FROM show_table";
		
		try (final Connection connection = dataSource.getConnection();
			final Statement statement = connection.createStatement();
			final ResultSet resultSet = statement.executeQuery(SQL)) {  
			
				// TODO: hoeveel rijen verwacht je eigenlijk dat hij teruggeeft?
				// Als dit er altijd maar 1 kan zijn, dan heb je geen while loop nodig toch?
				while (resultSet.next()) {
					final int chairsSold = resultSet.getInt("chairs_sold");
					
					// TODO: altijd een beetje gek om een 'return' statement binnen een loop te hebben. Toch?
					return chairsSold;
				}
			
				// Hier komt hij dus als er geen 'next()' in de resultset is. Als er geen enkele record gevonden wordt dus.
				throw new ServiceException("Niet mogelijk om aantal verkochte stoelen te bepalen, want record niet gevonden in de database");
			
		} catch (final SQLException e) {
	    	throw new ServiceException("Something went terribly wrong while retrieving the amount of chairs sold.", e);
		}
	}
}