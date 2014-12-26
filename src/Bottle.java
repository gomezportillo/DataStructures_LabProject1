/**
 * This class will implement the bottles
 * @author Pedro Manuel Gómez-Portillo López
 * @version 2.Oct.2014
 */
public class Bottle {

	Cap cap;
	String numberB;
	
	/**
	 * This method is the constructor method of the class
	 * @param b the identifier of the bottle
	 */
	public Bottle (String b){
		this.cap=null;
		this.numberB=b;	
	}
	
	public void addCap(Cap c){
		this.cap=c;
	}
}
