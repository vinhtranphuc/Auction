package home.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectMySQL {

	Connection conection;

	private String url = "jdbc:mysql://localhost/auction";
	private String user = "root";
	private String pass = "";

	Connection getConnection() throws SQLException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conection = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conection;
	}

	/**
	 * get PreparedStatement
	 * 
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	PreparedStatement getPrepareStatement(String query) throws SQLException {
		return getConnection().prepareStatement(query);
	}

	/**
	 * get ResultSet use PrepareStatement
	 * 
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	ResultSet getResultSet(String query) throws SQLException {
		return getPrepareStatement(query).executeQuery();
	}

}
