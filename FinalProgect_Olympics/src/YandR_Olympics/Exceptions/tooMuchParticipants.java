//Rivka Doskoch & Yuval Terry 
package YandR_Olympics.Exceptions;

public class tooMuchParticipants extends Exception {
	public tooMuchParticipants() {
		super("Can't add all the athletes .\nYou passed the limit .");
	}
}
