package nl.cerios.cerioscoop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

@Stateless
public class CustomerService {

	@Resource(name = "jdbc/cerioscoop")
	private DataSource dataSource;

	public void updateChairAmount(int ticketAmount, int chairAmount, int showingId) {

		String updateSQL = "UPDATE show_presentation SET chair_amount = ? WHERE show_id = ?";

		try {
			final Connection connection = dataSource.getConnection();
			final PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);

			preparedStatement.setInt(1, (chairAmount - ticketAmount));
			preparedStatement.setInt(2, showingId);
			preparedStatement.executeUpdate();

			System.out.println("Chairamount is updated.");
		} catch (final SQLException e) {
			throw new ServiceException("Something went wrong while updating the chairamount.", e);
		}
	}

}
