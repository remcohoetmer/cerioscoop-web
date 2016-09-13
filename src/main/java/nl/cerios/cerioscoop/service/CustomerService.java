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
				final ShowPresentation show = new ShowPresentationBuilder().withShowingId(showId)
						.withMovieTitle(resultSet.getString("movie_title"))
						.withShowingDate(resultSet.getDate("show_date")).withShowingTime(resultSet.getTime("show_time"))
						.build();
				return show;
			}

		} catch (final SQLException e) {
			throw new ServiceException("Something went terribly wrong while retrieving the showPresentation.", e);
		}
	}

	public boolean isAlfanumeric(CharSequence seq) {
		int len = seq.length();
		for (int i = 0; i < len; i++) {
			char c = seq.charAt(i);
			// Test for all positive cases
			if ('0' <= c && c <= '9')
				continue;
			if ('a' <= c && c <= 'z')
				continue;
			if ('A' <= c && c <= 'Z')
				continue;
			if (c == ' ')
				continue;
			// If we get here, we had an invalid char, fail right away
			return false;
		}
		// All seen chars were valid, succeed
		return true;
	}

	public boolean isUniqueUser(String user) {
		String SQL = "SELECT username FROM customer where username=?";
		try (final Connection connection = dataSource.getConnection()) {
			PreparedStatement preparedstatement = connection.prepareStatement(SQL);
			preparedstatement.setString(1, user);
			ResultSet resultSet = preparedstatement.executeQuery();

			if (!resultSet.next()) {
				return true;
			} else {
				return false;
			}

		} catch (final SQLException e) {
			throw new ServiceException("Something went terribly wrong while retrieving the username.", e);
		}

	}
}