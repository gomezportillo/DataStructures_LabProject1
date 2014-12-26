import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * This class defines a FIFO dron object with its typical operations
 * We had to define the Dron class in this way as it is not possible to implement an array of LinkedBlockingQueue directly 
 * in the Factory, as generics do not allow creating arrays with them, so in this way we could deal with this setback
 * @author Pedro Manuel Gómez-Portillo López
 * @version 12.Oct.14
 */
public class Dron{

	private boolean free;
	private int time=0; //time when the dron gets called
	private Queue<Box> dron;
	private int numberBoxes; //number of boxes the dron is carring
	
	/**
	 * The constructor of the class 
	 */
	public Dron(){
		dron = new LinkedBlockingQueue<Box>();
		free = true;
		numberBoxes=0;	
	}

	/**
	 * This method returns the next box in delivering, but it does not delete from the dron
	 * @return the next box in delivering, but it does not delete from the dron
	 */
	public Box top(){
		return dron.element();
	}

	/**
	 * This method returns the String with the label of the next box in delivering
	 * @return the label of the next box to deliver
	 */
	public String getLabel(){
		return top().getLabelStorageUnit();
	}

	/**
	 * This method allows to add a new box to the dron
	 * @param b the box we want to add
	 */
	public void addBox(Box b){
		numberBoxes++;
		dron.add(b);

	}

	/**
	 * This method extracts the next box to deliver from the dron
	 * @return the current box of being delivered
	 */
	public Box getBox(){
		numberBoxes--;
		return dron.poll();
		
	}

	/**
	 * Shows the occupancy of the dron (if its carrying boxes)
	 * @return the occupancy of the dron: true if full false if empty
	 */
	public boolean isFree(){
		return free;
	}

	/**
	 * Sets the occupancy of the dron
	 * @param occ
	 */
	public void setFree(boolean f){
		free = f;
	}
	
	/**
	 * Saves the time when the dron got called
	 * @param t
	 */
	public void setTime(int t){
		time=t;
	}
	
	/**
	 * Returns the time when the dron got called
	 * @return the time when the dron got called
	 */
	public int getTime(){
		return time;
	}

	/**
	 * This method returns the number of boxes the dron is carring
	 * @return the number of boxes the dron is carring
	 */
	public int getNumberBoxes(){
		return numberBoxes;
	}

}
