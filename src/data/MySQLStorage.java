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

import collections.PatientList;
import collections.VisitList;
import model.Consultant;
import model.Name;
import model.Patient;
import model.Practice;
import model.Visit;
import view.AlertBox;

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
	private void executeUpdate(List<String> queries) throws SQLException {
		stmt = conn.createStatement();
		for(String q: queries) {
			stmt.addBatch(q);
		}
		stmt.executeBatch();
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
	
	public ArrayList<Consultant> readConsultants() {
		
		try {
			ArrayList<Consultant> consultantList = new ArrayList<Consultant>();

			this.makeConnection();
			List<List<String>> results = this.executeQueryForResults("SELECT * FROM Consultants");
			for (int i = 0; i < results.size(); i++) {
				String ID = results.get(i).get(0);
				String fname = results.get(i).get(1);
				String lname = results.get(i).get(2);
				String phone = results.get(i).get(3);
				String expertise = results.get(i).get(4);
				Consultant c = new Consultant(new Name(fname, lname), phone, expertise, ID);
				c.setPatientList(readPatient(ID));
				consultantList.add(c);
			}
			return consultantList;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Error("Error connecting to DB", e);
		} finally {
			this.closeConnection();
		}
	}
	
	public PatientList readPatient(String consultantId) {
		try {
			PatientList patientList = new PatientList();

			this.makeConnection();
			List<List<String>> results = this.executeQueryForResults("SELECT * FROM Patients WHERE Patients.consultantId = " + "\"" + consultantId + "\"");
			for (int i = 0; i < results.size(); i++) {
				String ID = results.get(i).get(0);
				String fname = results.get(i).get(1);
				String lname = results.get(i).get(2);
				String phone = results.get(i).get(3);
				Patient p = new Patient(new Name(fname, lname), phone, ID, consultantId);
				p.setPatientVisits(readVisits(ID));
				patientList.addPatient(p);
			}
			return patientList;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Error("Error connecting to DB", e);
		} finally {
			this.closeConnection();
		}
	}
	
	public VisitList readVisits(String patientId) {
		try {
			VisitList visitList = new VisitList ();
			this.makeConnection();
			List<List<String>> results = this.executeQueryForResults("SELECT * FROM Visits WHERE Visits.patientId = " + "\"" + patientId + "\"");
			for (int i = 0; i < results.size(); i++) {
				LocalDate dateOfVisit = LocalDate.parse(results.get(i).get(0));
				String notes = results.get(i).get(1);
				String ilness = results.get(i).get(2);
				Visit v = new Visit(dateOfVisit, notes, ilness, patientId);				
				visitList.addVisits(v);
//				patients.addPatient(p);
			}
			return visitList;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Error("Error connecting to DB", e);
		} finally {
			this.closeConnection();
		}
	}

	@Override
	public void createOrUpdate(Object o) {
		try {
			this.makeConnection();
			Practice practice = (Practice) o;
			List<String> queries = new ArrayList<String>();
			for(Consultant c: practice.getConsultants()){
				queries.add("REPLACE INTO Consultants(ID, fname, lname, phone, expertise) VALUES ('" + c.getId() + "', '" + c.getName().getFirstName() + "', '" + c.getName().getLastName() + "', '" + c.getPhone() + "', '" + c.getExpertise() + "')");
				
				for(Patient p: c.getPatients()) {
					queries.add(" REPLACE INTO Patients(ID, fname, lname, phone, consultantId) VALUES ('" + p.getId() + "', '" + p.getName().getFirstName() + "', '" + p.getName().getLastName() + "', '" + p.getPhone() + "', '" + c.getId() + "')");
					
					ArrayList<Visit> visits = p.getPatientVisits().getVisits();
					
					if (visits.size() > 0) {
						queries.add("DELETE FROM Visits WHERE patientId = '" + p.getId() + "' ");
						for(Visit v: visits) {
							queries.add(" INSERT INTO Visits(dateOfVisit, notes, ilness, patientId) VALUES ('" + v.getDateOfVisitAsSQLString() + "', '" + v.getNotes() + "', '" + v.getIlness() + "', '" + p.getId() + "')");						
						}
					}
				}				
				
				executeUpdate(queries);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		
	}
	
	private int getLastConsultantId() {		
		try {
			this.makeConnection();
			int cId = Integer.parseInt(
					(this.executeQueryForResults("SELECT ID FROM Consultants ORDER by ID DESC LIMIT 1").get(0).get(0))
					.replace("CO", "")
					);
			return ++cId;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Error("Error connecting to DB", e);
		} finally {
			this.closeConnection();
		}
	}
	
	private int getLastPatientId() {
		try {
			this.makeConnection();
			int pId = Integer.parseInt(
					(this.executeQueryForResults("SELECT ID FROM Patients ORDER by ID DESC LIMIT 1").get(0).get(0))
					.replace("PA", "")
					);
			return ++pId;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Error("Error connecting to DB", e);
		} finally {
			this.closeConnection();
		}
	}
	
	@Override
	public Object read() {
		Practice practice = new Practice();
		practice.setConsultants(readConsultants());
		Consultant.ID = getLastConsultantId()+1;
		Patient.ID = getLastPatientId()+1;
		return practice;
	}
}
