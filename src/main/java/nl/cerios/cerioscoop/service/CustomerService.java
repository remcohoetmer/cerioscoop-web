package nl.cerios.cerioscoop.service;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import nl.cerios.cerioscoop.domain.ShowPresentation;
import nl.cerios.cerioscoop.domain.ShowPresentationBuilder;

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


	public ShowPresentation getShowPresentationByShowId(int showing_id) {
		BigInteger showId = BigInteger.valueOf(showing_id);
		String selectSQL = "SELECT movie_title, show_date, show_time FROM show_presentation WHERE show_id = ?";

		try (final Connection connection = dataSource.getConnection()) {
			final PreparedStatement preparedstatement = connection.prepareStatement(selectSQL);
			preparedstatement.setInt(1, showing_id);
			ResultSet resultSet = preparedstatement.executeQuery();
			
			{
				resultSet.next();
				final ShowPresentation show = new ShowPresentationBuilder()
						.withShowId(showId)
						.withMovieTitle(resultSet.getString("movie_title"))
						.withShowingDate(resultSet.getDate("show_date"))
						.withShowingTime(resultSet.getTime("show_time"))
						.build();	
				return show;
			}

		} catch (final SQLException e) {
			throw new ServiceException("Something went terribly wrong while retrieving the showPresentation.", e);
		}
	}


}