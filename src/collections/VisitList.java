//Gabriela Pinheiro - R00225375 - Project_Part1

package collections;

import java.io.Serializable;
import java.util.ArrayList;

import model.Patient;
import model.Visit;

public class VisitList implements Serializable{
	
	private ArrayList<Visit> visits;
	
	public VisitList() {
		this.visits = new ArrayList<Visit>();
	}
	
	public boolean addVisits(Visit visit) {
		try {
			this.visits.add(visit);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Number of visits: " + visits.size() + ", being: " + visits;
	}

}
