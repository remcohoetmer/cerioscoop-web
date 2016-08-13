package nl.cerios.testutil;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.derby.jdbc.EmbeddedDataSource40;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.mockito.Spy;

public class DatabaseTest {

	private static final String OPTION_CREATE = "create";
	private static final String OPTION_DROP = "drop";
	private static final String OPTION_SHUTDOWN = "shutdown";
	private static final Properties PROPS;

	static {
		final URL propertyDataURL = DatabaseTest.class.getResource("/integration-test.properties");
		try (final InputStream propertyDataStream = propertyDataURL.openStream()) {
			PROPS = new Properties();
			PROPS.load(propertyDataStream);
		} catch (IOException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	@Spy
	private DataSource dataSource = getDataSource();

	private static DataSource getDataSource(final String... dbOptions) {
		String dbName = "memory:" + PROPS.getProperty("db.name");
		for (final String dbOption : dbOptions) {
			dbName += ";" + dbOption + "=true";
		}

		final EmbeddedDataSource40 derbyDS = new EmbeddedDataSource40();
		derbyDS.setDatabaseName(dbName);
		derbyDS.setUser(PROPS.getProperty("db.user"));
		derbyDS.setPassword(PROPS.getProperty("db.password"));
		return derbyDS;
	}

	@BeforeClass
	public static void initDatabase() throws IOException, SQLException {
		// tell Derby to create the schema
		final DataSource ds = getDataSource(OPTION_CREATE);
		try (final Connection connection = ds.getConnection();
				final Statement sqlStatement = connection.createStatement()) {

			// run SQL scripts
			final String[] scriptResourceNames = new String[] { PROPS.getProperty("db.script.create"),
					PROPS.getProperty("db.script.testData") };

			for (String scriptResourceName : scriptResourceNames) {
				try (InputStream scriptStream = DatabaseTest.class.getResourceAsStream(scriptResourceName)) {
					runSqlScript(sqlStatement, scriptStream);
				}
			}
		}
	}

	private static void runSqlScript(final Statement sqlStatement, final InputStream scriptInputStream)
			throws SQLException {
		try (final Scanner inputScanner = new Scanner(scriptInputStream)) {
			inputScanner.useDelimiter(";");
			while (inputScanner.hasNext()) {
				final String line = inputScanner.next().trim();
				if (!line.isEmpty()) {
					sqlStatement.execute(line);
				}
			}
		}
	}

	@AfterClass
	public static void shutdownDatabase() throws SQLException {
		try {
			// Remove the database schema from memory...
			getDataSource(OPTION_DROP).getConnection();
			// ...and stop the database instance.
			getDataSource(OPTION_SHUTDOWN).getConnection();
		} catch (SQLNonTransientConnectionException e) {
			// Wow, this is a dirty tricky piece of code! See
			// http://stackoverflow.com/questions/2307788/how-to-shutdown-derby-in-memory-database-properly
			final String dbState = e.getSQLState();
			if (!dbState.equals("XJ015") && !dbState.equals("08006")) {
				throw e;
			}
		} catch (SQLException e) {
			// Database was already shut down.
			if (!e.getSQLState().equals("XJ004")) {
				throw e;
			}
		}
	}
}
