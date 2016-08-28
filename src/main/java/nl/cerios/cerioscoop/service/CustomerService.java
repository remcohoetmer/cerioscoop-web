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

	public void updateChairsSold(int chairsSold, int showingId) {

		String updateSQL = "UPDATE show_table SET chairs_sold = chairs_sold +? WHERE show_id = ?";

		try {
			final Connection connection = dataSource.getConnection();
			final PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);

			preparedStatement.setInt(1, (chairsSold));
			preparedStatement.setInt(2, showingId);
			preparedStatement.executeUpdate();

			System.out.println("Chairs_sold is updated.");
		} catch (final SQLException e) {
			throw new ServiceException("Something went wrong while updating the chairsSoldAmount.", e);
		}
	}


	public int getChairsSoldAmountByShowId(int showing_id) {
		String id = Integer.toString(showing_id);
		String SQL = "SELECT chairs_sold FROM show_presentation WHERE show_id =" + id;

		try (final Connection connection = dataSource.getConnection()) {
			final Statement statement = connection.createStatement();
			final ResultSet resultSet = statement.executeQuery(SQL);
			{
				resultSet.next();
				int chairsSold = resultSet.getInt("chairs_sold");
				System.out.println(chairsSold);
				return chairsSold;
			}

		} catch (final SQLException e) {
			throw new ServiceException("Something went terribly wrong while retrieving the chairsSoldAmount.", e);
		}
	}

}