//Gabriela Pinheiro - R00225375 - Project_Part1

import java.time.LocalDate;

import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import menu.Menu;
import model.Consultant;
import model.Name;
import model.Patient;
import model.Practice;
import model.SerialStorage;
import model.Visit;

public class Main extends Application {
	
	@Override
	public void start(Stage stage) {
		try {
			
			Controller.getInstance().init(stage);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
		
	
	private static void testClass() {
	
		// Instantiating patients + illnesses/severity
				Patient patient1 = new Patient(new Name("Claire", "Muniz"), "08400452190");
				Patient patient2 = new Patient(new Name("Marcos", "Fonseca"), "0848955419");
				Patient patient3 = new Patient(new Name("Ruby", "Fonseca"), "0848955419");
				Patient patient4 = new Patient(new Name("Kessie", "Simons"), "0872103214");
				Patient patient5 = new Patient(new Name("Olivia", "McCarty"), "0864589623");
				Patient patient6 = new Patient(new Name("Liam", "Vieira"), "02378401121");
				Patient patient7 = new Patient(new Name("Alfie", "Martins"), "0811236547");
				Patient patient8 = new Patient(new Name("Liz", "Dias"), "0844521690");
				
				patient1.setIlness("Covid");
				patient1.setSeverity(Patient.Severity.MODERATETOSEVERE);
				patient2.setIlness("Headache");
				patient2.setSeverity(Patient.Severity.MODERATE);
				patient3.setIlness("Chickenpox");
				patient3.setSeverity(Patient.Severity.MILD);
				patient4.setIlness("Influenza");
				patient4.setSeverity(Patient.Severity.MODERATE);
				patient5.setIlness("Allergy");
				patient5.setSeverity(Patient.Severity.SEVERE);
				patient6.setIlness("Diarrhea");
				patient6.setSeverity(Patient.Severity.MILD);
				patient7.setIlness("Headache");
				patient7.setSeverity(Patient.Severity.MODERATETOSEVERE);
				patient8.setIlness("Tooth ache");
				patient8.setSeverity(Patient.Severity.MILDTOMODERATE);
//				System.out.println(patient3.getIlness()); 		//patient tests
//				System.out.println(patient3.getSeverity());  	//patient tests
//				System.out.println(patient3);   				//patient tests
				
				// Instantiating consultants
				Consultant consultant1 = new Consultant(new Name("Barbara", "Qain"), "0862154789", "GP");
				Consultant consultant2 = new Consultant(new Name("Helena", "Clark"), "08245627541", "Pediatritian");
				Consultant consultant3 = new Consultant(new Name("Shane", "Alves"), "0872685491", "Dentist	");
//				System.out.println(consultant2);

				// Instantiating practice
				Practice practice = new Practice();
				
				
				// OPERATIONS
				practice.addConsultant(consultant1);
				practice.addConsultant(consultant2);
				practice.addConsultant(consultant3);
//				System.out.println(practice);
				
				consultant1.addPatient(patient1);
				consultant1.addPatient(patient4);
				consultant1.addPatient(patient6);
				consultant2.addPatient(patient3);
				consultant2.addPatient(patient5);
				consultant2.addPatient(patient7);
				consultant3.addPatient(patient2);
				consultant3.addPatient(patient8);
				
				patient1.addVisit(new Visit(LocalDate.of(2023, 11, 20), "Patient has fever and headache"));
				consultant1.addPatientVisit(patient1, new Visit(LocalDate.of(2023, 12, 05), "Patient developing pre-diabetes"));
				
				patient4.addVisit(new Visit(LocalDate.of(2023, 10, 06), "Runny nose, Puffy, watery eyes"));
				consultant1.addPatientVisit(patient4, new Visit(LocalDate.of(2023, 11, 18), "sneezing only. Patient discharged"));
				
				patient6.addVisit(new Visit(LocalDate.of(2023, 11, 24), "Dehidration, serum Intravenous"));
				
				patient3.addVisit(new Visit(LocalDate.of(2023, 10, 30), "Swollen ankle"));
				consultant2.addPatientVisit(patient3, new Visit(LocalDate.of(2023, 11, 03), "Removing plaster cast, return in 15 days"));
				
				patient5.addVisit(new Visit(LocalDate.of(2023, 11, 13), "Inflamed, itchy nose and throat with hives or skin rashes"));
				consultant2.addPatientVisit(patient5, new Visit(LocalDate.of(2023, 11, 23), "Stuffy nose only. Patient discharged"));
				
				patient7.addVisit(new Visit(LocalDate.of(2023, 10, 06), "Migrane, rest in dark with intravenous medication"));
				consultant2.addPatientVisit(patient7, new Visit(LocalDate.of(2023, 11, 18), "Mild headache, keep on oral medication. Patient discharged"));
				
				patient2.addVisit(new Visit(LocalDate.of(2023, 10, 06), "Strong headache and fever"));
				consultant3.addPatientVisit(patient2, new Visit(LocalDate.of(2023, 11, 03), "No more headaches, keepig treatment when needed"));

				patient8.addVisit(new Visit(LocalDate.of(2023, 10, 06), "Teeth extraction"));
				consultant3.addPatientVisit(patient8, new Visit(LocalDate.of(2023, 11, 18), "No pain. Patient discharged"));

//				System.out.println(patient5.showVisits());
//				System.out.println(consultant2.getPatients());
//				System.out.println(practice.showConsultants());
//				System.out.println(consultant3.showPatientsAndVisits());
//				System.out.println(practice.showConsultants());
				
				// keep for final
				System.out.println(practice.showConsultantsDetails());

				SerialStorage.writeFile(practice);
	}
	
}
