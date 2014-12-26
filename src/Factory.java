import java.util.concurrent.LinkedBlockingQueue;

/**
 * This class will model the object of a factory, using in its tasks all other defined classes in the proyect (drones, boxes, stacks...)
 * @author Pedro Manuel Gómez-Portillo López
 * @version 20.Oct.2014
 */
public class Factory {

	MyStack[] storage_stack = null; 
	Dron[] drones = null; 
	MyStack[] storage_units = null;
	LinkedBlockingQueue<Box> empty_box_belt = null;
	LinkedBlockingQueue<Bottle> bottle_belt = null;
	LinkedBlockingQueue<Cap> cap_belt = null;
	int current_stack, numLabel, time, number_of_stored_boxes, delivered_boxes;

	/**
	 *  Builder of the class. We are passing the number of caps, bottles and boxes through 
	 *  the arguments, as them depend on the implementation of the factory
	 * @param caps number of caps we want to create
	 * @param bottles number of bottles
	 * @param boxes number of boxes
	 */
	public Factory (int caps, int bottles, int boxes){

		storage_stack = new MyStack[3]; //stacks donde se almacenan las cajas despu�s del embotellamiento mientras esperan a los drones
		drones = new Dron[3]; 
		storage_units = new MyStack[4]; //las cuatro plantas (A, B, C, D) donde los drones llevan las cajas
		empty_box_belt = new LinkedBlockingQueue<Box>();
		bottle_belt = new LinkedBlockingQueue<Bottle>();
		cap_belt = new LinkedBlockingQueue<Cap>();
		time = -1; //we initialise time as -1 in order to start it the first time the factory starts working
		current_stack=0;
		number_of_stored_boxes=0;
		delivered_boxes=0;

		for(int i = 0; i<storage_stack.length; i++){
			storage_stack[i] = new MyStack();
		}
		for(int i=0; i<storage_units.length; i++){
			storage_units[i] = new MyStack();
		}
		for(int i = 0; i<drones.length; i++){
			drones[i] = new Dron();
		}

		try{
			int i=0;
			for(i=0; i<caps; i++){
				cap_belt.put(new Cap("T"+i));
			}
			for(i=0; i<bottles; i++){
				bottle_belt.put(new Bottle("B"+i));
			}
			for(i=0; i<boxes; i++){
				empty_box_belt.put(new Box("C"+i));
			}

		}catch(Exception e){ System.out.println("Something went wrong\n"+e); }

	}

	/**
	 * This method accomplish the task of bottling the different bottles with caps and packaging 12 of them in a box, also carring
	 * this box to its pertinent storage stack in order it to wait for the drones
	 */
	public void bottling_and_packaging(){

		Box tmp_box = empty_box_belt.poll(); //cuando las ha devuelto todas, devuelve NULL, lo que hace que luego lance una NullPointerException mas alante

		if(tmp_box != null){

			Bottle tmp_bottle = null;
			Cap tmp_cap = null;
			while(!tmp_box.isFull()){ 

				tmp_cap = cap_belt.poll(); //como estan contadas, nunca devolverá NULL
				tmp_bottle = bottle_belt.poll();
				tmp_bottle.addCap(tmp_cap);	
				tmp_box.addBottle(tmp_bottle); 
			}

			String label;
			numLabel++;
			if 		(numLabel <= 4) label= "A"; //las cuatro primeras cajas al bloque A
			else if (numLabel <= 8) label= "B"; //las cuatro siguientes al bloque B
			else if (numLabel <= 10) label= "C"; //las dos siguientes al bloque C
			else label = "D"; //el resto (2 cajas) al bloque D

			tmp_box.setLabel(label);
			

			if(current_stack > 2){ current_stack=0; } //una vez a cada stack, y vuelta a empezar

			storage_stack[current_stack].push(tmp_box);

			number_of_stored_boxes++;

			current_stack++;
		}	
	}


	/**
	 * This method load a dron with the number of boxes required from the determined stack
	 */
	public void loadDron(){
		int numberStack, numberDron, numberBoxes;
		numberBoxes = howManyBoxesPerDron();

		numberStack = firsStackAvailable(numberBoxes); //stack que tiene >= que esas cajas. Como cada uno tiene asociado un dron, nStack=nDron

		if(numberStack >= 0){ //si el method anterior ha devuelto '-1' es que no hay ningun stack con el minimo de cajas requerido

			numberDron=numberStack; //porque cada dron tiene asignado un unico stack

			for(int j=0; j < numberBoxes; j++){ //le carga las n-cajas del stack pertinente al dron correspondiente
				drones[numberDron].addBox(storage_stack[numberStack].pop());
			}

			drones[numberDron].setFree(false); //lo marca como ocupado
			drones[numberDron].setTime(time); //le asigna el tiempo en el que se ha realizado la carga

		}
	}


	/**
	 * This method finds a stack with at leas 'howManyBoxes', being it the required number of boxes 
	 * @param howManyBoxes the minimun number of boxes the stack has to have
	 * @return '0-2' the number of the stack which meet this condition. '-1' if there is no stack as the one requiered
	 */
	private int firsStackAvailable(int  howManyBoxes) {
		int numberStack=0;
		for (numberStack=0; (numberStack < storage_stack.length && storage_stack[numberStack].size() < howManyBoxes); numberStack++);
		if(numberStack < storage_stack.length) return numberStack;
		else return -1;
	}

	/**
	 * This method is the one managing the unloading of the drones. It check if the dron is full of boxes. If so, it checks 
	 * if it is 7 seconds since it picked up the boxes and if so, it unload the boxes of the determined dron an set it as free again
	 */
	private void unloadDron(){
		for(int i=0; i<drones.length; i++){
			if (!drones[i].isFree()){
				if (time - drones[i].getTime() > 7){ //comprueba si han pasado 7s desde que se le cargaron las cajas
					while (drones[i].getNumberBoxes() > 0){ //reparte las n-cajas en su respectiva Storage Unit

						String label=drones[i].getLabel(); //lee la etiqueta de la siguiente caja a entregar

						if 		(label.equals("A")) { storage_units[0].push(drones[i].getBox()); }
						else if (label.equals("B")) { storage_units[1].push(drones[i].getBox()); }
						else if (label.equals("C")) { storage_units[2].push(drones[i].getBox()); }
						else if (label.equals("D")) { storage_units[3].push(drones[i].getBox()); }	

						delivered_boxes++;
					}

					drones[i].setFree(true);
				}
			}
		}
	}


	/**
	 * This method compute how many boxes has a dron to carry at the same time, as in one determined moment there will not be 3 boxes in a stack 
	 * If the number of boxes stored in the stacks is bigger than 12, it means each dron can carry 1 box at least
	 * @return the number of boxes each dron has to carry
	 */
	private int howManyBoxesPerDron(){
		if(number_of_stored_boxes < 12 ) return 3;
		else return 1;
	}


	/**
	 * This method will be the one encharged of calling the different funtions of the factory deppending on the time 
	 * @return a boolean variable; true if the factory is working, false if it has finished of delivering all the boxes
	 */
	public boolean working(){

		boolean working = true;

		if(delivered_boxes < 12){

			time++;
			
			if (time%3 == 0) bottling_and_packaging();

			loadDron();

			unloadDron();

		}else working=false;

		return working; 
	}

	/**
	 * This method returns the current time of the factory
	 * @return the current time of the factory
	 */
	public int getTime(){
		return time;
	}

	/**
	 * This method prints the content of every storage unit
	 */
	public void printStorageUnits() {
		System.out.println("----- Content of the Storage Units (from bottom to top) -----");
		for(int i=0; i<storage_units.length; i++){
			System.out.println("Storage Unit nº"+i+" ="+storage_units[i].toString());
		}
	}


} //class
