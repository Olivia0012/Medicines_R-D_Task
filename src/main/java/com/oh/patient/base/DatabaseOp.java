package com.oh.patient.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseOp {
	
	private static final String driver = "org.postgresql.Driver";
	private static final String username = "visopwrfwurtch";
	private static final String password = "e96ec9ac2a925dc0d496ff907db4107bdc77a2392a23eeae9829a062e8ae139c";
	private static final String url = "jdbc:postgresql://ec2-54-205-183-19.compute-1.amazonaws.com:5432/dftr2330sk9gq8";
	
	/*	  private static final String driver = "org.postgresql.Driver";
	  private static final String username = "postgres"; 
	  private static final String password ="123"; 
	  private static final String url =
	  "jdbc:postgresql://localhost:5432/postgres"; 
	*/
	// minimum number
	private static final int minCount = 1;
	// Maximum number
	private static final int maxCount = 10;
	public static List<Connection> conPool = new ArrayList<Connection>();

	public static Connection connection() {
		try {
			Class.forName(driver);
			Connection c = DriverManager.getConnection(url, username, password);
			conPool.add(c);
			return c;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
			return null;
		}
		// System.out.println("Opened database successfully");

	}

	public synchronized static Connection getConnection() {
		Connection conn = null;
		if (conPool.size() == 0) {
			conn = connection();

		} else {
			conn = conPool.remove(0);
		}
		System.err.println(conPool.size());
		return conn;
	}

	public synchronized static void closeConnection(Connection conn) {
		if (conPool.size() < maxCount) {
			conPool.add(conn);
		}
	}

	public static PreparedStatement prepareInsert(String statement) {
		PreparedStatement stmt = null;
		Connection c = null;
		try {
			c = getConnection();
			stmt = c.prepareStatement(statement, Statement.RETURN_GENERATED_KEYS);
			return stmt;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		closeConnection(c);
		return null;
	}

	public static PreparedStatement prepare(String statement) {
		PreparedStatement stmt = null;
		Connection c = null;
		try {
			c = getConnection();
			stmt = c.prepareStatement(statement);
			return stmt;
		} catch (Exception e) {
			e.printStackTrace();

			System.exit(0);
		} finally {
			closeConnection(c);
		}

		return null;
	}

	public static void main(String args[]) {
		//DatabaseOp dbc = new DatabaseOp();
		//dbc.connection();
		
		Connection c = null;
	      Statement stmt = null;
	      try {
	    	  Class.forName(driver);
			  c = DriverManager.getConnection(url, username, password);
	         System.out.println("connected succeeded！");
	         stmt = c.createStatement();
	         
	      // Create test patient table
	         String sql = " DROP TABLE IF EXISTS patient;CREATE TABLE patient " +
                 "(pid  VARCHAR PRIMARY KEY NOT NULL," +
                 " name  VARCHAR"
                 + ")";
	         
	         // Create medicine_records table
	         String sql6 = " DROP TABLE IF EXISTS medicinerecords;"
	         		+ "CREATE TABLE medicinerecords " +
                 "(mrid  VARCHAR PRIMARY KEY     NOT NULL," +
                 " medicine        VARCHAR, " +
                 " amount        REAL, " +
                 " pid    VARCHAR   NOT NULL, " +
                 " takentime    TIMESTAMP WITH TIME ZONE," +
                 "CONSTRAINT fk_patient" + 
                 "      FOREIGN KEY(pid)" + 
                 "	  REFERENCES patient(pid)" +
                 ")";
	         
	         stmt.executeUpdate(sql6);
	         stmt.close();
	         c.close();
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	      System.out.println("Create Table successfully！");
	   }

	

}
