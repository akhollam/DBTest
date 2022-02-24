import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

	static Properties properties = new Properties();

	static {

		try {
			InputStream inputStream = ClassLoader.getSystemResourceAsStream("db.properties");
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Connection getConnection() throws SQLException {

		String url = properties.getProperty("url");
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");

		return DriverManager.getConnection(url, user, password);

	}

}
