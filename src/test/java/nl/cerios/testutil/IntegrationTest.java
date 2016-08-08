package nl.cerios.testutil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class IntegrationTest {

	private static final String PROPERTY_FILE = "/integration-test.properties";
	private static Properties PROPERTIES;

	private static Properties loadProperties(final String propertyFile) {
		try (final InputStream is = IntegrationTest.class.getResourceAsStream(propertyFile)) {
			final Properties properties = new Properties();
			properties.load(is);
			return properties;
		} catch (IOException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static String getProperty(final String propertyName) {
		if (PROPERTIES == null) {
			PROPERTIES = loadProperties(PROPERTY_FILE);
		}
		return PROPERTIES.getProperty(propertyName);
	}
}