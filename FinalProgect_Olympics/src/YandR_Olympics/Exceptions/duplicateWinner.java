//Rivka Doskoch & Yuval Terry 
package YandR_Olympics.Exceptions;

public class duplicateWinner extends Exception {
	public duplicateWinner() {
		super("Eror!!!\nOnly one Winners can be set in each place. ");
	}
}
