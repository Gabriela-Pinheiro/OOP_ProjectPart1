package menu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import collections.PatientList;
import model.Consultant;
import model.Name;
import model.Patient;
import model.Practice;
import model.SerialStorage;
import model.Visit;

public class Menu {

	private Practice practice;
	private int menuSelectedOption;
	private Scanner input;
	private MenuOptions option;

	public Menu() {
		input = new Scanner(System.in);

		Object obj = SerialStorage.readData();
		this.practice = (Practice)obj;

		for(Consultant c: practice.getConsultants()) {
			Consultant.ID++;
			for(Patient p: c.getPatients()) {
				Patient.ID++;
			}
		}
		showMenu();
	}


	private void showMenu() {
		try {
			do {
				System.out.println("\n  Hospital Consultancy Menu: ");
				System.out.println("1. Record a Patient visit");
				System.out.println("2. Display all patients of a particular Consultant");
				System.out.println("3. Display all Consultants followed by their Patients followed by their visits");
				System.out.println("4. Load information from a file");
				System.out.println("5. Exit");
				System.out.println("\nEnter desired option, please:");
				menuSelectedOption = input.nextInt();
				input.nextLine();

			} while (menuSelectedOption < 1 || menuSelectedOption > 5);
			option = MenuOptions.values()[menuSelectedOption -1];
			switch (option) {
			case RecordPatientVisit:
				recordPatientVisit();
				break;
			case DisplayConsultantsPatients:
				readConsultantsPatients();
				break;
			case DisplayConsultatsPatientsTheirVisists:
				readConsultantsPatientsVisits();
				break;
			case LoadInformationIntoFile:
				writeToFile();
				break;
			case Exit:
				System.out.println("Exit");
				System.exit(0);
				break;
			}
			showMenu();

		} catch (InputMismatchException ex) {
			input.nextLine();
			System.out.println("Invalid selection. Choose from:");
			showMenu();
		}
	}
	
	private Consultant getConsultantSelected() {
		ArrayList<Consultant> consultants = practice.getConsultants();
		int menuSelectedOption;
		try {
			do {

				for(int i = 1; i <= consultants.size(); i++) {			
					System.out.println(i + ". " + consultants.get(i-1).getId() + ": " + consultants.get(i-1).getName());		
				}
				System.out.println("Enter desired consultant, please:");
				menuSelectedOption = input.nextInt();
				input.nextLine();
				
			}
			while (menuSelectedOption < 1 || menuSelectedOption > consultants.size());
			
			return consultants.get(menuSelectedOption-1);			
			
		}
		catch (InputMismatchException ex) {
			input.nextLine();
			System.out.println("Invalid selection. Choose from:");
			return getConsultantSelected();
			
		}
	}

	private Patient getPatientSelected(Consultant c) {
		ArrayList<Patient> patients = c.getPatients();
		int menuSelectedOption;
		try {
			do {

				for(int i = 1; i <= patients.size(); i++) {			
					System.out.println(i + ". " + patients.get(i-1).getId() + ": " + patients.get(i-1).getName());		
				}
				System.out.println("Enter desired patient, please:");
				menuSelectedOption = input.nextInt();
				input.nextLine();
				
			}
			while (menuSelectedOption < 1 || menuSelectedOption > patients.size());
			
			return patients.get(menuSelectedOption-1);			
			
		}
		catch (InputMismatchException ex) {
			input.nextLine();
			System.out.println("Invalid selection. Choose from:");
			return getPatientSelected(c);
		}

	}
	
	
	private void recordPatientVisit() {
		Consultant c = getConsultantSelected();
		Patient p = getPatientSelected(c);
		System.out.println("Enter date of visit, please (DD/MM/YYYY):");
		String d = input.next();
		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse(d, f);
		System.out.println("Enter visit notes:");
		input.nextLine();
		String notes = input.nextLine();
		Visit v = new Visit(date, notes);
		p.addVisit(v);
		
		System.out.println("\nPatient " + p.getName() + ":" + v);
		
	}

	private void readConsultantsPatients() {		
		System.out.println(getConsultantSelected().showPatients());
	}

	private void readConsultantsPatientsVisits() {
		System.out.println(this.practice.showConsultantsDetails());
	}

	private void writeToFile() {
		SerialStorage.writeFile(this.practice);
	}
}
