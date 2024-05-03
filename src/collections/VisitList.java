//Gabriela Pinheiro - R00225375 - Project_Part1

package collections;

import java.io.Serializable;
import java.util.ArrayList;
import model.Visit;

public class VisitList implements Serializable{
	
	private ArrayList<Visit> visits;
	
	public VisitList() {
		this.visits = new ArrayList<Visit>();
	}
	
	public Visit addVisits(Visit visit) {
		try {
			this.visits.add(visit);
		} catch (Exception e) {
			return null;
		}
		return visit;
	}

	public ArrayList<Visit> getVisits() {
		return visits;
	}

	public void setVisits(ArrayList<Visit> visits) {
		this.visits = visits;
	}

	@Override
	public String toString() {
		return "Total of visits: " + visits.size() + ":\n" + visits;
	}

}
