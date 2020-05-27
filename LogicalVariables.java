/*
 * This class is about the logical variables of the algorithm.
 */
public class LogicalVariables {
	private boolean b;
	private String id;
	private int myHash;
	
	public LogicalVariables(boolean b, String id) {
		this.b = b;
		this.id = id;
	}
	
	public boolean getValue() { //returns the boolean value of the variable
		return b;
	}
	
	public String getId() { //returns the name of the variable
		return id;
	}
	
	//sets the hash code of the variable for check where is its complementary
	public void setHash (int Hash) { 
		this.myHash = Hash;
	}
	
	public int getHash() { //returns the hash code of the variable
		return myHash;
	}
	
	//sets and returns the variable which is the complementary of this logical variable
	public LogicalVariables makeComplementary() {
		LogicalVariables c = null;
			if(!this.b) 
				c = new LogicalVariables(!b,this.getId().substring(3, this.getId().length()));
			else
				c = new LogicalVariables(!b,"not"+this.getId());
		
		this.setHash(this.hashCode());
		if(c!=null) c.setHash(this.getHash());
		return c;
	}
	
	//returns true if the selected variable is complementary to this logical variable
	public boolean isComplementary(LogicalVariables lv) {
		return this.getHash() == lv.getHash();
	}
}
