/**
 * This class will be the definer of the object box
 * @author Pedro Manuel Gómez-Portillo López
 * @version 2.Oct.2014
 */
public class Box {

	int MAX_SIZE = 12, numBottles;
	private String labelBox, labelStorageUnit;
	private Bottle[] bottles;

	/**
	 * This method is the constructor of the class
	 * @param lB the label of the box
	 */
	public Box(String lB){
		this.labelBox = lB;
		this.labelStorageUnit=null;
		this.bottles = new Bottle[MAX_SIZE];
		numBottles = 0;

	}

	/**
	 * This method allows to know whether the box is full of bottles or there is, at least, an available space
	 * @return a boolean variable, true if the box is full and false if no
	 */
	public boolean isFull(){
		return (numBottles >= MAX_SIZE);
	}

	/**
	 * This method allows to add a bottle to the box, if there is any spare space
	 * @param b the new bottle
	 */
	public void addBottle(Bottle b){
		if(!isFull()){
			bottles[numBottles] = b; 
			numBottles++;
		} 
	}

	/**
	 * This method returns the label of the box which sais to which storage unit it must be delivered
	 * @return the label of the box which sais to which storage unit it must be delivered
	 */
	public String getLabelStorageUnit(){
		return labelStorageUnit;
	}

	/**
	 * This method sets the final storage unit of the box with its label
	 * @param l the label of the box which sais to which storage unit it must be delivered 
	 */
	public void setLabel(String l){
		this.labelStorageUnit=l;
	}

	/**
	 * This method returns the label identifier of the box
	 * @return the identifier label of the box
	 */
	public String getLabelBox(){
		return labelBox;
	}
	
}
