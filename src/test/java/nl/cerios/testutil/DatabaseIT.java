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

public class DatabaseIT {

	private static final Properties PROPS;

	static {
		final URL propertyDataURL = DatabaseIT.class.getResource("/integration-test.properties");
		try (final InputStream propertyDataStream = propertyDataURL.openStream()) {
			PROPS = new Properties();
			PROPS.load(propertyDataStream);
		} catch (IOException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	@Spy
	private DataSource dataSource = getDataSource();

	private static DataSource getDataSource() {
		final EmbeddedDataSource40 derbyDS = new EmbeddedDataSource40();
		derbyDS.setDatabaseName("memory:" + PROPS.getProperty("db.name"));
		derbyDS.setUser(PROPS.getProperty("db.user"));
		derbyDS.setPassword(PROPS.getProperty("db.password"));
		return derbyDS;
	}

	@BeforeClass
	public static void initDatabase() throws IOException, SQLException {
		final DataSource ds = getDataSource();
		// tell Derby to create the schema
		((EmbeddedDataSource40) ds).setCreateDatabase("create");

		// run SQL scripts
		try (final Connection connection = ds.getConnection();
				final Statement sqlStatement = connection.createStatement()) {

			final String[] scriptResourceNames = new String[]{
				PROPS.getProperty("db.script.create"),
				PROPS.getProperty("db.script.testData")
			};
			
			for (String scriptResourceName : scriptResourceNames) {
				try (InputStream scriptStream = DatabaseIT.class.getResourceAsStream(scriptResourceName)) {
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
			final DataSource ds = getDataSource();
			((EmbeddedDataSource40) ds).setShutdownDatabase("shutdown");
			ds.getConnection();
		} catch (SQLNonTransientConnectionException e) {
			// Wow, this is a dirty tricky piece of code! See
			// http://stackoverflow.com/questions/2307788/how-to-shutdown-derby-in-memory-database-properly
			final String dbState = e.getSQLState();
			if (!dbState.equals("XJ015") && !dbState.equals("08006")) {
				throw e;
			}
		}
	}
}
