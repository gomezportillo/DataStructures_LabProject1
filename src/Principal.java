/**
 * This is the main class of the proyect. From here, it is call the class Factory and made it work until it finish
 * @author Pedro Manuel Gómez-Portillo López
 * @version 19.Oct.2014
 */
public class Principal {

	public static void main(String[] args) {
	
		Factory factory = new Factory(144, 144, 12);

		while(factory.working());

		System.out.println("Total time: "+factory.getTime() +" time units");

		factory.printStorageUnits();

	}

}
