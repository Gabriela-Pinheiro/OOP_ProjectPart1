//Gabriela Pinheiro - R00225375 - Project_Part1

package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerialStorage implements Serializable{
	private static final String fileName = "serialPatientsData.ser";

	
	public static boolean writeFile(Object o) {
		boolean result = true;
		try {
			FileOutputStream file = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(o);
			out.close();
			file.close();
			System.out.println("Object has been serialized");
		} catch (IOException ex) {
			System.out.println("line 24 " + ex);
			result = false;
		}
		return result;
	}
	
	
	public static Object readFile() {
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
}
