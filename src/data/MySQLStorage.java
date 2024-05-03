//Gabriela Pinheiro - R00225375 - Project_Part2

package data;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Consultant;
import model.Name;
import model.Patient;
import model.Visit;

public class MySQLStorage implements DataHelper {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String db = "patients_db";
	static final String DB_URL = "jdbc:mysql://localhost/"+db;

	// Database credentials
	static final String USER = "root";
	static final String PASS = "x)gthM)D!76uHp$9B*6";
	Connection conn;
	Statement stmt;
	ResultSet rs;

	public MySQLStorage() {
		this.readConsultants();
	}

	public void makeConnection() {

		try {
			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected to database!");

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getResults(String query) throws SQLException {
		// Statements allow to issue SQL queries to the database
		stmt = conn.createStatement();
		// Result set get the result of the SQL query
		rs = stmt.executeQuery(query);
	}

	// generic method which will return a list of rows where each row contains a
	// list of strings
	// we can convert the columns later when we get them back
	public List<List<String>> executeQueryForResults(String query) throws SQLException {
		System.out.println(query);
		getResults(query);
		ResultSetMetaData meta = rs.getMetaData();
		final int columnCount = meta.getColumnCount();
		List<List<String>> rowList = new ArrayList<List<String>>();
		while (rs.next()) {
			List<String> columnList = new ArrayList<String>();
			rowList.add(columnList);

			for (int column = 1; column <= columnCount; column++) {
				Object value = rs.getObject(column);
				columnList.add(String.valueOf(value));
			}
		}
		return rowList;
	}

	//execute a query where we will not get results from the database but just update it
	public void executeUpdate(String query) throws SQLException {
		// Statements allow to issue SQL queries to the database
		stmt = conn.createStatement();
		// Result set get the result of the SQL query
		stmt.executeQuery(query);
	}

	public void closeConnection() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException se2) {
		}
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}finally{

			System.out.println("disConnected from database!");
		}
	}
	
	public void readConsultants() {
		// ReadWriteToFile read = new ReadWriteToFile();
		// patients = (PatientList) read.readFromSerialFile("patients.ser");
		try {
			this.makeConnection();
			List<List<String>> results = this.executeQueryForResults("SELECT * FROM Consultants");
			for (int i = 0; i < results.size(); i++) {
				String ID = results.get(i).get(0);
				String fname = results.get(i).get(1);
				String lname = results.get(i).get(2);
				String phone = results.get(i).get(3);
				String expertise = results.get(i).get(4);
				Consultant c = new Consultant(new Name(fname, lname), phone, expertise, ID);
				readPatient(ID);
//				patients.addPatient(p);
			}
			this.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			this.closeConnection();
		}
	}
	
	public void readPatient(String consultantId) {
		try {
			this.makeConnection();
			List<List<String>> results = this.executeQueryForResults("SELECT * FROM Patients WHERE Patients.consultantId = " + "\"" + consultantId + "\"");
			for (int i = 0; i < results.size(); i++) {
				String ID = results.get(i).get(0);
				String fname = results.get(i).get(1);
				String lname = results.get(i).get(2);
				String phone = results.get(i).get(3);
				Patient p = new Patient(new Name(fname, lname), phone, ID, consultantId);
				readVisits(ID);
//				patients.addPatient(p);
			}
			this.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			this.closeConnection();
		}
	}
	
	public void readVisits(String patientId) {
		try {
			this.makeConnection();
			List<List<String>> results = this.executeQueryForResults("SELECT * FROM Visits WHERE Visits.patientId = " + "\"" + patientId + "\"");
			for (int i = 0; i < results.size(); i++) {
				LocalDate dateOfVisit = LocalDate.parse(results.get(i).get(0));
				String notes = results.get(i).get(1);
				String ilness = results.get(i).get(2);
				Visit v = new Visit(dateOfVisit, notes, ilness, patientId);
//				patients.addPatient(p);
			}
			this.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			this.closeConnection();
		}
	}


	@Override
	public void createOrUpdate(Object o) {
		
	}

	@Override
	public Object read() {
		return null;
	}
}
