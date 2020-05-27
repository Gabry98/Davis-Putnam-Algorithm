/*
 * In this class you'll be able to test directly the algorithm. As you can see,
 * this algorithm works with clauses, which contains boolean variables. 
 * 
 * The step 1 consists in logical variables creation, which are with a boolean 
 * value and a name for them. If you want to make the complementary of a logical variable
 * you can use the method makeComplementary, of the complementary variable. 
 * 
 * In the step 2, the logical variables will be encapsulated inside some arrays.
 * 
 * In the step 3, there will be the clauses creation with the arrays that have 
 * already been created.
 * 
 * In the step 4, there will be the creation of a list which contains all the clauses.
 * 
 * In the step 5, in the end, there will be the execution of the Davis-Putnam-Algorithm
 * with the class DPA. It will return the boolean value of the resolution.
 */


import java.util.LinkedList;

public class Client {
	public static void main(String[] args) {
		
		// Step 1:
		LogicalVariables a = new LogicalVariables(true,"a");
		LogicalVariables notA = a.makeComplementary();
		LogicalVariables b = new LogicalVariables(true,"b");
		LogicalVariables notB = b.makeComplementary();
		LogicalVariables d = new LogicalVariables(true,"d");
		LogicalVariables notD = d.makeComplementary();
		LogicalVariables e = new LogicalVariables(true,"e");
		LogicalVariables notE = e.makeComplementary();
		LogicalVariables f = new LogicalVariables(true,"f");
		LogicalVariables notF = f.makeComplementary();
		LogicalVariables notC = new LogicalVariables(false,"notc");
		
		// Step 2:
		LogicalVariables[] lc1 = {notA,b};
		LogicalVariables[] lc2 = {notA,d};
		LogicalVariables[] lc3 = {notC,e};
		LogicalVariables[] lc4 = {notD,e};
		LogicalVariables[] lc5 = {notB,notE,f};
		LogicalVariables[] lc6 = {a};
		LogicalVariables[] lc7 = {notF};
		
		// Step 3:
		Clauses c1 = new Clauses(lc1);
		Clauses c2 = new Clauses(lc2);
		Clauses c3 = new Clauses(lc3);
		Clauses c4 = new Clauses(lc4);
		Clauses c5 = new Clauses(lc5);
		Clauses c6 = new Clauses(lc6);
		Clauses c7 = new Clauses(lc7);
		
		// Step 4:
		LinkedList<Clauses> lc = new LinkedList<Clauses>();
		lc.add(c1);
		lc.add(c2);
		lc.add(c3);
		lc.add(c4);
		lc.add(c5);
		lc.add(c6);
		lc.add(c7);
		
		// Step 5:
		DPA alg = new DPA(lc);
		System.out.println(alg.algorithm());
	}
}
