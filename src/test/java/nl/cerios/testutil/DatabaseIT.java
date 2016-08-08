package nl.cerios.testutil;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.sql.Statement;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.derby.jdbc.EmbeddedDataSource40;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class DatabaseIT extends IntegrationTest {

	@BeforeClass
	public static void startDatabase() throws IOException, SQLException {
		final DataSource ds = getDataSource();
		((EmbeddedDataSource40) ds).setCreateDatabase("create");
		try (final Connection conn = ds.getConnection();
				final InputStream is1 = DatabaseIT.class.getResourceAsStream("/derby-schema.sql");
				final InputStream is2 = DatabaseIT.class.getResourceAsStream("/derby-testdata.sql")) {
			importSQL(conn, is1);
			importSQL(conn, is2);
		}
	}

	@AfterClass
	public static void stopDatabase() throws SQLException {
		try {
			final DataSource ds = getDataSource();
			((EmbeddedDataSource40) ds).setShutdownDatabase("shutdown");
			ds.getConnection();
		} catch (SQLNonTransientConnectionException e) {
			final String dbState = e.getSQLState();
			if (!dbState.equals("XJ015") && !dbState.equals("08006")) {
				// See http://stackoverflow.com/questions/2307788/how-to-shutdown-derby-in-memory-database-properly
				throw e;
			}
		}
	}

	public static DataSource getDataSource() {
		final EmbeddedDataSource40 derbyDS = new EmbeddedDataSource40();
		derbyDS.setDatabaseName("memory:" + getProperty("db.name"));
		derbyDS.setUser(getProperty("db.user"));
		derbyDS.setPassword(getProperty("db.password"));
		return derbyDS;
	}

	protected static void importSQL(final Connection conn, final InputStream in) throws SQLException {
		try (final Scanner s = new Scanner(in); final Statement st = conn.createStatement()) {
			s.useDelimiter("(;(\r)?\n)|(--\n)");
			while (s.hasNext()) {
				String line = s.next();
				if (line.startsWith("/*!") && line.endsWith("*/")) {
					int i = line.indexOf(' ');
					line = line.substring(i + 1, line.length() - " */".length());
				}

				if (line.trim().length() > 0) {
					st.execute(line);
				}
			}
		}
	}
}
