//Gabriela Pinheiro - R00225375 - Project_Part2

package data;

import model.Consultant;
import model.Patient;

public interface DataHelper {
	public void createOrUpdate(Object o);
	public Object read();
	public void deleteConsultant(Consultant c);
	public void deletePatient(Patient p);
	public void insertConsultant(Consultant c);
	public void insertPatient(Patient p, Consultant c);

}
