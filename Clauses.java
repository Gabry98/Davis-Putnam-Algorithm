/*
 * This class is about the clauses of the algorithm
 */

import java.util.List;
import java.util.LinkedList;

public class Clauses {
	private LinkedList<LogicalVariables> elements = new LinkedList<LogicalVariables>();
	
	//in the constructor method i will add every selected variable to the clause
	public Clauses(LogicalVariables[] variables) {
		for(int i=0; i<variables.length; i++) {
			elements.add(variables[i]);
		}
	}
	
	public void add(LogicalVariables lv) { // adds a variable to the clause
		elements.add(lv);
	}
	
	public void remove(LogicalVariables lv) { //removes a variable to the clause
		elements.remove(lv);
	}
	
	
	public LogicalVariables get(int index){ //returns the selected variable
		return elements.get(index);
	}
	
	public LinkedList<LogicalVariables> getList() { //returns the clause's content
		return elements;
	}
	
	public void addList(LinkedList<LogicalVariables> lv) { //adds a list of variables to the clause
		elements.addAll(lv);
	}
	
	public int getSize() { //returns the size of clause
		return elements.size();
	}
	
	//this method will check if there is the selected clause as a sub set of this clause
	public boolean containsClausole (List<LogicalVariables> lv) {
		boolean contains = true;
		for(int i=0; i<lv.size() && contains; i++) {
			LogicalVariables variable = lv.get(i);
			contains = elements.stream().anyMatch(e -> e.equals(variable));
		}
		return contains;
	}
	
	//returns true if there is the selected logical variable inside the clause
	public boolean containsVariable(LogicalVariables lv) {
		return elements.stream().anyMatch(e -> e.equals(lv));
	}
}
