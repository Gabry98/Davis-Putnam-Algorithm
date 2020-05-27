/*
 * The main class of the algorithm
 */

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class DPA {
	private List<Clauses> elements; //set of clauses
	private LinkedList<LogicalVariables> pcElements; //elements of the pivot clause
	private Clauses pc; //this will take the pivot clause at every step
	private LogicalVariables pivot; //the algorithm will change the pivot at every step
	private boolean unsatisfiable; // this variable will check if the set of clauses is satisfactory
	
	public DPA(List<Clauses> elements) {
		this.elements = elements;
	}
	
	public boolean algorithm() { //the main method
		
		while(elements.size()>0 && !unsatisfiable) {
			elements.sort(Comparator.comparing(Clauses::getSize));
			
			print(); //shows the tested set
			
			pc = elements.get(0);
			pivot = pc.get(0);
			pcElements = pc.getList();
			elements = makeNewList();
			
			print(); //shows the set with calculated solvents
			System.out.println();
		}
		return unsatisfiable;
	}
	
	private LinkedList<Clauses> makeNewList() { //solvents management method
		LinkedList<Clauses> newGroup = new LinkedList<Clauses>();
		
		//with this loop i will remove the pivot from the list of variables to be tested
		for(int i=0; i<elements.size(); i++) {
			if(!elements.get(i).containsVariable(pivot)) newGroup.add(elements.get(i));
		}
		
		for(int i=0; i<newGroup.size(); i++) { //calculates the solvents
			Clauses c = newGroup.get(i);
			if(c.getList().stream().anyMatch(e -> e.isComplementary(pivot)))
				newGroup.get(i).remove(newGroup.get(i).getList().stream().
					filter(e -> e.isComplementary(pivot)).findFirst().get());
			
			if(pc.getSize()>1)
				newGroup.get(i).addList(
						pcElements.stream().
						filter(cpe -> !cpe.equals(pivot)).
						collect(Collectors.toCollection(LinkedList::new)));
		}
		
		//if there is an empty clause the set is unsatisfiable
		if(newGroup.size()>0 && newGroup.get(0).getSize()==0) {
			newGroup.get(0).add(new LogicalVariables(true,"[]"));
			unsatisfiable = true;
		}
		else { //otherwise i will verify the satisfiability of other clauses
		
			for(int i=0; i<newGroup.size() && !unsatisfiable; i++) {
				Clauses c = newGroup.get(i);
				c = verifyEmptyClauses(c);
				if(unsatisfiable) {
					newGroup.remove(i);
					newGroup.add(i,c);
				}
			}
		}
		
		return newGroup;
	}
	
	//In this method i will check if there is an empty clause
	private Clauses verifyEmptyClauses(Clauses c) {
		for(int i=0; i<c.getSize() && !unsatisfiable; i++) {
			LogicalVariables lv = c.get(i);
			if(c.getList().stream()
				.filter(e->!e.equals(lv)).anyMatch(e -> e.isComplementary(lv))) {
				c.remove(lv);
				c.remove(c.getList().parallelStream()
						.filter(e->e.isComplementary(lv)).findFirst().get());
				c.add(new LogicalVariables(true,"[]"));
				unsatisfiable = true;
			}
		}
		return c;
	}
	
	// i will show the tested set at every step of the algorithm
	private void print() { 
		for(int i=0; i<elements.size(); i++) {
			System.out.print("[ ");
			elements.get(i).getList().stream()
			.forEach(e->System.out.print(e.getId()+" "));
			System.out.print("]");
		}
		System.out.println();
	}
}

