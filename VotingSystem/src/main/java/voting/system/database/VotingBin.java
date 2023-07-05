package voting.system.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import voting.system.domain.Register;
import voting.system.domain.Vote;

public class VotingBin {

	public VotingBin() {

	}

	private static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static String dbSourceUrl = "jdbc:mysql://localhost:3306/votingdatabase";
	static private Connection courseDbConn;
	static private String userId = "root";
	static private String dbPassword = "ojas1234";

	public static Connection getConnection() throws SQLException {
		if (courseDbConn == null) {
			courseDbConn = DriverManager.getConnection(dbSourceUrl, userId, dbPassword);
		}

		return courseDbConn;
	}

	public static void shutdown() throws SQLException {
		if (courseDbConn != null) {
			courseDbConn.close();
		}
	}

	public int loginCheck(Register reg) throws SQLException, ClassNotFoundException {
		Class.forName(JDBC_DRIVER);
		Connection connection;
		connection = getConnection();
		int count;

		String queryStr = "select count(*) from studentdata where username = '" + reg.getUsername()
				+ "' and password = '" + reg.getPassword() + "'";
		Statement queryStmt = connection.createStatement();
		ResultSet result;
		result = queryStmt.executeQuery(queryStr);
		result.next();
		count = result.getInt(1);
		result.close();
		queryStmt.close();
		return count;
	}

	public int voteCheck(Vote vt) throws SQLException, ClassNotFoundException {
		Class.forName(JDBC_DRIVER);
		Connection connection;
		connection = getConnection();
		int count;

		String queryStr = "select count(*) from devlang where username = '" + vt.getUsername() + "'";
		Statement queryStmt = connection.createStatement();
		ResultSet result;
		result = queryStmt.executeQuery(queryStr);
		result.next();
		count = result.getInt(1);
		result.close();
		queryStmt.close();
		return count;
	}

	public void loginRegistration(Register reg) throws SQLException, ClassNotFoundException {
		Class.forName(JDBC_DRIVER);
		Connection connection;
		connection = getConnection();

		String queryStr1 = "insert into studentdata (fname,lname,username,password) values ('" + reg.getfName() + "','"
				+ reg.getlName() + "','" + reg.getUsername() + "','" + reg.getPassword() + "')";
		Statement queryStmt = connection.createStatement();
		try {
			queryStmt.executeUpdate(queryStr1);
		} catch (Exception e) {
		}
		queryStmt.close();
	}

	public List<Vote> displayVotingStatistics() throws SQLException, ClassNotFoundException {
		Class.forName(JDBC_DRIVER);
		List<Vote> list = new ArrayList<Vote>();
		Vote vt = new Vote();
		Connection connection;
		connection = getConnection();
		int javaCount, javascriptCount, pythonCount;

		String queryStr1 = "select count(*) from devlang where votes = 'Java'";
		String queryStr2 = "select count(*) from devlang where votes = 'JavaScript'";
		String queryStr3 = "select count(*) from devlang where votes = 'Python'";
		Statement queryStmt = connection.createStatement();
		Statement queryStmt2 = connection.createStatement();
		Statement queryStmt3 = connection.createStatement();
		ResultSet result1, result2, result3;

		result1 = queryStmt.executeQuery(queryStr1);
		result2 = queryStmt2.executeQuery(queryStr2);
		result3 = queryStmt3.executeQuery(queryStr3);

		if (result1.next()) {
			javaCount = result1.getInt(1);
			vt.setJavaCount(javaCount);
		}
		if (result2.next()) {
			javascriptCount = result2.getInt(1);
			vt.setJavascriptCount(javascriptCount);
		}
		if (result3.next()) {
			pythonCount = result3.getInt(1);
			vt.setPythonCount(pythonCount);
		}
		list.add(vt);
		result1.close();
		result2.close();
		result3.close();
		queryStmt.close();
		return list;
	}

	public void voteRegistration(Vote vt) throws SQLException, ClassNotFoundException {
		Class.forName(JDBC_DRIVER);
		Connection connection;
		connection = getConnection();

		String queryStr = "insert into devlang (username,votes) values ('" + vt.getUsername() + "','" + vt.getVotes()
				+ "')";
		Statement queryStmt = connection.createStatement();
		try {
			queryStmt.executeUpdate(queryStr);
		} catch (Exception e) {
		}
		queryStmt.close();
	}

	public List<Vote> getVote() throws SQLException, ClassNotFoundException {
		Class.forName(JDBC_DRIVER);
		List<Vote> list1 = new ArrayList<Vote>();
		Connection connection;
		connection = getConnection();

		String queryStr1 = "select * from devlang";
		Statement queryStmt = connection.createStatement();
		ResultSet result1;

		result1 = queryStmt.executeQuery(queryStr1);
		while (result1.next()) {
			Vote vt = new Vote();
			vt.setId(result1.getInt("id"));
			vt.setUsername(result1.getString("username"));
			vt.setVotes(result1.getString("votes"));
			list1.add(vt);
		}
		result1.close();
		queryStmt.close();
		return list1;
	}

}
