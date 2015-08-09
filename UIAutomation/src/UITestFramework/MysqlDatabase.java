package UITestFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import logger.Log;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class MysqlDatabase {

	public static Properties testDataFile = new Properties();
	File file = new File("");
	FileInputStream configFis;
	Connection connect = null;
	Statement statement = null;
	String DBURL;
	String username;
	String password;
	String driver;
	String DBURL_beta;
	String username_beta;
	String password_beta;




	public MysqlDatabase() throws IOException {
		// TODO Auto-generated constructor stub

		configFis = new FileInputStream(file.getAbsoluteFile()
				+ "//src//config//config.properties");
		testDataFile.load(configFis);

		DBURL= testDataFile.getProperty("DBURL").trim();
		username = testDataFile.getProperty("username").trim();
		password = testDataFile.getProperty("password").trim();
		DBURL_beta= testDataFile.getProperty("DBURL_beta").trim();
		username_beta = testDataFile.getProperty("username_beta").trim();
		password_beta = testDataFile.getProperty("password_beta").trim();
		driver = testDataFile.getProperty("driver").trim();

	}




	public Object getTableData(ResultSet resultSet, String column) throws SQLException {
		// ResultSet is initially before the first data set
		String result="";
		while (resultSet.next()) {
			// It is possible to get the columns via name
			// also possible to get the columns via the column number
			// which starts at 1
			// e.g. resultSet.getSTring(2);apiPrototype
			result = resultSet.getString(column);
			Log.info("result: " + result);

		}
		return result;
	}

	public ResultSet readDatabase(String sql, String message) throws Exception {
		try{
			// This will load the MySQL driver, each DB has its own driver
			Class.forName(driver);
			// Setup the connection with the DB
			connect = (Connection) DriverManager.getConnection(DBURL_beta, username_beta, password_beta);
			Log.info("connection successful to " + message);
			// Statements allow to issue SQL queries to the database
			statement = (Statement) connect.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			return rs;
		}
		catch(Exception e){
			Log.logError(this.getClass().getName(), "readProdDatabase", "Exception occured while reading beta databse");
			throw new Exception(e.getMessage());
		}

	}	
}
