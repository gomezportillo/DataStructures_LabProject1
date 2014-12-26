
/**
 * This class defines a LIFO stack object with its typical operations
 * @author Pedro Manuel Gómez-Portillo López
 * @version 15.Oct.2014
 */

public class MyStack {

	private Box[] stack;
	private int top;
	private int MAX_SIZE = 5;

	/**
	 * This method is the builder of the class
	 */
	public MyStack(){
		stack = new Box[MAX_SIZE];
		top = -1;
	}

	/**
	 * This method stores the box passed through the arguments at the top of the stack
	 * @param b the box we want to store at the top of the stack
	 */
	public void push(Box b){
		top++;
		stack[top]=b;
	}

	/**
	 * This method removes the top box from the stack and returns it
	 * @return the top box of the stack
	 */
	public Box pop(){
		Box tmp= stack[top];
		stack[top]=null; //avoiding loitering http://en.wikipedia.org/wiki/Loitering
		top--;
		return tmp;

	}

	/**
	 * This method returns the top box of the stack without removing it from the stack
	 * @return 
	 */
	public Box top(){
		if(top != -1) { 
			return stack[top];
		}else return null;
	}

	/**
	 * This method returns the size of the stack
	 * @return the size of the stack
	 */
	public int size(){
		int tmp = top;
		return ++tmp;
	}

	/**
	 * This method returns a string with the content of the stack
	 */
	public String toString(){
		String s= "";
		if(top == -1){ s = "This stack is empty"; 
		}else{
			for (int numBoxes = 0; numBoxes <= top; numBoxes++){
				s+=" "+stack[numBoxes].getLabelBox();
			}
		}
		return s;
	}
	
}
