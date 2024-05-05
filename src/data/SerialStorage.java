//Gabriela Pinheiro - R00225375 - Project_Part2

package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import model.Consultant;
import model.Patient;

public class SerialStorage implements Serializable, DataHelper{
	private static final String fileName = "serialPatientsData.ser";
	
	@Override
	public Object read() {
		Object outObject = null;
		try {
			FileInputStream file = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(file);
			
			outObject = in.readObject();
			in.close();
			file.close();
			System.out.println("Object has been deserialized");
			
		} catch (IOException ex) {
			System.out.println("IOException is caught 42 " + ex);
		} catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException is caught 44 " + ex);
		}
		return outObject;
	}


	
	@Override
	public void createOrUpdate(Object o) {
		try {
			FileOutputStream file = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(o);
			out.close();
			file.close();
			System.out.println("Object has been serialized");
		} catch (IOException ex) {
			throw new Error(ex);
		}
	}



	@Override
	public void deleteConsultant(Consultant c) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void deletePatient(Patient p) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void insertConsultant(Consultant c) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void insertPatient(Patient p, Consultant c) {
		// TODO Auto-generated method stub
		
	}
	


	

}
